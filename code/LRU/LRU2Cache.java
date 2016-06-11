package LRU;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRU2Cache {
	Queue<CacheItem> queue;
	private Map<String, CacheItem> map;
	private int maxSize;
	private int size;
	private LRUCache lruCache;
	
	public LRU2Cache(int maxSize, int maxSize2) {
		this.queue = new LinkedList<CacheItem>();
		this.map = new HashMap<String, CacheItem>();
		this.maxSize = maxSize;
		this.lruCache = new LRUCache(maxSize2);
	}
	
	public CacheItem get(String key) {
		synchronized (this) {
			CacheItem item = this.lruCache.get(key);
			if(null != item) {
				return item;
			} else {
				item = map.get(key);
				if(null != item) {
					item.count++;
					if(item.count == 2) {
						this.lruCache.set(key, item);
						this.map.remove(key);
					} else {
						this.lruCache.hitCount++;
					}
				}
				return item;
			}
		}
	}
	
	public void set(String key, CacheItem value) {
		synchronized (this) {
			if(value.isAdd()) {
				map.put(key, value);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRU2Cache lruCahce = new LRU2Cache(20,1000);
		String[] names = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","aa","bb","cc","dd","ab","ba"};
		int[] sizes = {153, 67, 123,127,75,23,45,32,112,46,34,56, 43,77,124,31,27,142,102,63};
		int len = names.length;
		for(int i = 0; i < len; i++) {
			CacheItem item = new CacheItem(names[i],sizes[i]);
			if(i < 5) {
				item.setCanDelete(false);
			}
			lruCahce.set(names[i], item);
			lruCahce.lruCache.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
		for(int i = 10; i < 15; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
		String[] names2 = {"a1","b1","c1","d1","e1","f1","g1"};
		int[] sizes2 = {153,123,76,87,102,78,66};
		int len2 = names2.length;
		for(int i = 0; i < len2; i++) {
			CacheItem item = new CacheItem(names2[i],sizes2[i]);
			if(i < 3) {
				item.setAdd(false);
			}
			lruCahce.set(names2[i], item);
			lruCahce.lruCache.print();
		}
		for(int i =10; i < 20; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
		for(int i = 10; i < 20; i++) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
		for(int i = 10; i > 0; i--) {
			CacheItem item = lruCahce.get(names[i]);
			lruCahce.lruCache.print();
		}
	}

}
