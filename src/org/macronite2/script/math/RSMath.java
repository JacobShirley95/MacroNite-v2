package org.macronite2.script.math;

import java.awt.Point;

import org.macronite2.script.RuneScape;
import org.macronite2.hooks.MapBase;
import org.macronite2.hooks.Renderer;
import org.macronite2.hooks.SoftwareRenderer;
public class RSMath {
	public static Point getScreenPosHidden(float x, float y, float z) {
		Renderer renderer = RuneScape.getRenderer();
		float[] out = new float[3];
		renderer.toScreen(x, y, z, out);
		return new Point((int)out[0], (int)out[1]);
	}
	
	public static Point localToGlobal(int x, int y) {
		MapBase base = RuneScape.getMapBase();
		return new Point(base.getX()+x, base.getY()+y);
	}
}
