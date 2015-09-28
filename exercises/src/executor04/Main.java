package executor04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.Thinker02;
import common.Util;


public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();

		for (int i = 0; i < 10; i++) {
			callables.add(new Thinker02());
		}

		List<Future<Integer>> futures = executor.invokeAll(callables);

		Integer sum = 0;
		for (Future<Integer> future: futures) {
			sum += future.get();
		}

		Util.log("Sum:" + sum);

		Util.shutdownAndAwaitTermination(executor);
	}

}
