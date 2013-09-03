package org.macronite2.script.math;

import java.awt.Point;

import org.macronite2.script.ScriptContext;
import org.macronite2.hooks.MapBase;
import org.macronite2.hooks.Renderer;

public class RSMath {
	
	private ScriptContext context;

	public RSMath(ScriptContext context) {
		this.context = context;
	}
	
	public Point getScreenPosHidden(float x, float y, float z) {
		Renderer renderer = context.runescape.getCurrentRenderer();
		float[] out = new float[3];
		renderer.toScreen(x, y, z, out);
		return new Point((int)out[0], (int)out[1]);
	}
	
	public Point localToGlobal(int x, int y) {
		MapBase base = context.runescape.getMapBase();
		return new Point(base.getX()+x, base.getY()+y);
	}
}
