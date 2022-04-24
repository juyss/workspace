package com.icepoint.framework.core.util;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public final class TimestampUtils {

    private TimestampUtils() {
    }

    /**
     * 获取传入时间当天的第一毫秒时间戳
     *
     * @param day 时间
     * @return 当天第一毫秒时间戳
     */
    public static long startOfDay(TemporalAccessor day) {
        LocalDateTime time = LocalDateTime.from(day).with(LocalTime.MIN);
        return toMills(time);
    }

    /**
     * 获取传入时间当天的最后一毫秒时间戳
     *
     * @param day 时间
     * @return 当天最后一毫秒时间戳
     */
    public static long endOfDay(TemporalAccessor day) {
        LocalDateTime time = LocalDateTime.from(day).with(LocalTime.MAX);
        return toMills(time);
    }

    /**
     * 获取传入时间当月的第一毫秒时间戳
     *
     * @param month 时间
     * @return 当月的第一毫秒时间戳
     */
    public static long firstMillOfMonth(TemporalAccessor month) {
        return toMills(LocalDateTime.of(
                month.get(ChronoField.YEAR),
                month.get(ChronoField.MONTH_OF_YEAR),
                1, 0, 0, 0, 0
        ));
    }

    /**
     * 获取传入时间当月的最后一毫秒时间戳
     *
     * @param month 时间
     * @return 当月最后一毫秒时间戳
     */
    public static long lastMillOfMonth(TemporalAccessor month) {
        LocalDateTime dateTime = LocalDateTime.of(
                month.get(ChronoField.YEAR),
                month.get(ChronoField.MONTH_OF_YEAR),
                1, 0, 0, 0, 0
        ).plusMonths(1).minusNanos(1);
        return toMills(dateTime);
    }

    /**
     * 将{@link LocalDateTime}转为13位毫秒级时间戳
     *
     * @param time 要转换的LocalDateTime
     * @return 13位毫秒级时间戳
     */
    public static long toMills(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将13位毫秒级时间戳转为{@link LocalDate}
     *
     * @param timestamp 13位毫秒级时间戳
     * @return LocalDate
     */
    public static LocalDate toLocalDate(long timestamp) {
        return toInstant(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将13位毫秒级时间戳转为{@link LocalDateTime}
     *
     * @param timestamp 13位毫秒级时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(long timestamp) {
        return toInstant(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Instant toInstant(long timestamp) {
        return Instant.ofEpochMilli(timestamp);
    }

}
