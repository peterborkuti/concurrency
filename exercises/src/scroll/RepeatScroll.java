package scroll;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import common.Util;

public class RepeatScroll {
	volatile Screen screen;
	volatile TextGraphics tGraphics;
	volatile boolean stop = false;

	static String[] messages = {
		"What the world needs now is love, sweet love",
		"It's the only thing that there's just too little of",
		"What the world needs now is love, sweet love",
		"No, not just for some but for everyone"
	};

	class Scroller implements Runnable {
		private final Scroll scroll;
		private final int x,y;
		private boolean right = true;

		public Scroller(int x, int y, boolean right, int length, char filler, String text) {
			scroll = new Scroll(length, filler, text);
			this.x = x;
			this.y = y;
			this.right = right;
		}

		@Override
		public void run() {
			if (right) {
				tGraphics.putString(x, y, scroll.scrollToRight());
			}
			else {
				tGraphics.putString(x, y, scroll.scrollToLeft());
			}
			try {
				screen.refresh(Screen.RefreshType.DELTA);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public RepeatScroll() throws IOException {
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		screen = new TerminalScreen(terminal);
		tGraphics = screen.newTextGraphics();

		screen.startScreen();
		screen.clear();
	}

	public static void main(String[] args) throws IOException {
		RepeatScroll app = new RepeatScroll();

		ScheduledExecutorService executor =
			Executors.newScheduledThreadPool(5);

		Random r = new Random();

		for (int i = 0; i < messages.length; i++) {
			int y = 2 * i + 10;
			int x = 10;
			int delay = 100 + r.nextInt(5) * 100;
			boolean right = r.nextBoolean();
			Scroller scroller = app.new Scroller(x, y, right, 50, '*', messages[i]);
			executor.scheduleAtFixedRate(scroller, 1000, delay, TimeUnit.MILLISECONDS);
		}

		Util.log("Waiting 20 seconds");
		Util.sleep(20, 0, TimeUnit.SECONDS);

		Util.log("Shutdown");
		Util.shutdownAndAwaitTermination(executor);

		app.screen.stopScreen();
	}

}
