package hu.bp.concurrency.visibility;

/**
 * The following class demonstrates the visibility issue in multi-threaded Java
 * programs.
 * 
 * The main thread starts a new thread and then changes the stopperFlag variable
 * to false. The other thread monitors this flag and if it becomes false, then
 * it exits the while loop finishing its run() method.
 * 
 * Actually making a Thread and starting it is quite slow. So in reality if we
 * don't call Thread.sleep() the flag will already be set to false by the time
 * the new Thread is started. (I think it is a race condition of some kind.)
 * 
 * The best explanation I have found so far why this is happening can be found
 * here: http://jeremymanson.blogspot.com/2007/08/atomicity-visibility-and-ordering.html
 * 
 * According to this article, what happens is that the compiler (the JVM)
 * doesn't let the second thread see the variable stopperFlag, because it
 * detects that no writes are performed to that variable from that thread.
 * 
 * When you uncomment the stopperBlock this theory is fallen. :(
 * 
 * The strangest thing is that the compiler makes the variable visible to the
 * second thread when you uncomment the code in the while loop:
 * "System.out.println("Running...");"
 *
 * @author Ádám T. Nagy
 *
 */
public class NoVisibility implements Runnable{

	private static boolean stopperFlag = false;

	@Override
	public void run() {
		System.out.println("run() method started.");

		/*stopperBlock: {
			stopperFlag = true;
			stopperFlag = false;
		}*/

		while (!stopperFlag) {
			// When the next line is commented out the issue will be triggered.
			//System.out.println("Running...");
		}

		System.out.println("Finished run() method.");
		stopperFlag = true;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new NoVisibility());
		t.start();
		Thread.sleep(1000);
		stopperFlag = true;
		System.out.println("stopperFlag is: " + stopperFlag);
	}

}
