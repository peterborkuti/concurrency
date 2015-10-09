package common;

import java.util.concurrent.CountDownLatch;

import common.Util;

public class WaitingNamedSleeper extends Sleeper {


	private String name;
	private CountDownLatch latch;

	public WaitingNamedSleeper(String name) {
		this.name = name;
	}

	public WaitingNamedSleeper(String name, CountDownLatch latch) {
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		Util.log(name + "sleeper is started");
		latch.countDown();
		Util.log(name + "sleeper is waiting for the others");
		try {
			latch.await();
			Util.writeAndSleep(name, messages, 1000, 2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}