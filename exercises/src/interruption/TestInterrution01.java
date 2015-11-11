package interruption;

import java.util.concurrent.TimeUnit;

import common.Util;

public class TestInterrution01 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Sleeper03());

		Util.log("Start sleeper");
		t.start();

		TimeUnit.SECONDS.sleep(10);

		Util.log("Try to interrupt sleeper");
		t.interrupt();

		TimeUnit.SECONDS.sleep(10);

		Util.log("Main exits");
	}

}
