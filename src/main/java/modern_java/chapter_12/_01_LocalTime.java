package modern_java.chapter_12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class _01_LocalTime {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(13, 45, 20);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();

        LocalTime parse = LocalTime.parse("13:45:20");
        System.out.println("parse = " + parse.getClass());

        LocalDateTime localDateTime = localTime.atDate(LocalDate.of(2022, 12, 10));

    }
}
