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
		// Set current system performance margin Gamma0 as 0 dB
		double gamma0 = 0;
		int iterateCount = 0;
		int usedCarriers = n;
		
		double[] b = new double[n];
		int[] b2 = new int[n];
		double[] diff = new double[n];
		int bitsTotal = 0;
		
		for (int i=0; i<n; i++) {
			b[i] = Math.log( 1 + snr[i]/(gamma+gamma0) )/Math.log(2);
			b2[i] = (int) Math.floor(b[i]);
			diff[i] = b[i] - b2[i];
			if (b2[i] == 0) {
				usedCarriers = usedCarriers-1;
			}
			bitsTotal = bitsTotal + b2[i];
		}
		
		if (bitsTotal == 0) {
			Log.p("Bad channel..");
			return result;
		}
		
		int bitsBudget = 100;
		
		// Compute new Gamma0
		gamma0 = 0;
		
		
		return result;
	}
	
	
	
}
