package simple;

public class N2048 {
	private static final int NUM = 4;
	
	public static void left2Right(int[] A) {
		int count = A.length;
		boolean flag = true;
		for(int i = count - 1; i >= 0; i--) {
			if(A[i] != 0 && i > 0 && flag) {
				int pos = findEqualPos(A, A[i], i - 1);
				if(pos != -1) {
					A[i] += A[i];
					A[pos] = 0;
					flag = false;
				}
			}
			if(A[i] != 0) {
				int k = i + 1;
				while(k < count && A[k] == 0) {
					A[k] = A[k - 1];
					A[k - 1] = 0;
					k++;
				}
			}
		}
	}
	
	public static int findEqualPos(int[] A, int m, int p) {
		int result = -1;
		for(int i = p; i >= 0; i--) {
			if(0 == A[i]) {
				continue;
			}
			if(m == A[i]) {
				result = i;
			}
			break;
		}
		return result;
	}

	public N2048() {}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {2, 4, 4, 2, 2};
		N2048.left2Right(A);
		for(int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}

}
