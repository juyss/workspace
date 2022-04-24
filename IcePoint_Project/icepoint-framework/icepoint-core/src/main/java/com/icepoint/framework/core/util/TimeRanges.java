package com.icepoint.framework.core.util;

import com.icepoint.framework.core.support.TimeRange;
import org.springframework.util.Assert;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 方便构造TimeRange的工具类
 *
 * @author Jiawei Zhao
 */
public class TimeRanges {

    private static final String TIME_MUST_NOT_BE_NULL = "指定的时间不能为空";

    private TimeRanges() {
    }

    /**
     * 构建time所在年份的时间范围
     *
     * @param time 指定的时间
     * @return 返回当年的TimeRange
     */
    public static TimeRange year(LocalDateTime time) {
        return year(time, 0);
    }

    public static TimeRange year(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
        LocalDateTime end = time.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);

        if (offset != 0) {
            start = start.plusYears(offset);
            end = end.plusYears(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在年份的时间范围
     *
     * @param date 指定的时间
     * @return 返回当年的TimeRange
     */
    public static TimeRange year(Date date) {
        return year(date, 0);
    }

    public static TimeRange year(Date date, long offset) {
        Assert.notNull(date, TIME_MUST_NOT_BE_NULL);
        return year(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在年份的时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回当年的TimeRange
     */
    public static TimeRange year(long mills) {
        return year(TimestampUtils.toLocalDateTime(mills), 0);
    }

    public static TimeRange year(long mills, long offset) {
        return year(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 构建time所在月份的时间范围
     *
     * @param time 指定的时间
     * @return 返回当月的TimeRange
     */
    public static TimeRange month(LocalDateTime time) {
        return month(time, 0);
    }

    public static TimeRange month(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        LocalDateTime end = time.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);

        if (offset != 0) {
            start = start.plusMonths(offset);
            end = end.plusMonths(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在月份的时间范围
     *
     * @param date 指定的时间
     * @return 返回当月的TimeRange
     */
    public static TimeRange month(Date date) {
        return month(date, 0);
    }

    public static TimeRange month(Date date, long offset) {
        return month(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在月份的时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回当月的TimeRange
     */
    public static TimeRange month(long mills) {
        return month(mills, 0);
    }

    public static TimeRange month(long mills, long offset) {
        return month(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 构建time所在星期的时间范围
     *
     * @param time 指定的时间
     * @return 返回当周的TimeRange
     */
    public static TimeRange week(LocalDateTime time) {
        return week(time, 0);
    }

    public static TimeRange week(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime end = time.with(DayOfWeek.SUNDAY).with(LocalTime.MAX);

        if (offset != 0) {
            start = start.plusWeeks(offset);
            end = end.plusWeeks(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在星期的时间范围
     *
     * @param date 指定的时间
     * @return 返回当周的TimeRange
     */
    public static TimeRange week(Date date) {
        return week(date, 0);
    }

    public static TimeRange week(Date date, long offset) {
        Assert.notNull(date, TIME_MUST_NOT_BE_NULL);
        return week(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在星期的时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回当周的TimeRange
     */
    public static TimeRange week(long mills) {
        return week(mills, 0);
    }

    public static TimeRange week(long mills, long offset) {
        return week(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 构建time所在星期的工作日时间范围, 也就是周一到周五的范围
     *
     * @param time 指定的时间
     * @return 返回工作日时间的TimeRange
     */
    public static TimeRange workday(LocalDateTime time) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime end = time.with(DayOfWeek.FRIDAY).with(LocalTime.MAX);

        return TimeRange.of(start, end);
    }

    public static TimeRange workday(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime end = time.with(DayOfWeek.FRIDAY).with(LocalTime.MAX);

        if (offset != 0) {
            start = start.plusWeeks(offset);
            end = end.plusWeeks(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在星期的工作日时间范围
     *
     * @param date 指定的时间
     * @return 返回工作日时间的TimeRange
     */
    public static TimeRange workday(Date date) {
        return workday(date, 0);
    }

    public static TimeRange workday(Date date, long offset) {
        Assert.notNull(date, TIME_MUST_NOT_BE_NULL);
        return workday(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在星期的工作日时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回工作日时间的TimeRange
     */
    public static TimeRange workday(long mills) {
        return workday(mills, 0);
    }

    public static TimeRange workday(long mills, long offset) {
        return workday(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 构建time所在日期的时间范围
     *
     * @param time 指定的时间
     * @return 返回当天的TimeRange
     */
    public static TimeRange day(LocalDateTime time) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(LocalTime.MIN);
        LocalDateTime end = time.with(LocalTime.MAX);

        return TimeRange.of(start, end);
    }

    public static TimeRange day(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        LocalDateTime start = time.with(LocalTime.MIN);
        LocalDateTime end = time.with(LocalTime.MAX);

        if (offset != 0) {
            start = start.plusDays(offset);
            end = end.plusDays(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在日期的时间范围
     *
     * @param date 指定的时间
     * @return 返回当天的TimeRange
     */
    public static TimeRange day(Date date) {
        return day(date, 0);
    }

    public static TimeRange day(Date date, long offset) {
        Assert.notNull(date, TIME_MUST_NOT_BE_NULL);
        return day(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在日期的时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回当天的TimeRange
     */
    public static TimeRange day(long mills) {
        return day(mills, 0);
    }

    public static TimeRange day(long mills, long offset) {
        return day(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 构建time所在小时的时间返回
     *
     * @param time 指定的时间
     * @return 返回指定小时的TimeRange
     */
    public static TimeRange hour(LocalDateTime time) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        int hour = time.getHour();
        LocalDateTime start = time.with(LocalTime.MIN).withHour(hour);
        LocalDateTime end = time.with(LocalTime.MAX).withHour(hour);

        return TimeRange.of(start, end);
    }

    public static TimeRange hour(LocalDateTime time, long offset) {

        Assert.notNull(time, TIME_MUST_NOT_BE_NULL);

        int hour = time.getHour();
        LocalDateTime start = time.with(LocalTime.MIN).withHour(hour);
        LocalDateTime end = time.with(LocalTime.MAX).withHour(hour);

        if (offset != 0) {
            start = start.plusHours(offset);
            end = end.plusHours(offset);
        }

        return TimeRange.of(start, end);
    }

    /**
     * 构建date所在小时的时间范围
     *
     * @param date 指定的时间
     * @return 返回指定小时的TimeRange
     */
    public static TimeRange hour(Date date) {
        return hour(date, 0);
    }

    public static TimeRange hour(Date date, long offset) {
        Assert.notNull(date, TIME_MUST_NOT_BE_NULL);
        return hour(toLocalDateTime(date), offset);
    }

    /**
     * 构建mills所在小时的时间范围
     *
     * @param mills 指定的时间戳, 毫秒级
     * @return 返回指定小时的TimeRange
     */
    public static TimeRange hour(long mills) {
        return hour(mills, 0);
    }

    public static TimeRange hour(long mills, long offset) {
        return hour(TimestampUtils.toLocalDateTime(mills), offset);
    }

    /**
     * 将date转换为LocalDateTime
     *
     * @param date Date
     * @return 转换结果
     */
    private static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
