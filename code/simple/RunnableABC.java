package simple;

public class RunnableABC implements Runnable {
	private int count = 0;
	private int i = 0;

	public void run() {
		String name = Thread.currentThread().getName();
		while (count < 30) {
			System.out.println(i++);
			synchronized (this) {
				int R = count % 3;
				switch (R) {
				case 0:
					if (name.equals("a")) {
						printA();
						this.notifyAll();
					} else {
						mWait();
					}
					break;
				case 1:
					if (name.equals("b")) {
						printB();
						this.notifyAll();
					} else {
						mWait();
					}
					break;
				case 2:
					if (name.equals("c")) {
						printC();
						this.notifyAll();
					} else {
						mWait();
					}
					break;
				default:
					break;
				}
			}
		}
	}
	
	private void mWait() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
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

}
