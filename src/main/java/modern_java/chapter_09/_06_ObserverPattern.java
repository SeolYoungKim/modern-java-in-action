package modern_java.chapter_09;

import java.util.ArrayList;
import java.util.List;

/**
 * 옵저버 패턴
 * - 어떤 이벤트가 발생했을 때, 한 객체(주제, subject)가 다른 객체 리스트(옵저버, observer)에 자동으로 알림을 보내야 하는 상황에서 사용
 * - GUI 애플리케이션에서 옵저버 패턴이 자주 등장함
 * - 다만, 반드시 GUI에서만 사용되는 것은 아닌데, 주식 가격 변동에 반응하는 다수 거래자 예제에서도 사용할 수 있음
 */
public class _06_ObserverPattern {
    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favorite book is Modern Java In Action!!");

        //TODO Observer가 하나의 메서드만 가졌기 때문에(함수형 인터페이스 형태) 람다식도 쓸 수 있다.
        // 구현체를 넘기지 않는 방법이다.
        feed.registerObserver((tweet) -> {
            if (tweet != null && tweet.contains("League Of Legend")) {
                System.out.println("LOL league is... " + tweet);
            }
        });

        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("Over Watch")) {
                System.out.println("Over Watch is... " + tweet);
            }
        });

        feed.notifyObservers("SKT T1 of League Of Legend won!");
    }

    interface Subject {
        void registerObserver(Observer o);
        void notifyObservers(String tweet);
    }

    static class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(o -> o.notify(tweet));
        }
    }

    interface Observer {
        void notify(String tweet);
    }

    static class NYTimes implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    static class Guardian implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London... " + tweet);
            }
        }
    }

    static class LeMonde implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }
}
