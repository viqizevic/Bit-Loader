package control;

import java.util.ArrayList;

import model.CCB;
import model.Converter;
import view.Log;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		ArrayList<Double> snrLevels_8 = TestInstance.getSmallSnrLevelsExample();
		double maxValue = 20;
		ArrayList<Double> snrLevels_100 = TestInstance.getRandomSnrLevelsExample(100, maxValue);
		ArrayList<Double> snrLevels_5000 = TestInstance.getRandomSnrLevelsExample(5000, maxValue);
		
		test(snrLevels_8, 8, 8);
//		test(snrLevels_100, 50, 40);
//		test(snrLevels_5000, 10000, 10000);
	}

	@SuppressWarnings("unused")
	public static void test(ArrayList<Double> snrLevels, double powerBudget, int targetBitRate) {

		ArrayList<Double> noiseLevels = new ArrayList<Double>();
		for (Double snr : snrLevels) {
			noiseLevels.add(1.0/snr);
		}
		double gamma = Converter.getValue(0.0);
		
		Log.p("CCB");
		ArrayList<Double> powerLevels = CCB.process(noiseLevels, powerBudget, targetBitRate);
	}
}
