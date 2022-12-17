package modern_java.chapter_17.rx_java;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import modern_java.chapter_17.TempInfo;

public class Main {
    public static void main(String[] args) {
        // 매 초마다 뉴욕의 온도를 보고하는 Observable
        Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Suwon", "Korea");

        // 단순 Observer로 이 Observable에 가입해서 온도 출력하기
        observable.blockingSubscribe(new TempObserver());
    }

    static Observable<TempInfo> getDangerousTemperature(String town) {
        return getCelsiusTemperature(town)
                .filter(tempInfo -> tempInfo.temp() < 0);
    }

    static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(Main::getCelsiusTemperature)
                .collect(Collectors.toList()));
    }

    static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature(town)
                .map(tempInfo -> new TempInfo(tempInfo.town(),
                        (tempInfo.temp() - 32) * 5 / 9));
    }

    /**
     * Emitter : RxJava의 기본 Emmiter를 상속 (onSubscribe 메서드가 빠진 Observer와 같음)
     */
    static Observable<TempInfo> getTemperature(String town) {
        // Observer를 소비하는 함수로부터 Observable 만들기
        return Observable.create(emitter -> {
            // 매 초마다 무한으로 증가하는 일련의 long 값을 방출하는 Observable
            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe(i -> {
                        if (!emitter.isDisposed()) {  // 소비된 옵저버가 아직 폐기되지 않았으면 어떤 작업을 수행
                            if (i >= 5) {  // 온도를 다섯번 보고했으면 옵저버를 완료하고 스트림 종료
                                emitter.onComplete();
                            } else {
                                try {
                                    emitter.onNext(TempInfo.fetch(town));  // 온도를 옵저버로 보고
                                } catch (Exception e) {
                                    emitter.onError(e);  // 에러 발생 시 옵저버에 알림
                                }
                            }
                        }
                    });
        });
    }
}
