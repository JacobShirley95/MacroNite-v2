package org.macronite2.hooks;

public interface OptionNode extends Node{
	public String getOption();
	public String getOptionName();
	public int getAction();
	public long getTarget();
}
