package org.macronite2.script.components.abilitybar;

import java.awt.Point;

import org.macronite2.script.components.RSSlot;

public class RSAbilitySlot extends RSSlot{
	public static final int SLOT_RECHARGE_OFFSET = 98;
	public static final int SLOT_OFFSET = 97;
	
	private RSAbilityBar abilityBar;

	public RSAbilitySlot(RSAbilityBar abilityBar, int index) {
		super(index);
		this.abilityBar = abilityBar;
	}

	public int getAbilityID() {
		return abilityBar.getComponent().getChild(SLOT_OFFSET+(index*5)).getImageID();
	}
	
	public boolean isReady() {
		return !abilityBar.getComponent().getChild(SLOT_RECHARGE_OFFSET+(index*5)).isVisible();
	}

	@Override
	public boolean isSelected() {
		return false;
	}

	@Override
	public String[] getActions() {
		return abilityBar.getComponent().getChild(SLOT_OFFSET+(index*5)).getActions();
	}

	@Override
	public Point getCentrePoint() {
		return abilityBar.getComponent().getChild(SLOT_OFFSET+(index*5)).getCentrePoint();
	}

	@Override
	public void mouse(int button) {
		abilityBar.getComponent().getChild(SLOT_OFFSET+(index*5)).mouse(button);
	}
}
