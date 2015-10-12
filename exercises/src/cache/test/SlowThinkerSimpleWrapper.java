package cache.test;

import common.SlowThinker;
import common.Util;
import cache.impl.SimpleCache;
import cache.interfaces.Cache;
import cache.interfaces.Cacheable;

public class SlowThinkerSimpleWrapper implements Cacheable<Integer, Integer> {

	private Cache<Integer, Integer> cache = new SimpleCache<Integer, Integer>();
	private SlowThinker thinker = new SlowThinker();

	@Override
	public Integer doProcess(Integer input) {
		if (cache.stored(input)) {
			return cache.get(input); 
		}

		Integer thought = thinker.doProcess(input);
		cache.store(input, thought);
		return thought;
	}
}
