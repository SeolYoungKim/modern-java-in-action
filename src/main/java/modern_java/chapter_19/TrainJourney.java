package modern_java.chapter_19;

public class TrainJourney {
    public int price;
    public TrainJourney onward;

    public TrainJourney(int price, TrainJourney onward) {
        this.price = price;
        this.onward = onward;
    }

    //상태를 변경 시킴
    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {  // a의 여정 끝에 다다른 다음, 마지막 여정에 b를 더함
            t = t.onward;
        }

        t.onward = b;
        return a;
    }

    //함수형 해결 방법
    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }
}
