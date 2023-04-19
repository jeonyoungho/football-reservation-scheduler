package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Component
public class FootballReservationScheduler {
    @Scheduled(cron = "0 0 9 * * *")
    public void doReserve() {
        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver());
        executor.execute(BeanUtil.getBean(FootballReserveInfo.class));
    }
}
