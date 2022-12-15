package modern_java.chapter_15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * 두 정보 소스로부터 발생하는 이벤트를 합쳐서 다른 구독자가 볼 수 있도록 발행하는 예시 스프레드 시트 셀에서 흔히 제공하는 동작 -> "=C1+C2"
 */
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    public static void main(String[] args) {
        // C1/C2의 값이 바뀌었을 때 C3가 두 값을 더하도록 어떻게 지정할 수 있을까?
        // C1과 C2에 이벤트가 발생했을 때 C3를 구독하도록 만들자
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);  // c1이 c3를 구독한다

        c1.onNext(10);
        c2.onNext(20);
    }
    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue;
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscribers();
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("call onSubscribe");
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
