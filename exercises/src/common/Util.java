package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {
	public static final SimpleDateFormat format = new SimpleDateFormat(
			"HH:mm:ss.SSS");

	/**
	 * Gets the current thread to sleep for at least *fix* milliseconds
	 * and an added random millisecond between 0 and *rand*
	 * 
	 * If InerruptedException happens during sleeping, it stops sleeping
	 * and sets interrupt flag again to true to give a chance to the caller
	 * functions to check it and stop
	 * 
	 * @param fix milliseconds to sleep
	 * @param rand added random milliseconds between 0 and rand
	 */
	public static void sleep(int fix, int rand) {
		Random r = new Random();
		try {
			Thread.sleep(fix + r.nextInt(rand));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Prints the time, thread name a message to the console
	 * @param s
	 */
	public static void log(String s) {
		String time = format.format(new Date());
		String thread = Thread.currentThread().getName();
		System.out.println(time + " - " + thread + " - " + s);
	}

	/**
	 * Writes the strings in sArr and sleeps between prints
	 * @param sArr
	 * @param fix
	 * @param rand
	 */
	public static void writeAndSleep(String[] sArr, int fix, int rand) {
		log(sArr[0]);

		for (int i = 1; i < sArr.length; i++) {
			sleep(fix, rand);
			if (Thread.currentThread().isInterrupted()) {
				return;
			}
			log(sArr[i]);
		}
	}

	/**
	 * Effectively close the executor so no new runnable is accepted and
	 * tries to terminate all the running thread.
	 * Threads must catch InterruptedException and check the interrupted
	 * flag and stop if it's thread was interrupted.
	 * 
	 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
	 * 
	 * @param pool
	 */
	public static void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown(); // Disable new tasks from being submitted
		pool.shutdownNow(); // Cancel currently executing tasks
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(2, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}
}
