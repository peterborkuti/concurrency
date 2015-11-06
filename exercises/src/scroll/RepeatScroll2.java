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

public class RepeatScroll2 {
	volatile Screen screen;
	volatile TextGraphics tGraphics;
	volatile boolean stop = false;

	static String[] messages = {
		"Hello",
		"Adios",
		"Hola",
		"Üdv"
	};
	

	static char[] fillers = {'-', '*', '=', ':'};

	class Scroller implements Runnable {
		private final Scroll scroll;
		private final int x,y;
		private boolean right = true;
		private boolean horizontal = true;

		public Scroller(int x, int y, boolean right, boolean horiz, int length, char filler, String text) {
			scroll = new Scroll(length, filler, text);
			this.x = x;
			this.y = y;
			this.right = right;
			this.horizontal = horiz;
		}

		private void putVertical(int x, int y, String s) {
			char[] chars = s.toCharArray();
			for (int i = 0; (i < chars.length) && (i + y) < tGraphics.getSize().getRows(); i++) {
				tGraphics.putString(x, y + i, String.valueOf(chars[i]));
			}
		}

		@Override
		public void run() {
			String s = (right) ? scroll.scrollToRight() : scroll.scrollToLeft();
			if (horizontal) {
				tGraphics.putString(x, y, s);
			}
			else {
				putVertical(x, y, s);
			}
			try {
				screen.refresh(Screen.RefreshType.DELTA);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public RepeatScroll2() throws IOException {
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		screen = new TerminalScreen(terminal);
		tGraphics = screen.newTextGraphics();

		screen.startScreen();
		screen.clear();
	}

	public static void main(String[] args) throws IOException {
		RepeatScroll2 app = new RepeatScroll2();

		ScheduledExecutorService executor =
			Executors.newScheduledThreadPool(5);

		Random r = new Random();

		for (int i = 0; i < messages.length; i++) {
			int y = 2 * i + 10;
			int l = r.nextInt(20) + 10;
			int x = r.nextInt(60 - l) + 10;
			int delay = 100 + r.nextInt(5) * 100;
			char filler = fillers[r.nextInt(fillers.length)];
			boolean right = r.nextBoolean();
			boolean horiz = r.nextBoolean();
			Scroller scroller = app.new Scroller(x, y, right, horiz, l, filler, messages[i]);
			executor.scheduleAtFixedRate(scroller, 1000, delay, TimeUnit.MILLISECONDS);
		}

		Util.log("Waiting 20 seconds");
		Util.sleep(20, 0, TimeUnit.SECONDS);

		Util.log("Shutdown");
		Util.shutdownAndAwaitTermination(executor);

		app.screen.stopScreen();
	}

}
