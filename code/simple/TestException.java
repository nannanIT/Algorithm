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
			r = 1; // ǰ�������쳣�˲������ִ��
		} catch (Exception e) {
			// �ڲ����쳣��ʱ����catch�����ж��
			e.printStackTrace();
			r = 2;
		} finally {
			// finally һ��ִ�У�һ����Ҫ��finally��return��һ��������io�����йر���Դ��
			r = 3;
		}
		// ���ǰ���쳣���������ˣ������ִ���������䣬���������ֹ
		System.out.println("\nok, r is " + r);
		print(-1);
	}
	
	private static void print(int a) {
		if(a < 0) {
			// �쳣�ּ���쳣�ͷǼ���쳣������쳣�ڱ���֮ǰӦ�ò���throw������һ���쳣��һ���ǷǼ���쳣��throws�ǿ����׳�һ���쳣�����ڷ���()֮��һ���Ǽ���쳣
			throw new IllegalStateException("hello");
		}
		System.out.println(a);
	}

}
