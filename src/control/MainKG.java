package control;

import java.util.ArrayList;

import view.Log;

public class MainKG {
	
	public static void main(String[] args) {
		
		Log.p("Krongold");
		ArrayList<Double> snrLevels_8 = TestInstance.getSmallSnrLevelsExample();
		double maxValue = 20;
		ArrayList<Double> snrLevels_100 = TestInstance.getRandomConnectedSnrLevelsExample(10, maxValue, 3);
		
		test(snrLevels_8, 8, 8);
	}

	public static void test(ArrayList<Double> snrLevels, double powerBudget, int targetBitRate) {

		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		for (Double snr : snrLevels) {
			noiseLevels.add(1.0/snr);
		}
		
	}
}
