package model;

import java.util.ArrayList;

import view.Log;

public class ChowCioffiBingham {

	public static ArrayList<Double> process(ArrayList<Double> noiseLevels, double totalPower) {
		
		ArrayList<Double> result = new ArrayList<Double>();
		
		// The maximum number of usable carriers
		int n = noiseLevels.size();
		
		// Distribute power equally over all subcarriers and compute SNR values based on this power
		double[] snr = new double[n];
		double power = totalPower/n;
		for (int i=0; i<n; i++) {
			snr[i] = power/noiseLevels.get(i);
		}
		
		// The SNR gap in the well known gap approximation
		double gamma = 1;
		// Set current system performance margin gammaMargin as 0 dB
		double gammaMargin = 0;
		
		int iterateCount = 0;
		int maxCount = 10;
		
		int usedCarriers;
		
		int bitsTotal = 0;
		
		// The desired number of bits per DMT symbol
		int bitsTarget = 4;

		double[] b = new double[n];
		int[] b2 = new int[n];
		double[] diff = new double[n];

		while (bitsTotal != bitsTarget && iterateCount < maxCount) {
			bitsTotal = 0;
			usedCarriers = n;
			
			for (int i=0; i<n; i++) {
				b[i] = Math.log( 1 + snr[i]/(gamma+gammaMargin) )/Math.log(2);
				b2[i] = (int) Math.floor(b[i]);
				diff[i] = b[i] - b2[i];
				if (b2[i] == 0) {
					usedCarriers = usedCarriers-1;
				}
			}
			
			// Calculate bitsTotal, stop and declare bad channel if bitsTotal = 0
			for (int i=0; i<n; i++) {
				bitsTotal = bitsTotal + b2[i];
			}
			if (bitsTotal == 0) {
				Log.p("Bad channel..");
				return result;
			}
			
			// Compute new gammaMargin
			gammaMargin = gammaMargin + 10 * Math.log10(Math.pow(2, (bitsTotal-bitsTarget)/usedCarriers));
			
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
			if (minDiffIdx != -1) {
				b2[minDiffIdx] = b2[minDiffIdx] - 1;
				diff[minDiffIdx] = b[minDiffIdx] - b2[minDiffIdx];
				bitsTotal = bitsTotal - 1;
			}
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
			if (maxDiffIdx != -1) {
				b2[maxDiffIdx] = b2[maxDiffIdx] + 1;
				diff[maxDiffIdx] = b[maxDiffIdx] - b2[maxDiffIdx];
				bitsTotal = bitsTotal + 1;
			}
		}

		return result;
	}
	
	
	
}
