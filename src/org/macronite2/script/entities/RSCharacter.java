package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.script.RuneScape;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.map.RSTile;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.models.RSModel;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSCharacter extends RSEntity {
	private org.macronite2.hooks.RSCharacter character;
	
	public RSCharacter(org.macronite2.hooks.RSCharacter character) {
		super(character);
		this.character = character;
	}
	
	@Override
	public RSModel getModel() {
		return null;
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
	
	public Point globalPos() {
		return RSMath.localToGlobal(character.getLocX1(), character.getLocY1());
	}
	
	public Point localPos() {
		return new Point(character.getLocX1(), character.getLocY1());
	}
	
	@Override
	public Point getCentrePoint() {
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		RuneScape.getClient().setPositionArray(last);
		
		return new Point((int)x, (int)y);
	}
	
	@Override
	public String toString() {
		return "Name: "+getName()+", Level: "+getCombatLevel();
	}
}
