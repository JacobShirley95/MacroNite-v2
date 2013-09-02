package org.macronite2.script.map;

import java.awt.Point;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.MapBase;
import org.macronite2.script.RuneScape;
import org.macronite2.script.components.RSMinimapComponent;

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
	
	private static RSMinimapComponent compass = null;

	public static int getCompassAngle() {
		Client cl = RuneScape.getClient();
		return (int) (cl.getCameraAngle() * 2607.5945876176133D)
				+ cl.getCameraOrigin() & 16383;
	}

	public static double getCompassAngleDegrees() {
		return ((16383.0 - getCompassAngle()) / 16383.0) * 360.0;
	}
	
	public static double getCompassAngleRadians() {
		return ((16383.0 - getCompassAngle()) / 16383.0) * (2 * Math.PI);
	}

	public static Point tileToMM(int x, int y) {
		if (compass == null)
			compass = new RSMinimapComponent();
		
		int angle = getCompassAngle();
		
		int angleX = SINE_ARRAY[angle];
		int angleY = COS_ARRAY[angle];
		
		MapBase base = RuneScape.getMapBase();
		
		int startX = RuneScape.getMyPlayer().getLocX1() << 9;
		int startY = RuneScape.getMyPlayer().getLocY1() << 9;
		
		int tileX = x - base.getX() << 9;
		int tileY = y - base.getY() << 9;
		
		int distX = tileX / 128 - startX / 128;
        int distY = tileY / 128 - startY / 128;
		
        int var13 = angleY * distX + angleX * distY >> 14;
        int var14 = distY * angleY - distX * angleX >> 14;
        
		Point comp = compass.getCentrePoint();
		
		return new Point(comp.x + var13, comp.y - var14);
	}
}
