package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Class WaterFilling.
 */
public class WaterFilling {

	/**
	 * Process.
	 *
	 * @param noiseLevels the noise levels
	 * @param powerBudget the power budget
	 * @return the array list
	 */
	public static ArrayList<Double> process(ArrayList<Double> noiseLevels, double powerBudget) {
		
		ArrayList<Double> result = new ArrayList<Double>();
		int n = noiseLevels.size();
		
		int decimals = 1000;
		int m = 1000;
		
		// create new array containing each value and it's index
		int[] valuesAndIdx = new int[n];
		for (int i=0; i<n; i++) {
			valuesAndIdx[i] = (int) (Math.round(noiseLevels.get(i)*decimals)*m + i);
			result.add(0.0);
		}
		
		// Sort
		Arrays.sort(valuesAndIdx);
		
		double[] sortedValues = new double[n];
		int[] sortedIdx = new int[n];
		for (int i=0; i<n; i++) {
			sortedIdx[i] = valuesAndIdx[i]%m;
			sortedValues[i] = (valuesAndIdx[i]-sortedIdx[i])/(m*decimals*1.0);
		}
		
		// waterfilling
		double[] sortedResult = new double[n]; 
		double budget = powerBudget;
		int j = 1;
		while (budget > 0 && j < n) {
			double diff = sortedValues[j] - ( sortedValues[j-1] + sortedResult[j-1] );
			if (budget >= diff * (j-1)) {
				for (int k=0; k < j; k++) {
					sortedResult[k] += diff;
					budget -= diff;
				}
			} else {
				double rest = budget/j;
				for (int k=0; k < j; k++) {
					sortedResult[k] += rest;
					budget -= rest;
				}
			}
			j++;
		}
		if (budget > 0) {
			double rest = budget/n;
			for (int k=0; k < n; k++) {
				sortedResult[k] += rest;
				budget -= rest;
			}
		}
		
		// transfer sorted result to the result array list
		for (int i=0; i<n; i++) {
			result.set(sortedIdx[i], sortedResult[i]);
		}
		
		return result;
	}
	
}
