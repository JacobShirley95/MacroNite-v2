package org.macronite2.script.screen;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.macronite2.rsapplet.Rs2Canvas;
import org.macronite2.script.RuneScape;

public class RSInput {
	public static final int MOUSE_LEFT = MouseEvent.BUTTON1;
	public static final int MOUSE_MIDDLE = MouseEvent.BUTTON2;
	public static final int MOUSE_RIGHT = MouseEvent.BUTTON3;
	public static final int MOUSE_MOVE = MouseEvent.NOBUTTON;
	
	public static final void clickMouse(int x, int y, int button) {
		moveMouse(x, y);
		MouseEvent event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x, y, 1, false, button);
		RuneScape.getMouseHandler().mousePressed(event);
		try {
			Thread.currentThread().sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, x, y, 1, false, button);
		RuneScape.getMouseHandler().mouseReleased(event);
	}
	
	public static final void moveMouse(int x, int y) {
		MouseEvent event = new MouseEvent(Rs2Canvas.instance, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 1, false);
		RuneScape.getMouseHandler().mouseMoved(event);
	}
	
	public static final void mouse(Point p, int button) {
		mouse(p.x, p.y, button);
	}
	
	public static final void mouse(int x, int y, int button) {
		if (button == MouseEvent.NOBUTTON)
			moveMouse(x, y);
		else
			clickMouse(x, y, button);
	}
	
	public static final void typeKey(int vkEnter) {
		KeyEvent event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, vkEnter, (char)vkEnter);
		RuneScape.getClient().getKeyboardHandler().keyPressed(event);
		try {
			Thread.currentThread().sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event = new KeyEvent(Rs2Canvas.instance, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, vkEnter, (char)vkEnter);
		RuneScape.getClient().getKeyboardHandler().keyPressed(event);
	}
	
	public static final void typeKeys(String message) {
		for (int i = 0; i < message.length(); i++)
			typeKey(message.codePointAt(i));
	}
}
