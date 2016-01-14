package control;

import java.util.ArrayList;
import java.util.Vector;

import model.data.Data;
import view.Log;
import view.XYSeriesChart;

public class MainKG {
	
	public static void main(String[] args) {
		
		Log.p("Krongold");
		ArrayList<Double> snrLevels_8 = TestInstance.getSmallSnrLevelsExample();
		double maxValue = 20;
//		ArrayList<Double> snrLevels_100 = TestInstance.getRandomConnectedSnrLevelsExample(100, maxValue, 1);
		
		ArrayList<Double> snrLevels = new ArrayList<Double>();
		for (double d : Data.getSnrPerChannel()) {
			snrLevels.add(d);
		}
		
		Vector<Double> x = new Vector<Double>();
		Vector<Double> y = new Vector<Double>();
		int i = 1;
		for (Double d : snrLevels) {
			x.add(i+0.0);
			y.add(d);
			i++;
		}
		XYSeriesChart.display(x, y, "XY");
		
		test(snrLevels_8, 8, 8);
	}

	public static void test(ArrayList<Double> snrLevels, double powerBudget, int targetBitRate) {

		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		for (Double snr : snrLevels) {
			noiseLevels.add(1.0/snr);
		}
		
	}
}
