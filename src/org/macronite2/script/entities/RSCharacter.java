package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.models.RSModel;

public abstract class RSCharacter extends RSEntity {
	private org.macronite2.hooks.RSCharacter character;
	
	public RSCharacter(ScriptContext context, org.macronite2.hooks.RSCharacter character) {
		super(context, character);
		this.character = character;
		this.context = context;
	}
	
	@Override
	public RSModel getModel() {
		return null;
	}
	
	public abstract String getName();
	public abstract int getCombatLevel();
	
	public int getPlane() {
		return character.getPlane();
	}
	
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
		return context.locator.localToGlobal(character.getLocX1(), character.getLocY1());
	}
	
	public Point localPos() {
		return new Point(character.getLocX1(), character.getLocY1());
	}
	
	@Override
	public Point getCentrePoint() {
		float x = 0;
		float y = 0;

		float[] last = context.runescape.getPositionArrayData();
		for (int i = 0; i < 30; i++) {
			context.runescape.getPositionArray(character, getHeight()/2, true);
			float[] fs = context.runescape.getPositionArrayData();
			
			if (x == 0) {
				x = fs[0];
				y = fs[1];
			} else if (x > fs[0]) {
				x = fs[0];
				y = fs[1];
				break;
			}
			
			context.sleep(10);
		}
		context.runescape.setPositionArray(last);
		
		return new Point((int)x, (int)y);
	}
	
	@Override
	public String toString() {
		return "Name: "+getName()+", Level: "+getCombatLevel();
	}
}
