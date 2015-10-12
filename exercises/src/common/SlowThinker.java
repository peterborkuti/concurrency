package common;

import cache.interfaces.Cacheable;

public class SlowThinker implements Cacheable<Integer, Integer> {
	private String[] messages = {
		"start to think",
		"hard problem...",
		"almost done...",
		"done."
	};

	@Override
	public Integer doProcess(Integer input) {
		Util.writeAndSleep("SlowThinker", messages, 100, 200);

		return input;
	}

}
