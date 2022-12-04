package modern_java.chapter_02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example05_Callable {

    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName();
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> futureImpl = executorService.submit(new Task());

        Future<String> futureAnonymous = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        Future<String> futureLambda = executorService.submit(() -> Thread.currentThread().getName());
    }

}
