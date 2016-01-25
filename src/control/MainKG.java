package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import model.Converter;
import model.data.Data;
import view.Log;
import view.XYSeriesChart;

public class MainKG {
	
	public static void main(String[] args) {
		
		Log.p("Krongold");
//		ArrayList<Double> snrLevels_8 = TestInstance.getSmallSnrLevelsExample();
//		double maxValue = 20;
//		ArrayList<Double> snrLevels_100 = TestInstance.getRandomConnectedSnrLevelsExample(100, maxValue, 1);
		
		double powerBudget = 1;
		ArrayList<Double> snrLevelsInDb = new ArrayList<Double>();
		for (double d : Data.getSnrPerChannelInDb()) {
			snrLevelsInDb.add(d);
		}
		ArrayList<Double> oldPowerLevels = new ArrayList<Double>();
		int size = snrLevelsInDb.size();
		double oldPower = 1.0 / size;
		for (int i=0; i<size; i++) {
			oldPowerLevels.add(oldPower);
		}
		
		test(snrLevelsInDb, powerBudget, 0.01, oldPowerLevels);
	}

	public static void test(ArrayList<Double> snrLevelsInDb, double powerBudget, double targetBer, ArrayList<Double> oldPowerLevels) {

		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		int size = snrLevelsInDb.size();
		for (int i=0; i<size; i++) {
			double snr = Converter.getValue(snrLevelsInDb.get(i));
			noiseLevels.add(oldPowerLevels.get(i)/snr);
		}
		XYSeriesChart.plot(snrLevelsInDb, "SNR");
		XYSeriesChart.plot(noiseLevels, "Noise Levels");
		
		Vector<Integer> mods = Data.getAvaliableModulationRates();
		HashMap<Integer, Double> snrOfModRate = Data.getSnrOfModulationRateFor1EMinus2Ber();
		Vector<Double> x = new Vector<Double>();
		Vector<Double> snrValues = new Vector<Double>();
		x.add(0.0);
		snrValues.add(0.0);
		for (int i : mods) {
			x.add(i+0.0);
			snrValues.add(snrOfModRate.get(i));
		}
		XYSeriesChart.display(x, snrValues, "ModRate x SNR");
		
		double prevMod = 0;
		double prevSnr = 0;
		Vector<Double> betas = new Vector<Double>();
		for (int currMod : mods) {
			double currSnr = snrOfModRate.get(currMod);
			Log.p(currMod + " >> " + currSnr);
			double beta = (currSnr - prevSnr) / (currMod - prevMod);
			betas.add(beta);
			Log.p("beta(" + currMod + ") = " + beta);
			prevMod = currMod;
			prevSnr = currSnr;
		}
		
		double totalPowerLow = 0;
		double totalBitLow = 0;
		
		int maxBitRate = mods.lastElement();
		double totalBitHigh = maxBitRate * size;
		double totalPowerHigh = 0.0;
		for (int i=0; i<size; i++) {
			totalPowerHigh += snrOfModRate.get(maxBitRate) * noiseLevels.get(i);
		}
		
		int counter = 1;
		int maxIteration = 20;
		ArrayList<Double> rates = new ArrayList<Double>();
		ArrayList<Double> powers = new ArrayList<Double>();
		double prevPower = -1;
		double tol = 0.00000001;
		while (totalPowerLow <= powerBudget && powerBudget <= totalPowerHigh) {
			double lambda = (totalPowerHigh - totalPowerLow) / (totalBitHigh - totalBitLow);
			Log.ps("Lambda = %f", lambda);
			
			double totalNewPower = 0.0;
			int totalNewBitRate = 0;
			rates = new ArrayList<Double>();
			powers = new ArrayList<Double>();
			for (double nLvl : noiseLevels) {
				int r = getRate(lambda/nLvl, mods, betas);
				double p = snrOfModRate.get(r) * nLvl;
				powers.add(p);
				totalNewPower += p;
				totalNewBitRate += r;
				rates.add(r+0.0);
			}
			Log.ps("New total rate = %d", totalNewBitRate);
			Log.ps("New total power = %f", totalNewPower);
			
			if (Math.abs(totalNewPower - totalPowerLow) <= tol && Math.abs(totalNewPower - totalPowerHigh) <= tol) {
				break;
			} else if (Math.abs(totalNewPower - powerBudget) < tol && totalNewPower < powerBudget) {
				break;
			} else if (totalNewPower > powerBudget) {
				totalPowerHigh = totalNewPower;
				totalBitHigh = totalNewBitRate;
			} else { // total new power < power budget
				totalPowerLow = totalNewPower;
				totalBitLow = totalNewBitRate;
			}
			
			if (Math.abs(totalNewPower - prevPower) < tol) {
				break;
			}
			prevPower = totalNewPower;
			counter++;
			if (counter > maxIteration) {
				break;
			}
		}
		
		XYSeriesChart.plot(rates, "Rate");
		XYSeriesChart.plot(powers, "Power");
		
	}
	
	private static int getRate(double lambda, Vector<Integer> mods, Vector<Double> betas) {
		int rate = 0;
		for (int currMod : mods) {
			if (betas.get(currMod-1) > lambda) {
				rate = currMod-2;
				break;
			}
		}
		if (lambda > betas.lastElement()) {
			rate = mods.lastElement();
		}
		return rate;
	}
	
}
