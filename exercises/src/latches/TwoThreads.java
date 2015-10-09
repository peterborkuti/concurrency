package latches;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.NamedSleeper;
import common.Util;

public class TwoThreads {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(8);

		CountDownLatch latch = new CountDownLatch(1);

		List<Future<Object>> futures = new ArrayList<Future<Object>>();

		executor.submit(new NamedSleeper("Péter", latch), null);
		Future<Object> futureOfWaiter = executor.submit(new Waiter(latch), null);

		futureOfWaiter.get();

		Util.shutdownAndAwaitTermination(executor);

	}

}
