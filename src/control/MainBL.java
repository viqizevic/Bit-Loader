package control;

import java.util.ArrayList;

import view.Log;
import model.ChowCioffiBingham;
import model.HughesHartoggs;
import model.Waterfilling;

public class MainBL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Double> values = new ArrayList<Double>();
		values.add(7.2);
		values.add(8.8);
		values.add(3.1);
		values.add(45.4);
		
		double budget = 40;
		
		ArrayList<Double> result = Waterfilling.process(values, budget);
		printResult(values, result);
//		ArrayList<Double> result = HughesHartoggs.process(values, budget);
		result = ChowCioffiBingham.process(values, budget);
		
		printResult(values, result);
	}
	
	private static void printResult(ArrayList<Double> values, ArrayList<Double> result) {
		String res = "";
		double b = 0;
		for (int i=0; i<result.size(); i++) {
			double v = values.get(i);
			double r = result.get(i);
			double t = v + r;
			res += String.format("%5.2f + %5.2f = %5.2f", v, r, t) + "\n";
			b += r;
		}
		if (b > 0) {
			res += String.format("    B = %5.2f", b);
			Log.p(res);
		}
	}

}
