package modern_java.chapter_15;

import static modern_java.chapter_15._03_Example.f;
import static modern_java.chapter_15._03_Example.g;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    /**
     * 아래의 코드는 f(x)의 실행이 끝나지 않는 상황에서 get()을 기다려야 하므로, 프로세싱 자원을 낭비할 수 있다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(() -> a.complete(f(x)));

        int b = g(x);
        System.out.println(a.get() + b);

        executorService.shutdown();
    }
}
