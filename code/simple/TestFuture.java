package simple;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {

	public TestFuture() {
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task2 task2 = new Task2();
		Future<?> result = executor.submit(task2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程中");
//		if(result.cancel(true)) {
//			System.out.println("task取消");
//		}
//		executor.shutdownNow();
		try {
			System.out.println("The result is " + result.get());
			System.out.println("The result is ok");
		} catch (InterruptedException e) {
			System.out.println("线程被中断了！");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("线程被取消了！");
			e.printStackTrace();
		}
	}

}

class Task implements Callable<Integer>{

	public Integer call() throws Exception {
		System.out.println("task start");
		Thread.sleep(2000);
		int sum = 0;
		for(int i = 0; i< 100; i++) {
			sum += i;
		}
		System.out.println("task start 2");
		return sum;
	}
	
}

class Task2 implements Runnable {

	public void run() {
		int sum = 0;
		for(int i = 0; i < 100; i++) {
			sum += i;
		}
		System.out.println("sum is " + sum);
	}
	
}
