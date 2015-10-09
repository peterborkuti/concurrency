package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Util;

import cyclicbarrier.modules.SwimmingRacer;

public class SwimmingRace {

	public static final int LANES = 4;
	public static final int SWIMMERS = 16;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(LANES);
		ExecutorService executor = Executors.newFixedThreadPool(8);

		for (int i = 1; i <= SWIMMERS; i++) {
			executor.submit(
				new SwimmingRacer("swimmer-" + i, barrier), null);
		}

		Util.sleep(30000, 1000);

		Util.shutdownAndAwaitTermination(executor);
	}

}
