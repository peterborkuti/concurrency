package common;

import java.util.concurrent.CountDownLatch;

import common.Util;

public class NamedSleeper extends Sleeper {


	private String name;
	private CountDownLatch latch;

	public NamedSleeper(String name) {
		this.name = name;
	}

	public NamedSleeper(String string, CountDownLatch latch) {
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		Util.writeAndSleep(name, messages, 1000, 2000);
		latch.countDown();
	}
	
}