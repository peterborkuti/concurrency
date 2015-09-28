package common;

import java.util.Random;

public class Thinker01 implements Runnable {
	int n = (new Random()).nextInt(10);
	String sArr[] = {
		"is woken up",
		"thinking",
		"got The number: " + n,
		"done"
	};

	@Override
	public void run() {
		Util.writeAndSleep(sArr, 1000, 1000);
	}

}
