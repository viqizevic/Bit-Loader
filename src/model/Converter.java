package model;

/**
 * The Class Converter.
 */
public class Converter {
	
	/**
	 * Gets the value.
	 *
	 * @param valueInDb the value in db
	 * @return the value
	 */
	public static double getValue(double valueInDb) {
		return Math.pow(10, valueInDb/10);
	}
	
	/**
	 * Gets the value in db.
	 *
	 * @param value the value
	 * @return the value in db
	 */
	public static double getValueInDb(double value) {
		return 10 * Math.log10(value);
	}
	
	/**
	 * Log2.
	 *
	 * @param value the value
	 * @return the double
	 */
	public static double log2(double value) {
		return Math.log(value) / Math.log(2);
	}

}
