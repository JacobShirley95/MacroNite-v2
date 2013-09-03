package org.macronite2.script.components;

import java.util.List;


import org.macronite2.script.ScriptContext;
import org.macronite2.script.screen.RSInput;


public class RSBank implements RSItemArea{
	public static final int BANK_WIDGET = 762;
	public static final int BANK_MAIN = 113;
	public static final int BANK_CLOSE_BUTTON = 63;
	private ScriptContext context;
	
	public RSBank(ScriptContext context) {
		this.context = context;
	}
	
	public void close() {
		getComponent().getChild(BANK_CLOSE_BUTTON).leftClick();
	}
	
	public boolean isOpen() {
		RSComponent bank = getComponent();
		return bank != null && bank.getChild(BANK_MAIN).isVisible();
	}
	
	public RSComponent getComponent() {
		return context.locator.getWidget(BANK_WIDGET);
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
	
	public void withdrawAt(int slot) {
		getItemSlot(slot).mouse(RSInput.MOUSE_LEFT);
	}
	
	public void withdraw(int id) {
		findItem(id).mouse(RSInput.MOUSE_LEFT);
	}
	
	public void withdraw(String name) {
		findItem(name).mouse(RSInput.MOUSE_LEFT);
	}
	
	private List<RSComponent> children() {
		return context.locator.getWidget(BANK_WIDGET).getChild(BANK_MAIN).children;
	}
}
