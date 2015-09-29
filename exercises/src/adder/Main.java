package adder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import common.Util;

public class Main {

	static final int NUM_OF_THREADS = 2;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREADS);

		List<Integer> list = new ArrayList<Integer>();

		Random r = new Random();

		long sumTest = 0;
		for (int i = 0; i < 10000000; i++) {
			int n = i; //r.nextInt();
			list.add(n);
			sumTest += n;
		}

		long timer = Util.startTimer();

		List<Callable<Long>> callables = new ArrayList<Callable<Long>>();

		int div = list.size() / NUM_OF_THREADS;

		for (int i = 0; i < list.size(); i += div) {
			callables.add(new Adder(list.subList(i,  i + div)));
		}

		List<Future<Long>> futures = null;
		futures = executor.invokeAll(callables);

		long sum = 0;
		for (Future<Long> future: futures) {
			sum += future.get();
		}

		Util.stopTimer(timer);

		Util.log("Test:" + sumTest + ", Sum:" + sum);

		Util.shutdownAndAwaitTermination(executor);
	}

}
