package modern_java.chapter_12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

// 기계의 날짜와 시간을 나타내는 클래스
public class _02_Instance {
    public static void main(String[] args) {
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println(instant);

        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(3, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(3, -1_000_000_000));

        System.out.println(Instant.now());
//        System.out.println(Instant.now().get(ChronoField.DAY_OF_MONTH));  // 예외 터짐

        //TODO Duration
        Duration duration = Duration.between(LocalTime.of(12, 0), LocalTime.of(6, 0));
        System.out.println("duration = " + duration);

        Duration minutes1 = Duration.ofMinutes(3);
        Duration minutes2 = Duration.of(3, ChronoUnit.MINUTES);

        //TODO Period
        Period period = Period.between(LocalDate.of(2022, 12, 10), LocalDate.of(2022, 12, 15));
        System.out.println("period = " + period);

        Period days = Period.ofDays(10);
        Period weeks = Period.ofWeeks(3);
        Period of = Period.of(2, 6, 1);

        System.out.println("days = " + days);
        System.out.println("weeks = " + weeks);
        System.out.println("of = " + of);
    }
}
