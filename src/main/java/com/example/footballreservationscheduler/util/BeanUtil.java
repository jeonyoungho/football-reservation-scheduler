package com.example.footballreservationscheduler.util;

import lombok.experimental.UtilityClass;

/**
 * Created by jyh1004 on 2023-04-19
 */

@UtilityClass
public class BeanUtil {
    public static <T> T getBean(Class<T> clazz) {
        return ApplicationContextProvider.getApplicationContext().getBean(clazz);
    }
}
