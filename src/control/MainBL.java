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
		values.add(1/19.94);
		values.add(1/17.03);
		values.add(1/17.03);
		values.add(1/10.0);
		values.add(1/10.0);
		values.add(1/2.968);
		values.add(1/2.968);
		values.add(1/0.0552);
		
		double budget = 8;
		
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
