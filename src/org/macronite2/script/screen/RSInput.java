package org.macronite2.script.screen;

import java.awt.AWTKeyStroke;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.KeyStroke;

import org.macronite2.hooks.KeyboardHandler;
import org.macronite2.hooks.MouseHandler;
import org.macronite2.rsapplet.Rs2Canvas;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.utils.Utils;

public class RSInput {
	public static final int MOUSE_LEFT = MouseEvent.BUTTON1;
	public static final int MOUSE_MIDDLE = MouseEvent.BUTTON2;
	public static final int MOUSE_RIGHT = MouseEvent.BUTTON3;
	public static final int MOUSE_MOVE = MouseEvent.NOBUTTON;
	private ScriptContext context;
	
	public RSInput(ScriptContext context) {
		this.context = context;
	}
	
	public final void clickMouse(int x, int y, int button) {
		moveMouse(x, y);
		
		MouseHandler handler = context.runescape.getMouseHandler();
		MouseEvent event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x, y, 1, false, button);
		handler.mousePressed(event);
		context.sleep(50+Utils.random(20));
		event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, x, y, 1, false, button);
		handler.mouseReleased(event);
	}
	
	public final void moveMouse(int x, int y) {
		MouseEvent event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 1, false);
		context.runescape.getMouseHandler().mouseMoved(event);
	}
	
	public final void mouse(Point p, int button) {
		mouse(p.x, p.y, button);
	}
	
	public final void mouse(int x, int y, int button) {
		if (button == MouseEvent.NOBUTTON)
			moveMouse(x, y);
		else
			clickMouse(x, y, button);
	}
	
	public final void typeKey(int vkC) {
		KeyboardHandler handler = context.runescape.getKeyboardHandler();
		int key2 = vkC;
			KeyEvent event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key2, (char)0, KeyEvent.KEY_LOCATION_STANDARD);
			handler.keyPressed(event);
			context.sleep(50+Utils.random(20));
			event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key2, (char)0, KeyEvent.KEY_LOCATION_STANDARD);
			handler.keyReleased(event);
	}
	
	public final void typeKeys(String message) {
		for (int i = 0; i < message.length(); i++)
			typeKey(KeyEvent.getExtendedKeyCodeForChar(message.charAt(i)));
	}
}
