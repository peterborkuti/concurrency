package interruptionold;

import common.Util;

public class PrintAndSleep01 {
	public static void print(String[] messages) {
		for (String m: messages) {
			Util.log(m);
			try {
				Sleep.Sleep3Secs();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
