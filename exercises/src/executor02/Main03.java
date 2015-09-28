package executor02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.Thinker01;
import common.Util;


public class Main03 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		Future<?> future = executor.submit(new Thinker01(), null);

		future.get();

		Util.shutdownAndAwaitTermination(executor);
	}

}
