package org.macronite2.script;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.MapBase;
import org.macronite2.hooks.MouseHandler;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.Renderer;

public class RuneScape {
	private static Client client;
	
	public static Client getClient() {
		return client;
	}
	
	public static void setClient(Client client) {
		RuneScape.client = client;
	}
	
	public static Renderer getRenderer() {
		return client.getCurrentRenderer();
	}
	
	public static MapBase getMapBase() {
		return client.getMapBase();
	}
	
	public static Player getMyPlayer() {
		return client.getMyPlayer();
	}
	
	public static int getPlane() {
		return getMyPlayer().getPlane();
	}
	
	public static MouseHandler getMouseHandler() {
		return client.getMouseHandler();
	}
}
