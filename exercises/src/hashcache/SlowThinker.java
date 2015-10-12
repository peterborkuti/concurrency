package hashcache;

import java.util.concurrent.Callable;

import common.Util;

public class SlowThinker implements Callable<Integer> {
	private String[] messages = {
		"start to think",
		"hard problem...",
		"almost done...",
		"done."
	};
	protected Integer input = 0;

	public void setInput(Integer input) {
		this.input = input;
	}

	@Override
	public Integer call() throws Exception {
		Util.writeAndSleep("SlowThinker", messages, 100, 200);
		return input;
	}

}
