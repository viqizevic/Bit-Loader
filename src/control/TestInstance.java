package control;

import java.util.ArrayList;

/**
 * The Class TestInstance.
 */
public class TestInstance {
	
	/**
	 * Gets the small snr levels example.
	 *
	 * @return the small snr levels example
	 */
	public static ArrayList<Double> getSmallSnrLevelsExample() {
		ArrayList<Double> snrLevels = new ArrayList<Double>();
		snrLevels.add(19.94);
		snrLevels.add(17.03);
		snrLevels.add(17.03);
		snrLevels.add(10.0);
		snrLevels.add(10.0);
		snrLevels.add(2.968);
		snrLevels.add(2.968);
		snrLevels.add(0.552);
		return snrLevels;
	}
	
	/**
	 * Gets the random snr levels example.
	 *
	 * @param size the size
	 * @param maxValue the max value
	 * @return the random snr levels example
	 */
	public static ArrayList<Double> getRandomSnrLevelsExample(int size, double maxValue) {
		ArrayList<Double> snrLevels = new ArrayList<Double>();
		for (int i=0; i<size; i++) {
			double snr = Math.max(0.1*maxValue, Math.random()*maxValue);
			snrLevels.add(snr);
		}
		return snrLevels;
	}

	/**
	 * Gets the random connected snr levels example.
	 *
	 * @param size the size
	 * @param maxValue the max value
	 * @param maxVariation the max variation
	 * @return the random connected snr levels example
	 */
	public static ArrayList<Double> getRandomConnectedSnrLevelsExample(int size, double maxValue, double maxVariation) {
		ArrayList<Double> snrLevels = new ArrayList<Double>();
		double prevValue = Math.random() * maxValue;
		for (int i=0; i<size; i++) {
			double snrVariation = (Math.random() - Math.random()) * maxVariation;
			double snr = prevValue + snrVariation;
			snr = Math.max(0, Math.min(snr, maxValue));
			snrLevels.add(snr);
			prevValue = snr;
		}
		return snrLevels;
	}
}
