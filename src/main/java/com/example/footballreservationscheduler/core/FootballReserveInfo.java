package com.example.footballreservationscheduler.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Data
@ConfigurationProperties(prefix = "reserve-info")
public class FootballReserveInfo {
    private String accountId;
    private String password;
    private String clubName;
    private String reservationSecondPhoneNum;
    private String reservationThirdPhoneNum;
    private String participantCount;
    private String clubCeoName;
    private String clubCeoSecondPhoneNum;
    private String clubCeoThirdPhoneNum;
    private String eventPlanDocLocation;
    private String participantListDocLocation;
}
