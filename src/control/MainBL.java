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
		
		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		noiseLevels.add(1/19.94);
		noiseLevels.add(1/17.03);
		noiseLevels.add(1/17.03);
		noiseLevels.add(1/10.0);
		noiseLevels.add(1/10.0);
		noiseLevels.add(1/2.968);
		noiseLevels.add(1/2.968);
		noiseLevels.add(1/0.552);

		double powerBudget = 8;
		
		int targetBitRate = 8;
		
		Log.p("Waterfilling");
		ArrayList<Double> powerLevels = WaterFilling.process(noiseLevels, powerBudget);
		printResult(noiseLevels, powerLevels);
		printHtmlChart("Waterfilling", noiseLevels, powerLevels);

		ArrayList<Double> snrLevels = new ArrayList<Double>();
		snrLevels.add(19.94);
		snrLevels.add(17.03);
		snrLevels.add(17.03);
		snrLevels.add(10.0);
		snrLevels.add(10.0);
		snrLevels.add(2.968);
		snrLevels.add(2.968);
		snrLevels.add(0.552);
		
		Log.p("Waterfilling (Rate adaptive)");
		double gamma = Converter.getValue(0.0);
		powerLevels = WaterFilling.rateAdaptiveProcess(snrLevels, gamma, powerBudget);
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Waterfilling (Rate adaptive)", noiseLevels, powerLevels);
		
		Log.p("Waterfilling (Margin adaptive)");
		gamma = Converter.getValue(8.8);
		powerLevels = WaterFilling.marginAdaptiveProcess(snrLevels, gamma, targetBitRate);
		printResultWithGamma(snrLevels, gamma, powerLevels);
		printHtmlChart("Waterfilling (Margin adaptive)", noiseLevels, powerLevels);
		
		Log.p("Hughes Hartoggs");
		powerLevels = HughesHartoggs.process(noiseLevels, powerBudget, targetBitRate);
		printResult(noiseLevels, powerLevels);
		printHtmlChart("Hughes Hartoggs", noiseLevels, powerLevels);
		
		Log.p("Chow Cioffi Bingham");
		powerLevels = ChowCioffiBingham.process(noiseLevels, powerBudget, targetBitRate);
		printResultWithGamma(snrLevels, ChowCioffiBingham.getGammaInDb(), powerLevels);
		Log.p("Gamma: " + ChowCioffiBingham.getGammaInDb());
		printHtmlChart("Chow Cioffi Bingham", noiseLevels, powerLevels);
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
			res += String.format("%5.2f + %5.2f = %5.2f (%5.2f)", noise, power, total, bit) + "\n";
			budget += power;
			rate += bit;
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
