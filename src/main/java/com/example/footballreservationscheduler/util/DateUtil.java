package com.example.footballreservationscheduler.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by jyh1004 on 2023-04-24
 */

@UtilityClass
public class DateUtil {

    public static Date convertToDate(LocalDateTime source) {
        if (source == null) {
            return null;
        }

        return Date.from(source.atZone(ZoneId.systemDefault())
                               .toInstant());
    }
}
