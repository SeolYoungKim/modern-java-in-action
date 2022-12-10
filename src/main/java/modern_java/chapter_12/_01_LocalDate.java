package modern_java.chapter_12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class _01_LocalDate {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2022, 12, 10);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        System.out.println("year = " + year);
        System.out.println("month = " + month);
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("dayOfWeek = " + dayOfWeek);
        System.out.println("len = " + len);
        System.out.println("leapYear = " + leapYear);

        System.out.println("now = " + LocalDate.now());

        int chrYear = date.get(ChronoField.YEAR);
        int chrMonthOfYear = date.get(ChronoField.MONTH_OF_YEAR);
        int chrDayOfMonth = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("chrYear = " + chrYear);
        System.out.println("chrMonthOfYear = " + chrMonthOfYear);
        System.out.println("chrDayOfMonth = " + chrDayOfMonth);

        int monthValue = date.getMonthValue();
        System.out.println("monthValue = " + monthValue);

        LocalDate parse = LocalDate.parse("2022-12-10");
        System.out.println("parse = " + parse.getClass());

        LocalDateTime localDateTime = date.atTime(13, 35, 20);
        LocalDateTime localDateTime2 = date.atTime(LocalTime.of(13, 35, 20));
    }
}
