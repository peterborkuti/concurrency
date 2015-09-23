package hu.bp.conway.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Coordinator implements Runnable {
	private Universe in, out;
	private ExecutorService executor;
	private List<FutureTask<?>> f;
	private int numOfThreads;

	public Coordinator(Universe in, int numOfThreads) {
		this.in = new Universe(in);
		out = new Universe(in.n, false);
		executor = Executors.newFixedThreadPool(numOfThreads);
		f = new ArrayList<FutureTask<?>>();
		this.numOfThreads = numOfThreads;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10000; i ++) {
			f.clear();
			int len = in.n / numOfThreads;
			for (int c = 0; c < in.n - 1; c += len) {
				f.add(new FutureTask<Void>(
					new Walker(in, out, 1, c, len), null));
			}

			for (FutureTask<?> ff: f) {
				executor.execute(ff);
			}

			for (FutureTask<?> ff: f) {
				try {
					ff.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

			//System.out.println(i);
			//print(out);
	
			if (in.equals(out)) {
				break;
			}

			Universe tmp;
			tmp = in;
			in = out;
			out = tmp;
		}
	}

	private void print(Universe u) {
		System.out.println();
		System.out.println(u.toString().replaceAll(",", "\n").replaceAll(" ", "-"));
	}

}
