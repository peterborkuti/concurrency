package hu.bp.conway.modules;

public class Main {

	static long timer; 
	public static void main(String[] args) {
		Universe in = new Universe(200, 0.2f);

		Coordinator c;

		for (int i = 1; i < 32; i++) {
			c = new Coordinator(in, i);
			startTimer();
			c.run();
			stopTimer();
		}
		/*
		c = new Coordinator(in, 4);
		startTimer();
		c.run();
		stopTimer();
		*/
	}

	public static void startTimer() {
		timer = System.nanoTime();
	}

	public static void stopTimer() {
		System.out.println(System.nanoTime() - timer);
	}
	

}
