package model.data;

import java.util.HashMap;

public class Data {
	
	public static HashMap<Integer, Double> snrOverRateFor1EMinus2Ber() {
		HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
		
		hm.put(1, 0.13529736077635854);
		hm.put(2, 2.7059472155271709);
		hm.put(3, 5.4118944310543409);
		hm.put(4, 13.846275095095123);
    	hm.put(5, 24.561319845656509);
    	hm.put(6, 51.913490602253852);
    	hm.put(7, 94.088380640006577);
    	hm.put(8, 188.17908089902);
    	hm.put(9, 349.22763204909018);
    	hm.put(10, 684.50826338639206);
    	hm.put(11, 1291.8007857523794);
    	
		return hm;
	}

}
