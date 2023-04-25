package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.DateUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class FootballReserveExecutorTest {

    @Test
    public void executeTest() {
        LocalDate nowDate = LocalDate.now();
        LocalDate targetDate = nowDate.plusMonths(1);
        LocalTime reserveStartTime = LocalTime.of(9, 0, 0);

        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver(), nowDate, targetDate, reserveStartTime);
        executor.execute(BeanUtil.getBean(FootballReserveInfo.class));
    }

    @Test
    public void calculateDateTest() {
        LocalDate now = LocalDate.now();

        LocalDate oneMonthAfter = now.plusMonths(1);
        System.out.println("oneMonthAfter = " + oneMonthAfter);

        DayOfWeek dayOfWeekForOneMonthAfter = oneMonthAfter.getDayOfWeek();
        System.out.println("dayOfWeekForOneMonthAfter = " + dayOfWeekForOneMonthAfter);
    }

    @Test
    public void timeUntilTest() {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 0);
        Date until = calendar.getTime();

        long waitTime = until.getTime() - now.getTime();

        try {
            Thread.sleep(waitTime);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("current -> " + LocalDateTime.now());

    }

    @Test
    public void dateTest() {
        LocalDate now = LocalDate.now();
        LocalTime reserveStartTime = LocalTime.of(9, 0, 0);

        Date result = DateUtil.convertToDate(LocalDateTime.of(now, reserveStartTime));

        System.out.println("-> " + result);
    }

}
