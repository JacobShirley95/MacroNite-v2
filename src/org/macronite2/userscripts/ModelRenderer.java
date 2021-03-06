package org.macronite2.userscripts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.macronite2.hooks.InteractableObject;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.SModel;
import org.macronite2.hooks.Tile;
import org.macronite2.hooks.WorldObjects;
import org.macronite2.script.Script;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.models.RSModel;
import org.macronite2.script.screen.RSInput;

public class ModelRenderer extends Script {
	public ModelRenderer(ScriptContext context) {
		super(context);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		WorldObjects objs = context.runescape.getWorldObjects();
		if (objs != null) {
			Player my = context.runescape.getMyPlayer();
			Tile t = objs.getTiles()[my.getPlane()][my.getLocX1() + 1][my
					.getLocY1()];
			if (t != null) {
				if (t.getInteractable() != null && t.getInteractable().getObject() instanceof InteractableObject) {
					InteractableObject obj = (InteractableObject) t.getInteractable().getObject();
					
					new RSModel(context, (SModel)obj.getModel(), obj.getViewport()).draw(g2D);
				}
			}
		}
	}

	@Override
	public int run() {
		if (!isLoggedIn())
			return -1;
		
		WorldObjects objs = context.runescape.getWorldObjects();
		if (objs != null) {
			Player my = context.runescape.getMyPlayer();
			Tile t = objs.getTiles()[my.getPlane()][my.getLocX1() + 1][my
					.getLocY1()];
			if (t != null) {
				if (t.getInteractable() != null && t.getInteractable().getObject() instanceof InteractableObject) {
					InteractableObject obj = (InteractableObject) t.getInteractable().getObject();
					
					Point p = new RSModel(context, (SModel)obj.getModel(), obj.getViewport()).getCentrePoint();
					if (p != null)
						context.input.mouse(p, RSInput.MOUSE_RIGHT);
				}
			}
		}
		return 0;
	}
}