package org.macronite2.script.items;

import java.awt.Point;

import org.macronite2.script.components.RSItemSlot;

public class RSInterfaceItem extends RSItem{
	public RSItemSlot slot;
	public int amount;
	
	public RSInterfaceItem(org.macronite2.hooks.RSObject rsObj, RSItemSlot rsItemSlot) {
		super(rsObj);
		this.slot = rsItemSlot;
		this.amount = rsItemSlot.getAmount();
	}

	@Override
	public Point getCentrePoint() {
		return slot.getCentrePoint();
	}
}
