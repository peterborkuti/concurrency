package common;

import java.util.concurrent.CountDownLatch;

import common.Util;

public class NamedSleeperWithLatch extends NamedSleeper {
	private CountDownLatch latch;

	public NamedSleeperWithLatch(String name, CountDownLatch latch) {
		super(name);
		this.latch = latch;
	}

	@Override
	public void run() {
		Util.writeAndSleep(name, messages, 1000, 2000);
		latch.countDown();
	}

}