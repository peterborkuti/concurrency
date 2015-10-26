package timeunit;

import common.Util;

public class IAmRunning implements Runnable {
	private final String name;

	public IAmRunning(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		Util.log("I am running");
	}

}
