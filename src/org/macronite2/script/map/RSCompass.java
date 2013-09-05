package org.macronite2.script.map;

import java.awt.event.KeyEvent;

import org.macronite2.hooks.Client;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.Utils;

public class RSCompass {
	public static int[] SINE_ARRAY = new int[16384];
	public static int[] COS_ARRAY = new int[16384];
	
	static {
		double var0 = 3.834951969714103E-4D;

		for (int var2 = 0; var2 < 16384; ++var2) {
			SINE_ARRAY[var2] = (int) (16384.0D * Math.sin(var2 * var0));
			COS_ARRAY[var2] = (int) (16384.0D * Math.cos(var2 * var0));
		}
	}

	private ScriptContext context;
	
	public RSCompass(ScriptContext context) {
		this.context = context;
	}
	
	public int getCompassAngle() {
		Client cl = context.runescape;
		return (int) (cl.getCameraAngle() * 2607.5945876176133D)
				+ cl.getCameraOrigin() & 16383;
	}

	public double getCompassAngleDegrees() {
		return ((16383.0 - getCompassAngle()) / 16383.0) * 360.0;
	}
	
	public double getCompassAngleRadians() {
		return ((16383.0 - getCompassAngle()) / 16383.0) * (2 * Math.PI);
	}
	
	public void setCompass(int angle) {
		int turn = (int)getCompassAngleDegrees() - angle;
		int key = turn < 0 ? KeyEvent.VK_LEFT : KeyEvent.VK_RIGHT;
		
		context.input.pressKey(key);
		while (!Utils.inRange((int)getCompassAngleDegrees(), angle-10, angle+10)) {
			context.sleep(10);
		}
		context.input.releaseKey(key);
	}
}
