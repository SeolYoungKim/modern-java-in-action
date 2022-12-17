package modern_java.chapter_17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TempSubscription implements Subscription {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {  // 다른 스레드에서 다음 요소를 구독자에게 보냄
        executor.submit(() -> {
            for (long i = 0L; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town));  // 현재 온도를 Subscriber에게 전달
                } catch (Exception e) {
                    subscriber.onError(e);  // 실패하면 에러 전달
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        subscriber.onComplete();  // 구독이 취소되면 완료 신호 전달
    }
}
