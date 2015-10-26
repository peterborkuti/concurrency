package shutdown;

import java.util.concurrent.TimeUnit;

import common.Util;

class ShutdownHook extends Thread {

	@Override
	public void run() {
		Util.log("ShutdownHook activated");
	}

}

/**
 * ShutdownHook is not activated when clicking on Eclipse's
 * Terminate button, because Terminate button does not terminate
 * gracefully JVM.
 * Try it stand alone and CTRL+C on windows/Linux
 * 
 * peter@debian:~/work/concurrency/exercises/bin (development)$ java -cp . shutdown.LogWhenJVMShutdown
 *
 * @author Peter Borkuti
 *
 */
public class LogWhenJVMShutdown {

	public static void main(String[] args) throws InterruptedException {
		Util.log("app starts");
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		Util.log("ShutdownHook registered");
		TimeUnit.SECONDS.sleep(60);
		Util.log("app exits");
	}

}
