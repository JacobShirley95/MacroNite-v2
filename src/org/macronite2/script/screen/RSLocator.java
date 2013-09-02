package org.macronite2.script.screen;

import java.awt.Point;
import java.util.Comparator;
import java.util.List;

import org.macronite2.hooks.Client;
import org.macronite2.script.entities.RSDoor;
import org.macronite2.script.entities.RSNPC;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.items.RSGroundItem;
import org.macronite2.script.items.RSInterfaceItem;

public abstract class RSLocator {
	protected Client runescape;
	public RSLocator(Client runescape) {
		this.runescape = runescape;
	}
	
	public abstract RSDoor findDoor(final int openID, int mode);
	public abstract RSNPC findNPC(String name);
	public abstract RSPlayer findPlayer(String name);
	public abstract RSGroundItem findGroundItem(int id);
	public abstract RSInterfaceItem findBackpackItem(int id);
	
	protected void spiralSearch(int maxRadius, SpiralPoint update, boolean exitOnFind) {
		int curR = 1;
		int curDir = 0;
		
		Point mp = new RSPlayer(runescape.getMyPlayer()).globalPos();
		while (curR < maxRadius) {
			for (int i = 0; i < curR; i++) {
				if (curDir == 0)
					mp.x++;
				else if (curDir == 1)
					mp.y++;
				else if (curDir == 2)
					mp.x--;
				else
					mp.y--;

				if (update.found(mp.x, mp.y) && exitOnFind)
					return;
			}
			curDir++;
			curDir = curDir % 4;
			if (curDir % 2 == 0)
				curR++;
		}
	}
	
	protected abstract class SpiralPoint {
		public abstract boolean found(int x, int y);
	}
}
