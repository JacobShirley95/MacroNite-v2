package org.macronite2.hooks;

public interface Client {
	public Player[] getPlayers();
	public Player getMyPlayer();
	public int[] getPlayerIndices();
	
	public int getFriendsCount();
	public Friend[] getFriends();
	
	public int getIgnoredCount();
	public IgnoredPlayer[] getIgnoredPlayers();
	
	public NodeArrayList getGroundObjects();
	
	public int getAvailableNPCS();
	public int[] getNPCIndices();
	public NodeArrayList getNPCS();
	
	public RSInterfaceGroup[] getInterfaceGroups();
	
	public CacheObjectLoader getCacheObjectLoader();
	
	public RenderSettings getRenderSettings();
	
	public CacheNodeList getPlayerModelCache();
	
	public Renderer getCurrentRenderer();
	public int getPlayerCount();
	
	public MapBase getMapBase();

	public boolean[] getValidInterfaces();
	
	public float getCameraAngle();
	public int getCameraOrigin();
	
	public NodeArrayList getWidgets();
	
	public MouseHandler getMouseHandler();
	public KeyboardHandler getKeyboardHandler();
	
	public int getOptionsBoxX();
	public int getOptionsBoxY();
	public int getOptionsBoxWidth();
	public int getOptionsBoxHeight();
	public int getOptionsBoxOffset();
	public int getOptionsCount();
	public boolean isOptionsBoxOpen();
	public IterableNodeList getOptions();
	
	public int getTileHeight(int x, int y, int plane);
	
	public float[] getPositionArrayData();
	public void setPositionArray(float[] data);
	
	public void getPositionArray(MovableEntity entity, int height, boolean whileShowing);
	public WorldObjects getWorldObjects();
}
