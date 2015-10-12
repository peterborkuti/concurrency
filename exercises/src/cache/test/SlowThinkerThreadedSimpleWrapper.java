package cache.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cache.impl.SimpleCache;
import cache.interfaces.Cache;
import cache.interfaces.Cacheable;

import common.SlowThinker;

class CallableSlowThinker extends SlowThinker implements Callable<Integer> {
	private final Integer input;
	public CallableSlowThinker(Integer input) {
		this.input = input;
	}
	@Override
	public Integer call() throws Exception {
		return doProcess(input);
	}

}

public class SlowThinkerThreadedSimpleWrapper implements Cacheable<Integer, Integer> {

	private Cache<Integer, Integer> cache = new SimpleCache<Integer, Integer>();
	private ExecutorService executor = Executors.newFixedThreadPool(8);

	@Override
	public Integer doProcess(Integer input) throws InterruptedException, ExecutionException {
		if (cache.stored(input)) {
			return cache.get(input); 
		}

		Callable<Integer> thinker = new CallableSlowThinker(input);
		Future<Integer> futureThought = executor.submit(thinker);
		Integer thought = futureThought.get();
		cache.store(input, thought);

		return thought;
	}
}
