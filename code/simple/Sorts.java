package simple;

public class Sorts {

	public static void main(String[] args) {
		int[] A = { 12, 2, 3363, 53, 25, 523, 6, 63, 7 };
		Sorts.bucketSort(A, 4);
		for(int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
	
	
	/**
	 * 关键点是外循环n-1次，每次内循环操作次数减1，基本操作是交换
	 */
	public static void bubbleSort(int[] A) {
		if(null == A || A.length <= 1) {
			return;
		}
		int len = A.length;
		for(int i = 0; i < len - 1; i++) {
			for(int j = 0; j < len - 1 - i; j++) {
				if(A[j] > A[j + 1]) {
					int tem = A[j];
					A[j] = A[j + 1];
					A[j + 1] = tem;
				}
			}
		}
	}
	
	/**
	 * 前面已经有序，比后面值大的元素依次往后走一步，插入数据
	 */
	public static void insertSort(int[] A) {
		if(null == A || A.length <= 1) {
			return;
		}
		int len = A.length;
		for(int i = 1; i < len; i++) {
			int k = i - 1;
			int m = A[i];
			while(k >= 0 && A[k] > m) {
				A[k + 1] = A[k];
				k--;
			}
			A[k + 1] = m;
		}
	}
	
	/**
	 * 每次选择当前数组中最小值和首元素交换
	 */
	public static void selectionSort(int[] A) {
		if(null == A || A.length <= 1) {
			return;
		}
		int len = A.length;
		for(int i = 0; i < len - 1; i++) {
			int min = i;
			for(int j = min + 1; j < len; j++) {
				if(A[j] < A[min]) {
					min = j;
				}
			}
			if(min != i) {
				int tem = A[i];
				A[i] = A[min];
				A[min] = tem;
			}
		}
	}
	
	public static void quickSort(int[] A, int left, int right) {
		if(left >= right || null == A || A.length <= 1) {
			return;
		}
		int first = left, last = right;
		int m = A[first];
		while(first < last) {
			while(first < last && A[last] >= m) {
				last--;
			}
			A[first] = A[last];
			while(first < last && A[first] <= m) {
				first++;
			}
			A[last] = A[first];
		}
		int mid = first;
		A[mid] = m;
		quickSort(A, left, mid -1);
		quickSort(A, mid + 1, right);
	}
	
	/**
	 * 相当于合并两个有序数组，需要申请一个新的数组保存有序后的数据，然后给原数组重新赋值
	 */
	public static void merge(int[] A, int left, int mid, int right) {
		int len = right - left + 1;
		int r[] = new int[len];
		int firstA = left, lastA = mid, firstB = mid + 1,lastB = right;
		int i = 0;
		while(firstA <= lastA && firstB <= lastB) {
			if(A[firstA] <= A[firstB]) {
				r[i++] = A[firstA++];
			} else {
				r[i++] = A[firstB++];
			}
		}
		while(firstA <= lastA) {
			r[i++] = A[firstA++];
		}
		while(firstB <= lastB) {
			r[i++] = A[firstB++];
		}
		for(int j = 0; j < len; j++) {
			A[left + j] = r[j];
		}
	}
	
	public static void mergeSort(int[] A, int left, int right) {
		if(null == A || A.length <= 1 || left >= right) {
			return;
		}
		int mid = (left + right) / 2;
		mergeSort(A, left, mid);
		mergeSort(A, mid + 1, right);
		merge(A, left, mid, right);
	}
	
	/*堆排序*/
	public static void headSort(int[] A) {
		if(null == A || A.length <= 1) {
			return;
		}
		int len = A.length;
		for(int i = (len / 2) - 1; i >= 0; i--) {
			adjust(A, i, len);
		}
		for(int i = len - 1; i >= 0; i--) {
			int tem = A[0];
			A[0] = A[i];
			A[i] = tem;
			adjust(A, 0, i);
		}
	}
	
	public static void adjust(int[] A, int pos, int len) {
		if(null == A || A.length <= 1 || pos >= A.length || pos >= len) {
			return;
		}
		int m = pos;
		while(2 * m + 1 < len) {
			int leftP = 2 * m + 1;
			if(leftP + 1 < len && A[leftP + 1] > A[leftP]) {
				leftP++;
			}
			if(A[m] < A[leftP]) {
				int tem  = A[m];
				A[m] = A[leftP];
				A[leftP] = tem;
			} else {
				break;
			}
			m = leftP;
		}
	}
	
	/*桶排序*/
	public static void bucketSort(int[] A, int m) {
		int len = A.length;
		int[][] bucket = new int[10][len];
		int[] num = new int[10];
		int base = 1;
		for(int q = 0; q < m; q++){
			for(int i = 0; i < len; i++) {
				int k = (A[i] / base) % 10;
				bucket[k][num[k]] = A[i];
				num[k]++;
			}
			int ss = 0;
			for(int i = 0; i < 10; i++) {
				if(num[i] != 0) {
					for(int j = 0; j < num[i]; j++) {
						A[ss++] = bucket[i][j];
					}
				}
				num[i] = 0;
			}
			base *= 10;
		}
	}

}
