package modern_java.chapter_12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class _01_LocalDateTime {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2022, Month.DECEMBER, 10, 13, 35, 20);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
    }
}
