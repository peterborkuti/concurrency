package adder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.Util;

public class Main {

	public static void main(String[] args) {
		int NumOfThreads = 1;
		ExecutorService executor = Executors.newFixedThreadPool(1);

		List<Integer> list = new ArrayList<Integer>();

		//Random r = new Random();

		for (int i = 0; i < 1000; i++) {
			list.add(i);
		}

		List<Callable<Long>> callables = new ArrayList<Callable<Long>>();

		int div = list.size() / NumOfThreads;

		for (int i = 0; i < list.size(); i += div) {
			callables.add(new Adder(list.subList(i,  i + div)));
		}

		List<Future<Long>> futures = null;
		try {
			futures = executor.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long sum = 0;
		for (Future<Long> future: futures) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Util.log("Sum:" + sum);

		Util.shutdownAndAwaitTermination(executor);
	}

}
