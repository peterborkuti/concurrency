package hu.bp.concurrency.future.main;

import hu.bp.concurrency.future.modules.MyCallable02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture03 {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		FutureTask<String> future = new FutureTask<String>(new MyCallable02());
		Thread thread = new Thread(future);
		thread.start();

		System.out.println("Get the future...");
		String output = "";
		try {
			output = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		}
		System.out.println("Got the future:" + output);
	}

}
