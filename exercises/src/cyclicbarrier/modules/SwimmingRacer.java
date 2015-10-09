package cyclicbarrier.modules;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import common.Util;

public class SwimmingRacer implements Runnable {

	private final static String[] messages1 = {
		"Going to the pool",
		"Waiting for empty line",
	};

	private final static String[] messages2 = {
		"Start smimming",
		"getting out from the pool",
		"go to the sauna"
	};

	private final CyclicBarrier barrier;
	private final String name;

	public SwimmingRacer(String name, CyclicBarrier barrier) {
		this.barrier = barrier;
		this.name = name;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {

			Util.writeAndSleep(name, messages1, 1000, 2000);

			try {
				barrier.await();
			} catch (InterruptedException e) {
				return;
			} catch (BrokenBarrierException e) {
				return;
			}

			Util.writeAndSleep(name, messages2, 1000, 2000);

		}
	}

}
