package model;

import java.util.ArrayList;
import java.util.Arrays;

import view.Log;

/**
 * The Class HughesHartoggs.
 */
public class HughesHartoggs {

	/**
	 * Process.
	 *
	 * @param noiseLevels the noise levels
	 * @param powerBudget the power budget
	 * @param targetBitRate the target bit rate
	 * @return the array list
	 */
	public static ArrayList<Double> process(ArrayList<Double> noiseLevels, double powerBudget, int targetBitRate) {
		
		ArrayList<Double> result = new ArrayList<Double>();

		int n = noiseLevels.size();
		
		// create new array containing each value and it's index
		double[] noise = new double[n];
		int k=0;
		for (double d : noiseLevels) {
			noise[k] = d;
			k++;
		}
		
		double[] power = new double[n];
		double powerBudgetLeft = powerBudget;
		
		int[] transportedBits = new int[n];
		int totalTransportedBits = 0;
		
		while (totalTransportedBits <= targetBitRate) {
			double minRequiredPower = Double.MAX_VALUE;
			int minIndex = -1;
			for (int i=0; i<n; i++) {
				double snr = Converter.getSNR( getSNRDependsOnBits(transportedBits[i]+1) );
				double currentRequiredPower = noise[i] * snr;
				if (minRequiredPower > currentRequiredPower) {
					minRequiredPower = currentRequiredPower;
					minIndex = i;
				}
			}
			
			double oldUsedPower = power[minIndex];
			double snr = Converter.getSNR( getSNRDependsOnBits(transportedBits[minIndex]+1) );
			double newUsedPower = noise[minIndex]*snr;
			powerBudgetLeft = powerBudgetLeft - newUsedPower + oldUsedPower;
			if (powerBudgetLeft < 0) {
				break;
			}
			
			transportedBits[minIndex] = transportedBits[minIndex] + 1; 
			totalTransportedBits++;
			power[minIndex] = newUsedPower;
		}
		
		for (int i=0; i<n; i++) {
			result.add(power[i]);
		}
		
		return result;
	}
	
	/**
	 * Gets the SNR depends on bits.
	 *
	 * @param bits the bits
	 * @return the SNR depends on bits
	 */
	private static double getSNRDependsOnBits(int bits) {
		return bits*3 + 3;
	}
	
}
