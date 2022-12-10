package modern_java.chapter_12;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.util.TimeZone;

public class _04_ZoneId {
    public static void main(String[] args) {
        ZoneId koreaZone = ZoneId.of("Asia/Seoul");
        System.out.println(koreaZone);

        ZoneId defaultZoneId = TimeZone.getDefault().toZoneId();
        System.out.println("defaultZoneId = " + defaultZoneId);

        LocalDate date = LocalDate.of(2022, Month.DECEMBER, 10);
        System.out.println("date = " + date);

        ZonedDateTime zonedDateTime = date.atStartOfDay(koreaZone);
        System.out.println("zonedDateTime = " + zonedDateTime);

        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = now.atZone(koreaZone);
        System.out.println("zonedDateTime1 = " + zonedDateTime1);

        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime2 = instant.atZone(koreaZone);
        System.out.println("zonedDateTime2 = " + zonedDateTime2);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, koreaZone);
        System.out.println("localDateTime = " + localDateTime);

        //TODO 한국의 오프셋 값 +9:00
        ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
        LocalDateTime now1 = LocalDateTime.now();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(now1, zoneOffset);
        System.out.println("offsetDateTime = " + offsetDateTime);

        JapaneseDate japaneseDate = JapaneseDate.from(LocalDate.now());
        System.out.println("japaneseDate = " + japaneseDate);
    }

}
