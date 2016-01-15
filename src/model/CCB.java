package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.data.Data;
import view.Log;

public class CCB {

	public static ArrayList<Double> process(ArrayList<Double> noiseLevels, double powerBudget, int totalBitRate) {
		ArrayList<Double> result = new ArrayList<Double>();
		
		HashMap<Integer, Double> snrOverRateFor1EMinus2Ber = Data.getSnrOfModulationRateFor1EMinus2Ber();
		
		// The maximum number of usable carriers
		int n = noiseLevels.size();
		
		// Distribute power equally over all subcarriers and compute SNR values based on this power
		double[] snr = new double[n];
		double[] powers = new double[n];
		for (int i=0; i<n; i++) {
			// power := total power / number of subcarriers
			double power = powerBudget / n;
			powers[i] = power;
			snr[i] = power / noiseLevels.get(i);
		}
		
		// The SNR gap as the well known gap approximation
		@SuppressWarnings("unused")
		double gammaInDb = 9.8;
		// Set current system performance margin gammaMargin as 0 dB
		double gammaMarginInDb = 0;
		
		int iterateCount = 0;
		int maxCount = 10;
		
		int usedCarriers = n;
		
		int bitsTotal = 0;
		
		// The desired number of bits per DMT symbol
		int bitsTarget = totalBitRate;

		double[] bits = new double[n];
		int[] b2 = new int[n];
		double[] diff = new double[n];

		while (bitsTotal < bitsTarget && iterateCount < maxCount) {
			usedCarriers = n;
			
			for (int i=0; i<n; i++) {
				b2[i] = getBitForSpecifiedSnr(snr[i], snrOverRateFor1EMinus2Ber);
				if (b2[i] == 0) {
					usedCarriers = usedCarriers-1;
				}
			}
			
			// Calculate bitsTotal, stop and declare bad channel if bitsTotal = 0
			bitsTotal = 0;
			for (int i=0; i<n; i++) {
				bitsTotal = bitsTotal + b2[i];
			}
			if (bitsTotal == 0) {
				Log.p("Bad channel..");
				return result;
			}
			
			// Compute new gammaMargin
			gammaMarginInDb = gammaMarginInDb + Converter.getValueInDb(Math.pow(2, (bitsTotal-bitsTarget+0.0)/usedCarriers));
			
			iterateCount = iterateCount + 1;
		}
		
		// Subtract one bit at a time
		while (bitsTotal > bitsTarget) {
			int minDiffIdx = -1;
			double minDiffValue = Double.MAX_VALUE;
			for (int i=0; i<n; i++) {
				if (diff[i] < minDiffValue && b2[i] > 0) {
					minDiffValue = diff[i];
					minDiffIdx = i;
				}
			}
			b2[minDiffIdx] = b2[minDiffIdx] - 1;
			diff[minDiffIdx] = bits[minDiffIdx] - b2[minDiffIdx];
			bitsTotal = bitsTotal - 1;
		}
		
		return result;
	}
	
	private static int getBitForSpecifiedSnr(double snr, HashMap<Integer, Double> snrOverRateFor1EMinus2Ber) {
		int bit = 1;
		double maxValidSnr = snrOverRateFor1EMinus2Ber.get(bit);
		if (maxValidSnr > snr) {
			maxValidSnr = 0.0;
			bit = 0;
		}
		for (Integer iBit : snrOverRateFor1EMinus2Ber.keySet()) {
			double currentSnr = snrOverRateFor1EMinus2Ber.get(iBit);
			if (maxValidSnr < currentSnr && currentSnr <= snr) {
				maxValidSnr = currentSnr;
				bit = iBit;
			}
		}
		return bit;
	}

}
