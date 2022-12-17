package modern_java.chapter_17.rx_java;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class ObservableEx {
    public static void main(String[] args) {
        // onNext("first") -> onNext("second") -> onComplete()
        Observable<String> strings = Observable.just("first", "second");

        // 사용자와 실시간으로 상호작용하면서 지정된 속도로 이벤트를 방출하는 상황에서 유용하게 사용
        // 1초 간격으로 long 형식의 값을 무한으로 증가시키며 방출
        // 데몬 스레드에서 실행
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
//        onePerSec.subscribe(i -> System.out.println(TempInfo.fetch("New York")));
//        onePerSec.blockingSubscribe(i -> System.out.println(TempInfo.fetch("New york")));
    }
}
