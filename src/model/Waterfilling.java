package model;

import java.util.ArrayList;
import java.util.Arrays;

import view.Log;

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
		int m = (int) Math.round(Math.pow(10, (""+n).length()));
		
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
	
	/**
	 * Rate adaptive process.
	 *
	 * @param snrLevels the snr levels
	 * @param gamma the gamma
	 * @param powerBudget the power budget
	 * @return the array list
	 */
	public static ArrayList<Double> rateAdaptiveProcess(ArrayList<Double> snrLevels, double gamma, double powerBudget) {
		
		ArrayList<Double> result = new ArrayList<Double>();
		int n = snrLevels.size();
		
		int decimals = 1000;
		int m = (int) Math.round(Math.pow(10, (""+n).length()));
		
		// create new array containing each value and it's index
		int[] snrValuesAndIdx = new int[n];
		for (int i=0; i<n; i++) {
			snrValuesAndIdx[i] = (int) (Math.round(snrLevels.get(i)*decimals)*m + i);
			result.add(0.0);
		}
		
		// Sort g_1 > ... > g_n
		Arrays.sort(snrValuesAndIdx);
		
		double[] sortedSnrValues = new double[n];
		int[] sortedIdx = new int[n];
		for (int i=0; i<n; i++) {
			sortedIdx[i] = snrValuesAndIdx[n-1-i]%m;
			sortedSnrValues[i] = (snrValuesAndIdx[n-1-i]-sortedIdx[i])/(m*decimals*1.0);
		}
		
		int idx = n-1;
		
		double bigK2 = powerBudget;
		for (int i=0; i<n; i++) {
			bigK2 += gamma / sortedSnrValues[i];
		}
		
		double bigK = 0.0;
		
		boolean lowestEnergyIsPositive = false;
		
		while (!lowestEnergyIsPositive) {
			
			bigK = bigK2 / (idx + 1);
			
			double lowestEnergy = bigK - (gamma/sortedSnrValues[idx]);
			
			if ( lowestEnergy <= 0 ) {
				bigK2 = bigK2 - gamma/sortedSnrValues[idx];
				idx = idx - 1;
			} else {
				lowestEnergyIsPositive = true;
			}
		}
		
		// transfer result to the result array list
		for (int i=0; i<n; i++) {
			result.set(sortedIdx[i], Math.max(0, bigK - gamma/sortedSnrValues[i]));
		}
		
		return result;
	}
	
	/**
	 * Margin adaptive process.
	 *
	 * @param snrLevels the snr levels
	 * @param gamma the gamma
	 * @param targetBitRate the target bit rate
	 * @return the array list
	 */
	public static ArrayList<Double> marginAdaptiveProcess(ArrayList<Double> snrLevels, double gamma, double targetBitRate) {
		
		ArrayList<Double> result = new ArrayList<Double>();
		int n = snrLevels.size();
		
		int decimals = 1000;
		int m = (int) Math.round(Math.pow(10, (""+n).length()));
		
		// create new array containing each value and it's index
		int[] snrValuesAndIdx = new int[n];
		for (int i=0; i<n; i++) {
			snrValuesAndIdx[i] = (int) (Math.round(snrLevels.get(i)*decimals)*m + i);
			result.add(0.0);
		}
		
		// Sort g_1 > ... > g_n
		Arrays.sort(snrValuesAndIdx);
		
		double[] sortedSnrValues = new double[n];
		int[] sortedIdx = new int[n];
		for (int i=0; i<n; i++) {
			sortedIdx[i] = snrValuesAndIdx[n-1-i]%m;
			sortedSnrValues[i] = (snrValuesAndIdx[n-1-i]-sortedIdx[i])/(m*decimals*1.0);
		}
		
		int idx = n-1;
		
		double logTwoOfbigK2 = 2*targetBitRate;
		for (int i=0; i<n; i++) {
			logTwoOfbigK2 -= Converter.log2(sortedSnrValues[i]);
		}
		
		double bigK = 0;
		
		boolean lowestEnergyIsPositive = false;
		
		while (!lowestEnergyIsPositive) {
			
			double logTwoOfbigK = Converter.log2(gamma) + logTwoOfbigK2 / (idx + 1);
			bigK = Math.pow(2, logTwoOfbigK);
			
			double lowestEnergy = bigK - (gamma/sortedSnrValues[idx]);
			
			if ( lowestEnergy <= 0 ) {
				logTwoOfbigK2 = logTwoOfbigK2 + Converter.log2(sortedSnrValues[idx]);
				idx = idx - 1;
			} else {
				lowestEnergyIsPositive = true;
			}
		}
		
		// transfer result to the result array list
		for (int i=0; i<n; i++) {
			result.set(sortedIdx[i], Math.max(0, bigK - gamma/sortedSnrValues[i]));
		}
		
		return result;
	}}
