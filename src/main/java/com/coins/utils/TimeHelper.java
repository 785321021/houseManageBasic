package com.coins.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeHelper {
    public static LocalDateTime DateToLocalDateTime(Date date){
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ldt;
    }

    public static Date LocalDateTimeToDate(LocalDateTime ldt){
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        return output;
    }

    public static LocalDate DateToLocalDate(Date date){
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ldt.toLocalDate();
    }
}
