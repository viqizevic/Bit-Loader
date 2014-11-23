package control;

import java.util.ArrayList;
import java.util.HashMap;

import view.Log;
import view.Template;
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
		
//		double maxValue = 20;
//		ArrayList<Double> snrLevels = TestInstance.getRandomSnrLevelsExample(5000, maxValue);
//		ArrayList<Double> snrLevels = TestInstance.getRandomSnrLevelsExample(100, maxValue);
		ArrayList<Double> snrLevels = TestInstance.getSmallSnrLevelsExample();

		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		for (Double snr : snrLevels) {
			noiseLevels.add(1.0/snr);
		}
		
//		double powerBudget = 10000;
//		double powerBudget = 50;
		double powerBudget = 8;
		
//		int targetBitRate = 10000;
//		int targetBitRate = 40;
		int targetBitRate = 8;
		double gamma = Converter.getValue(0.0);
		
		Log.p("Waterfilling");
		long time = System.currentTimeMillis();
		ArrayList<Double> powerLevels = WaterFilling.process(noiseLevels, powerBudget);
		Log.p(String.format("Elapsed time: %.3f (ms)", (System.currentTimeMillis()-time)/1000.0));
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Waterfilling", noiseLevels, powerLevels);
		Log.p("");

		Log.p("Waterfilling (Rate adaptive)");
		time = System.currentTimeMillis();
		powerLevels = WaterFilling.rateAdaptiveProcess(snrLevels, gamma, powerBudget);
		Log.p(String.format("Elapsed time: %.3f (ms)", (System.currentTimeMillis()-time)/1000.0));
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Waterfilling (Rate adaptive)", noiseLevels, powerLevels);
		Log.p("");

		Log.p("Waterfilling (Margin adaptive)");
		gamma = Converter.getValue(8.8);
		time = System.currentTimeMillis();
		powerLevels = WaterFilling.marginAdaptiveProcess(snrLevels, gamma, targetBitRate);
		Log.p(String.format("Elapsed time: %.2f (ms)", (System.currentTimeMillis()-time)/1000.0));
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Waterfilling (Margin adaptive)", noiseLevels, powerLevels);
		Log.p("");
		
		Log.p("Hughes Hartoggs");
		time = System.currentTimeMillis();
		powerLevels = HughesHartoggs.process(noiseLevels, powerBudget, targetBitRate);
		Log.p(String.format("Elapsed time: %.2f (ms)", (System.currentTimeMillis()-time)/1000.0));
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Hughes Hartoggs", noiseLevels, powerLevels);
		Log.p("");
		
		Log.p("Chow Cioffi Bingham");
		time = System.currentTimeMillis();
		powerLevels = ChowCioffiBingham.process(noiseLevels, powerBudget, targetBitRate);
		printResultWithGamma(snrLevels, ChowCioffiBingham.getGammaInDb(), powerLevels);
		Log.p(String.format("Elapsed time: %.2f (ms)", (System.currentTimeMillis()-time)/1000.0));
		Log.p(String.format("Gamma: %.4f", ChowCioffiBingham.getGammaInDb()));
		printHtmlChart("Chow Cioffi Bingham", noiseLevels, powerLevels);
		Log.p("");
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
			budget += power;
			rate += bit;
			if (powerLevels.size() > 10) {
				continue;
			}
			res += String.format("%5.2f + %5.4f = %5.2f (%5.2f)", noise, power, total, bit) + "\n";
		}
		res += String.format("    B = %5.2f", budget) + "\n";
		res += String.format("    R = %5.2f", rate) + "\n";
		res += String.format("  R/B = %5.2f", rate/budget);
		Log.p(res);
	}

	/**
	 * Prints the result with gamma.
	 *
	 * @param snrLevels the snr levels
	 * @param gamma the gamma
	 * @param powerLevels the power levels
	 */
	private static void printResultWithGamma(ArrayList<Double> snrLevels, double gamma, ArrayList<Double> powerLevels) {
		String res = "";
		double budget = 0;
		double rate = 0;
		for (int i=0; i<powerLevels.size(); i++) {
			double noise = 1.0 / snrLevels.get(i);
			double power = powerLevels.get(i);
			double total = noise + power;
			double bit = 0.5 * Converter.log2(1 + power * snrLevels.get(i) / gamma);
			budget += power;
			rate += bit;
			if (powerLevels.size() > 10) {
				continue;
			}
			res += String.format("%5.2f + %5.4f = %5.2f (%5.2f)", noise, power, total, bit) + "\n";
		}
		res += String.format("    B = %5.2f", budget) + "\n";
		res += String.format("    R = %5.2f", rate) + "\n";
		res += String.format("  R/B = %5.2f", rate/budget);
		Log.p(res);
	}
	
	/**
	 * Prints the html chart.
	 *
	 * @param title the title
	 * @param noiseLevels the noise levels
	 * @param powerLevels the power levels
	 */
	private static void printHtmlChart(String title, ArrayList<Double> noiseLevels, ArrayList<Double> powerLevels) {
		if (powerLevels.size() == 0) {
			return;
		}
		if (powerLevels.size() > 200) {
			return;
		}
		String data1 = "[";
		String data2 = "[";
		for (int i=0; i<powerLevels.size(); i++) {
			data1 += String.format("%.3f, ", noiseLevels.get(i));
			data2 += String.format("%.3f, ", powerLevels.get(i));
		}
		data1 = data1.substring(0, data1.length()-", ".length()) + "]";
		data2 = data2.substring(0, data2.length()-", ".length()) + "]";
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("<VAR:title>", title);
		hm.put("<VAR:data1>", data1);
		hm.put("<VAR:data2>", data2);
		
		title = title.replace(' ', '-').toLowerCase();
		Template.readAndPrintTemplate("src/view/main.html.tpl", hm, "html/"+title+".html");
	}
}
