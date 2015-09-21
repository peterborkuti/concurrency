package hu.bp.concurrency.future.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import hu.bp.concurrency.future.modules.MyCallable01;

public class TestFuture02 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<FutureTask<String>> futureList = new ArrayList<FutureTask<String>>();

		for (int i = 0; i < 10; i++) {
			FutureTask<String> future = new FutureTask<String>(new MyCallable01());
			futureList.add(future);
			Thread thread = new Thread(future);
			thread.start();
		}

		System.out.println("Start getting the future");

		for (FutureTask<String> future: futureList) {
			System.out.print("Get the future...");
			String output = future.get();
			System.out.println("Got the future:" + output);
		}

		System.out.println("Done getting the future");
	}

}
