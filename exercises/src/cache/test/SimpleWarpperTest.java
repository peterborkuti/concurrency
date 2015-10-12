package cache.test;

import common.Util;

public class SimpleWarpperTest {

	private static void oneThread() {
		Util.log("SimpleWrapperTest - one thread");
		SlowThinkerSimpleWrapper thinker = new SlowThinkerSimpleWrapper();
		Util.log("10x 0..9");
		long timer = Util.startTimer();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Util.log("doProcess(" + j + ")");
				Util.log("got" + thinker.doProcess(j));
			}
		}
		Util.stopTimer(timer);

	}

	public static void main(String[] args) {
		oneThread();
	}

}
