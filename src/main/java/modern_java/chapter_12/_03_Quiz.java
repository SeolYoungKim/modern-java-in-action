package modern_java.chapter_12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class _03_Quiz {
    static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {  //day가 넘어온다고 가정
            LocalDate nextDay = (LocalDate) temporal.plus(1, ChronoUnit.DAYS);
            DayOfWeek dayOfWeek = nextDay.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.SATURDAY) {
                nextDay = nextDay.plus(2, ChronoUnit.DAYS);
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                nextDay = nextDay.plus(1, ChronoUnit.DAYS);
            }

            return nextDay;
        }
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2022, 12, 11);
        date = date.with(new NextWorkingDay());
        date = date.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }

            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        System.out.println(date);
    }
}
