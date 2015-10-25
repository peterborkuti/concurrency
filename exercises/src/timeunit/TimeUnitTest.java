package timeunit;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Sleep for 2 seconds");
		TimeUnit.SECONDS.sleep(2);
	
		System.out.println("3 minutes in seconds = " + TimeUnit.MINUTES.toSeconds(3));

		//Using TimeUnit with other class
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		//Waiting up to 3 seconds
		System.out.println("Waiting for 3 seconds");
		queue.poll(3, TimeUnit.SECONDS);
	}

}
