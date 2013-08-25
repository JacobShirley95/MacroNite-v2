package org.macronite2.hooks;

import java.util.Collection;

public interface IterableNodeList extends Collection, Iterable {
	public Node getCurrent();
}
