package latches;

import java.util.concurrent.CountDownLatch;

import common.Util;

public class Waiter implements Runnable {

	private CountDownLatch latch;

	public Waiter(CountDownLatch latch){
		this.latch = latch;
	}

	@Override
	public void run() {
		Util.log("Waiter: I am waiting for the others to finish");

		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Util.log("Waiter: got it");
	}

}
