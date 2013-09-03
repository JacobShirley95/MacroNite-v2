package org.macronite2.script.components;

import java.awt.Point;

import org.macronite2.script.ScriptContext;
import org.macronite2.script.items.RSInterfaceItem;

public class RSItemSlot extends RSSlot{

	private RSComponent slotComp;
	private ScriptContext context;

	public RSItemSlot(ScriptContext context, RSComponent slotComp, int index) {
		super(index);
		this.slotComp = slotComp;
		this.context = context;
	}
	
	public RSInterfaceItem getItem() {
		return new RSInterfaceItem(context, context.runescape.getCacheObjectLoader().loadObject(getItemID()), this);
	}
	
	public int getItemID() {
		return slotComp.getItemID();
	}

	public int getAmount() {
		return slotComp.getItemAmount();
	}

	@Override
	public boolean isSelected() {
		return false;
	}

	@Override
	public String[] getActions() {
		return getItem().inventoryActions;
	}

	@Override
	public Point getCentrePoint() {
		return slotComp.getCentrePoint();
	}

	@Override
	public void mouse(int button) {
		slotComp.mouse(button);
	}
}
