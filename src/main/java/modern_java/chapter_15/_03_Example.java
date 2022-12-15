package modern_java.chapter_15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _03_Example {
    static int f(int x) {
        return x + 1;  // 대충 오래 걸리는 작업
    }

    static int g(int x) {
        return x + 100;  // 대충 오래 걸리는 작업
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int y = f(1);
        int z = g(1);

        System.out.println(y + z);


        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> {
            result.left = f(x);
        });

        Thread t2 = new Thread(() -> {
            result.right = g(x);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(result.left + result.right);


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> futureY = executorService.submit(() -> f(x));
        Future<Integer> futureZ = executorService.submit(() -> g(x));
        System.out.println(futureY.get() + futureZ.get());

        executorService.shutdown();
    }

    static class Result {
        private int left;
        private int right;

        public void setLeft(int left) {
            this.left = left;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int left() {
            return left;
        }

        public int right() {
            return right;
        }
    }
}

