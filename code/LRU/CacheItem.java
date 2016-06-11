package LRU;

public class CacheItem {
	private String name;
	private int size;
	private long time;
	private int priority;
	public int count;
	private boolean isAdd;
	private boolean canDelete;
	
	public CacheItem(String name, int size) {
		this.name = name;
		this.size = size;
		this.time = System.currentTimeMillis();
		this.priority = 1;
		this.isAdd = true;
		this.canDelete = true;
		count = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
