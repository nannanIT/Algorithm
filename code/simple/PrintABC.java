package simple;

public class PrintABC extends Thread {

	private static int count = 0;
	private int mark;

	public PrintABC(int mark) {
		this.mark = mark;
	}

	@Override
	public void run() {
		while (count < 30) {
			if (count % 3 == mark) {
				switch (mark) {
				case 0:
					printA();
					break;
				case 1:
					printB();
					break;
				case 2:
					printC();
					break;
				default:
					break;
				}
			}
		}
	}

	private void printA() {
		System.out.println("A");
		count++;
	}

	private void printB() {
		System.out.println("B");
		count++;
	}

	private void printC() {
		System.out.println("C");
		count++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new PrintABC(0).start();
//		new PrintABC(1).start();
//		new PrintABC(2).start();
		RunnableABC abc = new RunnableABC();
		new Thread(abc, "a").start();
		new Thread(abc, "b").start();
		new Thread(abc, "c").start();
	}

}
