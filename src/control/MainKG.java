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
			noiseLevels.add(powerBudget * oldPowerLevels.get(i)/snr);
		}
		
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
		
//		XYSeriesChart.display(x, snrValues, "ModRate x SNR");
		
		double lambda = 0.7;
		Log.ps("lambda = %f ", lambda);
		
	}
	
}
