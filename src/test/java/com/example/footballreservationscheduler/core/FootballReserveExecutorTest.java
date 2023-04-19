package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FootballReserveExecutorTest {

    @Test
    public void executeTest() {
        FootballReserveExecutor executor = new FootballReserveExecutor(WebDriverUtil.getChromeDriver());
        executor.execute(BeanUtil.getBean(FootballReserveInfo.class));
    }
}
