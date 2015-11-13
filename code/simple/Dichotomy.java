package simple;

/**
 * @author hzj 关于二分算法的实现和拓展
 */
public class Dichotomy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 5, 5, 5, 6, 6, 7 };
		int pos = Dichotomy.dichotomy_default(A, 15);
		int left = Dichotomy.dichotomy_low(A, 6);
		int right = Dichotomy.dichotomy_high(A, 6);
		int[] B = { 8, 10, 2, 3, 5, 6, 7 };
		int posB = Dichotomy.dichotomy_hard(B, 7);
		System.out.println("pos is " + pos + " left is " + left + " right is "
				+ right);
		System.out.println(posB);
	}

	/**
	 * default dichotomy algorithm
	 * 
	 * @param A
	 *            The data array.
	 * @param target
	 *            The target to find.
	 * @return The position of target in A[] if find, -1 otherwise.
	 */
	public static int dichotomy_default(int[] A, final int target) {
		int pos = -1;
		if (null == A || A.length == 0) {
			return pos;
		}
		int left = 0, right = A.length - 1;
		while (left <= right) {
			final int mid = (left + right) / 2;
			if (target == A[mid]) {
				pos = mid;
				break;
			} else if (target < A[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return pos;
	}

	/**
	 * @param A 一个有序数组，数组中数值存在重复
	 * @param target
	 * @return 返回某个值在数组中的第一个位置
	 */
	public static int dichotomy_low(int[] A, final int target) {
		int result = -1;
		if (null == A || A.length == 0) {
			return result;
		}
		int left = 0, right = A.length - 1;
		while (left <= right) {
			final int mid = (left + right) / 2;
			if (target == A[mid]) {
				if ((mid > 0 && target != A[mid - 1]) || mid == 0) {
					result = mid;
					break;
				} else {
					right = mid - 1;
				}
			} else if (target < A[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}

	/**
	 * @param A 一个有序数组，数组中数值存在重复
	 * @param target
	 * @return 返回某个值在数组中的最后一个位置
	 */
	public static int dichotomy_high(int[] A, final int target) {
		int result = -1;
		if (null == A || A.length == 0) {
			return result;
		}
		int left = 0, right = A.length - 1;
		while (left <= right) {
			final int mid = (left + right) / 2;
			if (target == A[mid]) {
				if ((mid < right && target != A[mid + 1]) || mid == right) {
					result = mid;
					break;
				} else {
					left = mid + 1;
				}
			} else if (target < A[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return result;
	}

	//Search in Rotated Sorted Array 数组中没有重复的数值
	//Suppose a sorted array is rotated at some pivot unknown to you beforehand.(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	public static int dichotomy_hard(int[] A, final int target) {
		int result = -1;
		if (null == A || A.length == 0) {
			return result;
		}
		int left = 0, right = A.length - 1;
		while (left <= right) {
			final int mid = (left + right) / 2;
			if (target == A[mid]) {
				result = mid;
				break;
			} else if (A[left] <= A[mid]) {
				if (target >= A[left] && target < A[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (target > A[mid] && target <= A[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return result;
	}
	
	//前面一种情况数组是默认没有重复的数值的，如果有重复的数值呢？其实在这种情况下，判断A[left] <= A[mid]的时候，相等的情况要单独去判断，如果相等只要left++即可。
	public static int dichotomy_duplicate(int[] A, final int target) {
		int result = -1;
		if(null == A || A.length == 0) {
			return result;
		}
		int left = 0, right = A.length -1;
		while(left <= right) {
			final int mid = (left + right) / 2;
			if(target == A[mid]) {
				result = mid;
				break;
			}
			if(A[left] < A[mid]) {
				if(A[left] <= target && target < A[mid]) {
					right = mid -1;
				} else {
					left = mid + 1;
				}
			} else if(A[left] > A[mid]) {
				if(A[mid] < target && target <= A[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				left++;
			}
		}
		return result;
	}

}
