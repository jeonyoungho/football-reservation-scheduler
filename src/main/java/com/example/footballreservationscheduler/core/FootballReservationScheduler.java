package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Component
public class FootballReservationScheduler {
    @Scheduled(cron = "0 0 9 * * *")
    public void doReserve() {
        LocalDate now = LocalDate.now();
        LocalDate targetDate = now.plusMonths(1);
        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver(), now, targetDate);
        executor.execute(BeanUtil.getBean(FootballReserveInfo.class));
    }
}
