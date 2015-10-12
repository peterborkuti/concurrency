package cache.interfaces;

import java.util.concurrent.ExecutionException;

public interface Cacheable<O,I> {
	public O doProcess(I input) throws InterruptedException, ExecutionException;
}
