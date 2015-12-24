package simple;

public class TestException {

	public TestException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int r = -1;
		try {
			r = 1 / 0;
			r = 1; // 前面的语句异常了不会继续执行
		} catch (Exception e) {
			// 在捕获到异常的时候处理，catch可以有多个
			e.printStackTrace();
			r = 2;
		} finally {
			// finally 一定执行，一定不要在finally中return，一般在类似io操作中关闭资源等
			r = 3;
		}
		// 如果前面异常被捕获处理了，则可以执行下面的语句，否则程序终止
		System.out.println("\nok, r is " + r);
		print(-1);
	}
	
	private static void print(int a) {
		if(a < 0) {
			// 异常分检查异常和非检查异常，检查异常在编译之前应该捕获，throw是抛弃一个异常，一般是非检查异常，throws是可能抛出一个异常，用在方法()之后，一般是检查异常
			throw new IllegalStateException("hello");
		}
		System.out.println(a);
	}

}
