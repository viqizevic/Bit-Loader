package model;

import java.util.ArrayList;

import view.Log;

/**
 * The Class ChowCioffiBingham.
 */
public class ChowCioffiBingham {
	
	/** The gamma in db_. */
	private static double gammaInDb_;

	/**
	 * Process.
	 *
	 * @param noiseLevels the noise levels
	 * @param powerBudget the power budget
	 * @param totalBitRate the total bit rate
	 * @return the array list
	 */
	public static ArrayList<Double> process(ArrayList<Double> noiseLevels, double powerBudget, int totalBitRate) {
		
		ArrayList<Double> result = new ArrayList<Double>();
		
		// The maximum number of usable carriers
		int n = noiseLevels.size();
		
		// Distribute power equally over all subcarriers and compute SNR values based on this power
		double[] snr = new double[n];
		for (int i=0; i<n; i++) {
			// power := total power / number of subcarriers
			double power = powerBudget / n;
			snr[i] = power / noiseLevels.get(i);
		}
		
		// The SNR gap as the well known gap approximation
		double gammaInDb = 9.8;
		// Set current system performance margin gammaMargin as 0 dB
		double gammaMarginInDb = 0;
		
		int iterateCount = 0;
		int maxCount = 10;
		
		int usedCarriers = n;
		
		int bitsTotal = 0;
		
		// The desired number of bits per DMT symbol
		int bitsTarget = totalBitRate;

		double[] b = new double[n];
		int[] b2 = new int[n];
		double[] diff = new double[n];

		while (bitsTotal < bitsTarget && iterateCount < maxCount) {
			usedCarriers = n;
			
			for (int i=0; i<n; i++) {
				b[i] = Math.log( 1 + snr[i]/(Converter.getValue(gammaInDb+gammaMarginInDb)) )/Math.log(2);
				b2[i] = (int) Math.floor(b[i]);
				diff[i] = b[i] - b2[i];
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
			diff[minDiffIdx] = b[minDiffIdx] - b2[minDiffIdx];
			bitsTotal = bitsTotal - 1;
		}
		
		// Add one bit at a time
		while (bitsTotal < bitsTarget) {
			int maxDiffIdx = -1;
			double maxDiffValue = -Double.MAX_VALUE;
			for (int i=0; i<n; i++) {
				if (diff[i] > maxDiffValue) {
					maxDiffValue = diff[i];
					maxDiffIdx = i;
				}
			}
			b2[maxDiffIdx] = b2[maxDiffIdx] + 1;
			diff[maxDiffIdx] = b[maxDiffIdx] - b2[maxDiffIdx];
			bitsTotal = bitsTotal + 1;
		}
		
		for (int i=0; i<n; i++) {
			double snrValue = (Math.pow(2, 2*b2[i]) - 1) * Converter.getValue(gammaInDb + gammaMarginInDb);
			result.add(noiseLevels.get(i) * snrValue);
		}
		
		gammaInDb_ = gammaInDb + gammaMarginInDb;

		return result;
	}

	/**
	 * Gets the gamma in db.
	 *
	 * @return the gamma in db
	 */
	public static double getGammaInDb() {
		return gammaInDb_;
	}
	
	
	
}
