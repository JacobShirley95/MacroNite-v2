package org.macronite2.script.components;

import org.macronite2.script.ScriptContext;

public class RSMinimapComponent extends RSComponent {
	public static final int MM_WIDGET_ID = 1465;

	public RSMinimapComponent(ScriptContext context) {
		super(context, context.locator.getWidget(MM_WIDGET_ID));
	}

}
