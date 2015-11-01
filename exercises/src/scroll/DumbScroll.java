package scroll;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DumbScroll {

	public static void main(String[] args) throws IOException, InterruptedException {
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		TerminalScreen screen = new TerminalScreen(terminal);
		TextGraphics tGraphics = screen.newTextGraphics();

		screen.startScreen();
		screen.clear();

		Scroll scroll = new Scroll(30, '*', "Hello");

		while (screen.pollInput() == null) {
			tGraphics.putString(10, 10, scroll.scrollToRight());
			screen.refresh(Screen.RefreshType.DELTA);
			TimeUnit.MILLISECONDS.sleep(100);
		}

		screen.stopScreen();
	}

}
