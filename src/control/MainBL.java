package control;

import java.util.ArrayList;

import view.Log;
import model.ChowCioffiBingham;
import model.Converter;
import model.HughesHartoggs;
import model.WaterFilling;

/**
 * The Class MainBL.
 */
public class MainBL {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		noiseLevels.add(1/19.94);
		noiseLevels.add(1/17.03);
		noiseLevels.add(1/17.03);
		noiseLevels.add(1/10.0);
		noiseLevels.add(1/10.0);
		noiseLevels.add(1/2.968);
		noiseLevels.add(1/2.968);
		noiseLevels.add(1/0.0552);
		
		double powerBudget = 8;
		
		int targetBitRate = 20;
		
		Log.p("Waterfilling..");
		ArrayList<Double> result = WaterFilling.process(noiseLevels, powerBudget);
		printResult(noiseLevels, result);

		Log.p("Hughes Hartoggs..");
		result = HughesHartoggs.process(noiseLevels, powerBudget, targetBitRate);
		printResult(noiseLevels, result);

		Log.p("Chow Cioffi Bingham..");
		result = ChowCioffiBingham.process(noiseLevels, powerBudget, targetBitRate);
		printResult(noiseLevels, result);
	}
	
	/**
	 * Prints the result.
	 *
	 * @param noiseLevels the noise levels
	 * @param powerLevels the power levels
	 */
	private static void printResult(ArrayList<Double> noiseLevels, ArrayList<Double> powerLevels) {
		String res = "";
		double budget = 0;
		double rate = 0;
		for (int i=0; i<powerLevels.size(); i++) {
			double noise = noiseLevels.get(i);
			double power = powerLevels.get(i);
			double total = noise + power;
			double bit = Math.max( ( Converter.getValueInDb(power/noise) - 3 ) / 3, 0);
			res += String.format("%5.2f + %5.2f = %5.2f (%5.2f)", noise, power, total, bit) + "\n";
			budget += power;
			rate += bit;
		}
		res += String.format("    B = %5.2f", budget) + "\n";
		res += String.format("    R = %5.2f", rate);
		Log.p(res);
	}

}
