package interruption;

public class Printer01 implements Runnable {

	@Override
	public void run() {
		while (true) {
			PrintAndSleep01.print(new String[]{"A", "B", "C"});
		}

	}

}
