package modern_java.chapter_15;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _03_Example3 {
    public static void main(String[] args) throws InterruptedException {
        work1();
        Thread.sleep(10000);
        System.out.println("해당 라인도 10초 후에 실행된다. work2()만 단독적으로 10초 후에 실행할 수 없다.");
        work2();

        System.out.println("---");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        work1();
        scheduledExecutorService.schedule(
                _03_Example3::work2, 10,
                TimeUnit.SECONDS);  // work1 이 끝난 후, 10초 뒤에 work2를 개별 태스크로 스케줄링

        System.out.println("하지만 해당 라인은 잠들지 않는다. work2()만 단독적으로 잠들었다!");

        scheduledExecutorService.shutdown();
    }

    private static void work1() {
        System.out.println("Hello from Work1!");
    }

    private static void work2() {
        System.out.println("Hello from Work2!");
    }
}
