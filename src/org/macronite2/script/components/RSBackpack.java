package org.macronite2.script.components;

import java.util.List;

import org.macronite2.script.ScriptContext;

public class RSBackpack implements RSItemArea{
	public static final int BACKPACK_ID = 1473;
	public static final int BACKPACK_MAIN_ID = 8;
	private ScriptContext context;
	
	public RSBackpack(ScriptContext context) {
		this.context = context;
	}

	@Override
	public int itemCount() {
		int c = 0;
		for (RSComponent comp : children())
			if (comp.getItemID() != -1)
				c++;
		return c;
	}

	@Override
	public RSItemSlot findItem(String name) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public RSItemSlot findItem(int id) {
		int i = 0;
		for (RSComponent comp : children()) {
			if (comp.getItemID() == id)
				return new RSItemSlot(context, comp, i);
			i++;
		}
		return null;
	}

	@Override
	public RSItemSlot getItemSlot(int slot) {
		return new RSItemSlot(context, children().get(slot), slot);
	}
	
	private List<RSComponent> children() {
		return context.locator.getWidget(BACKPACK_ID).getChild(BACKPACK_MAIN_ID).children;
	}
}
