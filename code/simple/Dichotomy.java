package simple;

/**
 * @author hzj
 *  关于二分算法的实现和拓展
 */
public class Dichotomy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {1, 2, 3, 5, 6, 7};
		int pos = Dichotomy.dichotomy_default(A, 15);
		System.out.println(pos);
	}
	
	 /**
     * default dichotomy algorithm
     *
     * @param A[] The data array.
     * @param target The target to find.
     * @return The position of target in A[] if find, -1 otherwise.
     */
	public static int dichotomy_default(int[] A, final int target) {
		int pos = -1;
		if(null == A || A.length == 0) {
			return pos;
		}
		int left = 0, right = A.length - 1;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(target == A[mid]) {
				pos = mid;
				break;
			} else if(target < A[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return pos;
	}

}
