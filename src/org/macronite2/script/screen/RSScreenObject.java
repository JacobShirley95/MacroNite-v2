package org.macronite2.script.screen;

import java.awt.Point;

public interface RSScreenObject {
	public Point toScreen();
	public void mouse(int button);
}
