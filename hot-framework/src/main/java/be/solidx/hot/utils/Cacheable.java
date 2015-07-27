package be.solidx.hot.utils;

public interface Cacheable<V> {
	boolean outdated (V value);
	V getValue();
	String getId();
}