package model;

/**
 * The Class Converter.
 */
public class Converter {
	
	/**
	 * Gets the snr.
	 *
	 * @param snrInDb the snr in db
	 * @return the snr
	 */
	public static double getSNR(double snrInDb) {
		return Math.pow(10, snrInDb/10);
	}
	
	/**
	 * Gets the SNR in db.
	 *
	 * @param snr the snr
	 * @return the SNR in db
	 */
	public static double getSNRInDb(double snr) {
		return 10 * Math.log10(snr);
	}

}
