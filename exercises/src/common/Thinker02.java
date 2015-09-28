package common;

import java.util.Random;
import java.util.concurrent.Callable;

public class Thinker02 implements Callable<Integer> {
	int n = (new Random()).nextInt(10);
	String sArr[] = {
		"is woken up",
		"thinking",
		"got The number: " + n,
		"done"
	};

	@Override
	public Integer call() throws Exception {
		Util.writeAndSleep(sArr, 1000, 1000);
		return new Integer(n);
	}

}
