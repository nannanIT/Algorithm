package simple;

public class MaxSubsequence {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {1, 2, -5, 6, -13, 8, 2};
		int max = MaxSubsequence.maxSubsquence(A);
		System.out.println(max);
	}
	
	public static int maxSubsquence(int[] A) {
		int len = A.length;
		int max = A[0];
		int currentMax = max;
		for(int i = 1; i < len; i++) {
			currentMax = Math.max(currentMax + A[i], A[i]);
			max = Math.max(max, currentMax);
		}
		return max;
	}
	
	//Çî¾Ù
	public static int maxSubsquence2(int[] A) {
		int len = A.length;
		int max = A[0];
		for(int i = 0; i < len; i++) {
			int tem = A[i];
			max = Math.max(max, tem);
			for(int j = i + 1; j < len; j++) {
				tem += A[j];
				max = Math.max(tem, max);
			}
		}
		return max;
	}

}
