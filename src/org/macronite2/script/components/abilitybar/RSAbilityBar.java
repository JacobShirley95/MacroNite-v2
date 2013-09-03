package org.macronite2.script.components.abilitybar;

import org.macronite2.script.ScriptContext;
import org.macronite2.script.components.RSComponent;

public class RSAbilityBar {
	public static final int ABILITY_BAR_WIDGET = 1430;
	private ScriptContext context;
	
	public RSAbilityBar(ScriptContext context) {
		this.context = context;
	}
	
	protected RSComponent getComponent() {
		return context.locator.getWidget(ABILITY_BAR_WIDGET);
	}
	
	public RSAbilitySlot getSlot(int index) {
		return new RSAbilitySlot(this, index);
	}
}
