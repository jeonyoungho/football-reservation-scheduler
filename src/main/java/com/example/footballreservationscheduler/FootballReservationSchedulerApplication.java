package com.example.footballreservationscheduler;

import com.example.footballreservationscheduler.core.FootballReserveInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(FootballReserveInfo.class)
@SpringBootApplication
public class FootballReservationSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballReservationSchedulerApplication.class, args);
	}
}
