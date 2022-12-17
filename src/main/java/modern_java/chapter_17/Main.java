package modern_java.chapter_17;

import java.util.concurrent.Flow.Publisher;

public class Main {
    public static void main(String[] args) {
        // 뉴욕에 새로운 Publisher를 만들고 TempSubscriber를 구독시킴
//        getTemperatures("New York").subscribe(new TempSubscriber());
        getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    // 구독한 Subscriber에게 TempSubscription을 전송하는 Publisher 반환
    private static Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(
                new TempSubscription(subscriber, town)
        );
    }

    private static Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor tempProcessor = new TempProcessor();
            tempProcessor.subscribe(subscriber);
            tempProcessor.onSubscribe(new TempSubscription(tempProcessor, town));
        };
    }

}
