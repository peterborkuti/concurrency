package hu.bp.conway.modules;

public class Main {

	static long timer; 
	public static void main(String[] args) {
		Universe in = new Universe(100, 0.2f);

		Coordinator c;

		for (int i = 0; i < 5; i++) {
			c = new Coordinator(in, 1);
			startTimer();
			c.run();
			stopTimer();
	
			c = new Coordinator(in, 2);
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
