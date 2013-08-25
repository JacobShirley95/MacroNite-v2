package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.script.RuneScape;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.map.RSTile;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSCharacter implements RSScreenObject{
	private org.macronite2.hooks.RSCharacter character;
	
	public RSCharacter(org.macronite2.hooks.RSCharacter character) {
		this.character = character;
	}
	
	public abstract String getName();
	public abstract int getCombatLevel();
	
	public boolean isMoving() {
		return !character.isStationary();
	}
	
	public int getIdleAnimation() {
		return character.getIdleAnimation();
	}
	
	public RSInterfaceGroup getHeadInterfaces() {
		return character.getHeadInterfaces();
	}
	
	public int getAnimation() {
		return character.getAnimation();
	}
	
	public int getHeight() {
		try {
			return character.getHeight();
		} catch (NullPointerException npe) {
			return -1;
		}
	}
	
	@Override
	public Point toScreen() {
		/*Point global = RSMath.localToGlobal(character.getLocX1(), character.getLocY1());
		
		RSTile tile = new RSTile(character.getPlane(), global);
		RSTile tile2 = new RSTile(character.getPlane(), character.getLocX2(), character.getLocY2());
		
		Point p = tile.toScreen(getHeight());
		Point p2 = tile2.toScreen(getHeight());*/
		float x = 0;
		float y = 0;

		float[] last = RuneScape.getClient().getPositionArrayData();
		for (int i = 0; i < 30; i++) {
			RuneScape.getClient().getPositionArray(character, getHeight()/2, true);
			float[] fs = RuneScape.getClient().getPositionArrayData();
			
			if (x == 0) {
				x = fs[0];
				y = fs[1];
			} else if (x > fs[0]) {
				x = fs[0];
				y = fs[1];
				break;
			}
			
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RuneScape.getClient().setPositionArray(last);
		
		return new Point((int)x, (int)y);
	}
	
	public Point toMM() {
		Point p = RSMath.localToGlobal(character.getLocX1(), character.getLocY1());
		return RSCompass.tileToMM(p.x, p.y);
	}
	
	@Override
	public void mouse(int button) {
		RSInput.mouse(toScreen(), button);
	}
	
	public void click() {
		mouse(RSInput.MOUSE_MOVE);
	}
	
	@Override
	public String toString() {
		return "Name: "+getName()+", Level: "+getCombatLevel();
	}
}
