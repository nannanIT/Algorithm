package simple;

public class RemoveDuplicate {
	
	public static int removeDup(int[] A) {
		if(null == A || A.length == 0) {
			return 0;
		}
		int len = A.length;
		int cur = 0;
		for(int i = 0; i < len; i++) {
			if(A[i] != A[cur]) {
				A[++cur] = A[i];
			}
		}
		return cur + 1;
	}
	
	public static int removeDupTwo(int[] A) {
		if(null == A || A.length == 0) {
			return 0;
		}
		int len = A.length;
		int num = 1;
		int cur = 0;
		for(int i = 0; i < len; i++) {
			if(A[cur] == A[i]) {
				if(num < 2) {
					cur++;
					num++;
				}
			} else {
				A[++cur] = A[i];
				num = 1;
			}
		}
		return cur + 1;
	}
	
	public static void printArray(int[] A, int k) {
		int len = A.length;
		for(int i = 0; i < len && i < k; i++) {
			System.out.println(A[i]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {3, 5, 7, 7, 7, 9, 10, 10, 10, 12};
		int len = RemoveDuplicate.removeDupTwo(A);
		System.out.println(len);
		RemoveDuplicate.printArray(A, len);
	}

}
