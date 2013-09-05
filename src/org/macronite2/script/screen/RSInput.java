package org.macronite2.script.screen;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.macronite2.hooks.KeyboardHandler;
import org.macronite2.hooks.MouseHandler;
import org.macronite2.rsapplet.Rs2Canvas;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.Utils;

public class RSInput {
	private static final String SHIFT_KEYS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ¬!\"£$%^&*()_+{}:@~<>?|€";
	private static final int DEFAULT_MOUSE_TIME = 100;
	private static final int DEFAULT_KEY_TIME = 200;
	
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
		context.sleep(DEFAULT_MOUSE_TIME+Utils.random(20));
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
	
	public final void type(int c) {
		int mods = 0;
		if (SHIFT_KEYS.contains(""+(char)c)) {
			type(KeyEvent.VK_SHIFT, DEFAULT_KEY_TIME, InputEvent.SHIFT_DOWN_MASK);
			mods |= InputEvent.SHIFT_DOWN_MASK;
		}
		type(c, DEFAULT_KEY_TIME, mods);
	}
	
	public final void type(String message) {
		for (char c : message.toCharArray())
			type(c);
	}
	
	public void type(int c, int time) {
		type(c, time, 0);
	}
	
	public void type(int c, int time, int mods) {
		KeyboardHandler handler = context.runescape.getKeyboardHandler();
		int key = KeyEvent.getExtendedKeyCodeForChar(c);

		KeyEvent event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, (char)c, KeyEvent.KEY_LOCATION_STANDARD);
		handler.keyPressed(event);
		
		event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_TYPED, System.currentTimeMillis(), mods, KeyEvent.VK_UNDEFINED, (char)c, KeyEvent.KEY_LOCATION_UNKNOWN);
		handler.keyTyped(event);
	
		context.sleep(time+Utils.random(20));
	
		event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), mods, key, (char)c, KeyEvent.KEY_LOCATION_STANDARD);
		handler.keyReleased(event);
	}
	
	public void pressKey(int c) {
		KeyboardHandler handler = context.runescape.getKeyboardHandler();

		KeyEvent event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, c, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		handler.keyPressed(event);
	}
	
	public void releaseKey(int c) {
		KeyboardHandler handler = context.runescape.getKeyboardHandler();
	
		KeyEvent event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, c, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
		handler.keyReleased(event);
	}
}
