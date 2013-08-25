package org.macronite2.rsapplet;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Rs2ClassLoader extends URLClassLoader{

	private List<Rs2ClassLoaderListener> listeners = new ArrayList<>();
	
	public Rs2ClassLoader(URL url) {
		super(new URL[] {url});
	}

	public Rs2ClassLoader(String rsJar) throws MalformedURLException {
		this(new URL(rsJar));
	}
	
	public void addEventListener(Rs2ClassLoaderListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> loadedClass = super.loadClass(name);
		
		for (Rs2ClassLoaderListener listener : listeners)
			listener.onLoadClass(loadedClass);
		
		return loadedClass;
	}
	
}
