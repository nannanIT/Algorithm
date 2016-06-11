package simple;

public class List {
	private ListNode head;
	private ListNode tail;

	public List() {
		this.head = new ListNode(0, null);
		this.tail = this.head;
	}
	
	public void insertListNode(int value) {
		ListNode node = new ListNode(value, null);
		node.next = head.next;
		head.next = node;
		head.value++;
	}
	
	public void printList() {
		ListNode node = this.head.next;
		while(null != node) {
			System.out.println(node.value);
			node = node.next;
		}
	}
	
	public void insertListNodeByTail(int value) {
		ListNode node = new ListNode(value, null);
		node.next = this.tail.next;
		this.tail.next = node;
		this.tail = node;
	}
	
	public boolean containsValue(int value) {
		ListNode node = this.head.next;
		while(null != node) {
			if (node.value == value) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	public boolean removeValue(int value) {
		ListNode pre = this.head;
		ListNode current = this.head.next;
		while(null != current) {
			if(current.value == value) {
				break;
			}
			current = current.next;
			pre = pre.next;
		}
		if(null != current) {
			pre.next = current.next;
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
