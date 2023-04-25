package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Component
public class FootballReservationScheduler {
    @Scheduled(cron = "45 59 8 * * *")
    public void doReserve() {
        LocalDate nowDate = LocalDate.now();
        LocalDate targetDate = nowDate.plusMonths(1);
        LocalTime reserveStartTime = LocalTime.of(9, 0, 0);

        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver(), nowDate, targetDate, reserveStartTime);
        executor.execute(BeanUtil.getBean(FootballReserveInfo.class));
    }
}
