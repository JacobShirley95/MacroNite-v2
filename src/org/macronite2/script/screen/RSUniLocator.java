package org.macronite2.script.screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.DoorDecor;
import org.macronite2.hooks.NPC;
import org.macronite2.hooks.NPCNode;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.Tile;
import org.macronite2.hooks.WallDecor;
import org.macronite2.script.RuneScape;
import org.macronite2.script.entities.RSDoor;
import org.macronite2.script.entities.RSNPC;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.items.RSGroundItem;
import org.macronite2.script.items.RSInterfaceItem;
import org.macronite2.script.util.node.NodeList;

public class RSUniLocator extends RSLocator {

	public RSUniLocator(Client runescape) {
		super(runescape);
	}

	@Override
	public RSDoor findDoor(final int openID, int mode) {
		final Tile[][] tiles = runescape.getEntityStack().getTiles()[runescape.getMyPlayer().getPlane()];
		final List<RSDoor> doors = new ArrayList<RSDoor>();
		spiralSearch(52, new SpiralPoint() {
			@Override
			public boolean found(int x, int y) {
				WallDecor decor = tiles[x][y].getWallDecor();
				if (decor instanceof DoorDecor) {
					DoorDecor door = (DoorDecor) decor;
					if (door.getID() == openID) {
						doors.add(new RSDoor(door, openID));
						return true;
					}
				}
				return false;
			}
		}, true);
		return doors.isEmpty() ? null : doors.get(0);
	}

	@Override
	public RSNPC findNPC(String name) {
		int npcsCount = runescape.getAvailableNPCS();
		int[] npcIDs = runescape.getNPCIndices();
		NodeList npcs = new NodeList(runescape.getNPCS());
		final List<RSNPC> npcsList = new ArrayList<RSNPC>();
		for (int i = 0; i < npcsCount; i++) {
			NPC npc = (NPC) ((NPCNode) npcs.getNode(npcIDs[i])).getObject();
			if (npc.getName().contains(name)) {
				npcsList.add(new RSNPC(npc));
			}
		}
		final List<RSNPC> found = new ArrayList<RSNPC>();
		spiralSearch(52, new SpiralPoint() {
			@Override
			public boolean found(int x, int y) {
				for (RSNPC npc : npcsList) {
					if (npc.localPos().equals(new Point(x, y))) {
						found.add(npc);
						return true;
					}
				}
				return false;
			}
		}, true);
		return found.isEmpty() ? null : found.get(0);
	}

	@Override
	public RSPlayer findPlayer(String name) {
		int[] ints = runescape.getPlayerIndices();
		Player[] players = runescape.getPlayers();
		for (int i = 0; i < runescape.getPlayerCount(); i++) {
			Player p = players[ints[i]];
			if (p.getName().contains(name))
				return new RSPlayer(p);
		}
		return null;
	}

	@Override
	public RSGroundItem findGroundItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RSInterfaceItem findBackpackItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
