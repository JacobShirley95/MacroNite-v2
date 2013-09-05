package org.macronite2.script;

public class Utils {
	public static final int random(int range) {
		return (int)Math.random()*range;
	}
	
	public static final boolean inRange(int i, int low, int high) {
		return i >= low && i <= high;
	}
}
