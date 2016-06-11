package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
	final LinkedHashMap<String, CacheItem> map;
	private int maxSize;
	private int size;
	private int putCount;
	private int evictionCount;
	public int hitCount;
	public int missCount;
	
	public LRUCache(int maxSize) {
		this.maxSize = maxSize;
		this.map = new LinkedHashMap<String, CacheItem>(0, 0.75f, true);
	}
	public CacheItem get(String key) {
		CacheItem cache;
		synchronized(this) {
			cache = map.get(key);
			if(null != cache) {
				hitCount++;
				return cache;
			}
			missCount++;
		}
		return null;
	}
	public void set(String key, CacheItem cache) {
		synchronized (this) {
			CacheItem previous = map.put(key, cache);
			putCount++;
			if(null != previous) {
				size -= previous.getSize();
				evictionCount++;
			}
			size += cache.getSize();
			trimToSize();
		}
	}
	private void trimToSize() {
		while(true) {
			String key;
			CacheItem value;
			synchronized (this) {
				if(size <= maxSize || map.isEmpty()) {
					break;
				}
				Map.Entry<String, CacheItem> item = map.entrySet().iterator().next();
				key = item.getKey();
				value = item.getValue();
				if(!value.isCanDelete()) {
					continue;
				}
				map.remove(key);
				size -= value.getSize();
				evictionCount++;
			}
		}
	}
	
	public void print() {
		System.out.println("size:" + size + " putCount:" + putCount + " hitCount:" + hitCount + " evictionCount:" + evictionCount + "missCount :" + missCount);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache lruCahce = new LRUCache(1000);
		String[] names = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","aa","bb","cc","dd","ab","ba"};
		int[] sizes = {153, 67, 123,127,75,23,45,32,112,46,34,56, 43,77,124,31,27,142,102,63};
		int len = names.length;
		for(int i = 0; i < len; i++) {
			CacheItem item = new CacheItem(names[i],sizes[i]);
			lruCahce.set(names[i], item);
			lruCahce.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
		for(int i = 10; i < 15; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
		String[] names2 = {"a1","b1","c1","d1","e1","f1","g1"};
		int[] sizes2 = {153,123,76,87,102,78,66};
		int len2 = names2.length;
		for(int i = 0; i < len2; i++) {
			CacheItem item = new CacheItem(names2[i],sizes2[i]);
			lruCahce.set(names2[i], item);
			lruCahce.print();
		}
		for(int i =10; i < 20; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
		for(int i =10; i < 20; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.print();
		}
	}

}
