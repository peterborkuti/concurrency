package executor02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.Thinker02;
import common.Util;


public class Main04 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		Future<Integer> future = executor.submit(new Thinker02());

		Integer output = future.get();

		System.out.println("Output:" + output);

		Util.shutdownAndAwaitTermination(executor);
	}

}
