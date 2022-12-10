package modern_java.chapter_12;

import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class _03_TemporalAdjusters {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2022, 12, 10);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());

        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
        System.out.println("date3 = " + date3);

        System.out.println(date1.with(dayOfWeekInMonth(0, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(1, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(3, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(4, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(5, DayOfWeek.FRIDAY)));
        System.out.println(date1.with(dayOfWeekInMonth(-1, DayOfWeek.FRIDAY)));
    }
}
