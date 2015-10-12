package cache.impl;

import java.util.HashMap;
import java.util.Map;

import cache.interfaces.Cache;

public class SimpleCache<CACHEABLETYPE, KEY> implements Cache<CACHEABLETYPE, KEY> {

	Map<KEY, CACHEABLETYPE> cache = new HashMap<KEY, CACHEABLETYPE>();

	@Override
	public void store(KEY key, CACHEABLETYPE obj) {
		cache.put(key, obj);
	}

	@Override
	public boolean stored(KEY key) {
		return cache.containsKey(key);
	}

	@Override
	public CACHEABLETYPE get(KEY key) {
		return cache.get(key);
	}

}
