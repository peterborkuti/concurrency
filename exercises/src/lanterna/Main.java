package lanterna;

import java.io.IOException;
import java.util.Random;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Test class for lanterna.
 * Lanterna is a cross-platform console framework,
 * so you can write texts to a given coordinate on the 
 * console.
 * 
 * You have to have lanterna*.jar in your classpath.
 * Check the lib directory of this project.
 * 
 * Run this class to see if it works for you.
 * It writes random characters to the console at random
 * coordinates.
 * Press any key to stop it.
 * 
 * @author Peter Borkuti
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(terminal);
		TextGraphics tGraphics = screen.newTextGraphics();

		screen.startScreen();
		screen.clear();

		tGraphics.putString(10, 10, "Press any key to stop it");

		Random r = new Random();

		while (screen.pollInput() == null) {
			TerminalSize size = screen.getTerminalSize();

			screen.setCharacter(
				r.nextInt(size.getColumns()), r.nextInt(size.getColumns()),
				new TextCharacter(Character.toChars(33 + r.nextInt(128-33))[0]));

			screen.refresh(Screen.RefreshType.DELTA);
		}

		screen.stopScreen();
	}
}
