package hu.bp.concurrency.future.modules;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable01 implements Callable<String> {

	@Override
	public String call() throws Exception {
		Random r = new Random();
		int millis = 1000 + r.nextInt(5000);
		Thread.sleep(millis);
		return "Millis:" + millis;
	}

}
