package hashcache;

import java.util.HashMap;
import java.util.Map;

public class HashCachedThinker extends SlowThinker {

	private final Map<Integer, Integer> cache =
			new HashMap<Integer, Integer>();

	@Override
	public Integer call() throws Exception {
		Integer value = cache.get(input);
		if (value == null) {
			value = super.call();
		}

		return value;
	}

}
