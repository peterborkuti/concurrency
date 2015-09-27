package executor05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import common.Sleeper;
import common.Util;

public class Main {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(8);

		List<Future<Object>> futures = new ArrayList<Future<Object>>();

		for (int i = 0; i < 10; i++) {
			FutureTask<Object> f = new FutureTask<Object>(new Sleeper(), null);
			futures.add(f);
			executor.execute(f);
		}

		Util.sleep(5000, 1000);

		Util.shutdownAndAwaitTermination(executor);
	}

}
