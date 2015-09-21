package hu.bp.concurrency.future.main;

import hu.bp.concurrency.future.modules.MyCallable01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture01 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> future = new FutureTask<String>(new MyCallable01());
		Thread thread = new Thread(future);
		thread.start();

		System.out.print("Get the future...");
		String output = future.get();
		System.out.println("Got the future:" + output);
	}

}
