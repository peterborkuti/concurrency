package lanterna;

import java.io.IOException;
import java.util.Random;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(terminal);

		screen.startScreen();
		screen.clear();
		screen.refresh();

		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			TerminalSize size = screen.getTerminalSize();
			screen.setCharacter(
				r.nextInt(size.getColumns()), r.nextInt(size.getColumns()),
				new TextCharacter(Character.toChars(33 + r.nextInt(128-33))[0]));
			screen.refresh();
		}
		screen.stopScreen();
	}
}
