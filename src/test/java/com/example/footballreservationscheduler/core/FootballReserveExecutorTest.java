package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;

@SpringBootTest
class FootballReserveExecutorTest {

    @Test
    public void executeTest() {
        LocalDate now = LocalDate.now();
        LocalDate targetDate = now.plusMonths(1);
        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver(), now, targetDate);
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
}
