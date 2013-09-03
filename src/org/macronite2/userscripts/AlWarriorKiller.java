package org.macronite2.userscripts;

import java.awt.Point;

import org.macronite2.script.Script;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.entities.RSNPC;
import org.macronite2.script.screen.RSInput;

public class AlWarriorKiller extends Script{

	public AlWarriorKiller(ScriptContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int run() {
		sleep(100);
		if (!isLoggedIn())
			return 0;
		RSNPC npc = context.locator.findNPC("Al Kharid warrior");
		if (npc == null)
			return 0;
		Point screen = npc.getCentrePoint();
		if (screen.x > 0) {
			if (menu.openOptions(screen.x, screen.y)) {
				if (menu.clickOption("Attack")) {
					sleep(2000);
					while (moving())
						sleep(100);
					long t = System.currentTimeMillis();
					while (System.currentTimeMillis() < t+3000) {
						if (context.getMyPlayer().isAnimating()) {
							t = System.currentTimeMillis();
						}
						if (npc.getHeadInterfaces() != null)
							System.out.println("DFSDFDS");
						if (!npc.exists() || !npc.isLoaded())
							break;
						sleep(100);
					}
				}
			}
		} else {
			screen = npc.toMM();
			context.input.mouse(screen, RSInput.MOUSE_LEFT);
			sleep(2000);
			while (moving()) {
				sleep(100);
			}
		}
		return 0;
	}

}
