package com.github.tangyi.core.common.util;

import com.github.tangyi.core.common.BaseConstants;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * date time format
     */
    private static final FastDateFormat DATE_TIME_FORMAT = FastDateFormat
            .getInstance(BaseConstants.DEFAULT_PATTERN_DATETIME);

    /**
     * date format
     */
    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(BaseConstants.DEFAULT_PATTERN_DATE);

    /**
     * time format
     */
    private static final FastDateFormat TIME_FORMAT = FastDateFormat.getInstance(BaseConstants.DEFAULT_PATTERN_TIME);

    /**
     * 将string转化为日期
     *
     * @param dateString
     */
    public static Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将string转化为时间
     *
     * @param dateString
     */
    public static Date parseTime(String dateString) {
        try {
            return TIME_FORMAT.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将string转化为日期时间
     *
     * @param dateString
     */
    public static Date parseDateTime(String dateString) {
        try {
            return DATE_TIME_FORMAT.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按格式输出string到date
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parse(String dateString, String pattern) {
        try {
            return FastDateFormat.getInstance(pattern).parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按格式输出date到string
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * 按格式输出time到string
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return TIME_FORMAT.format(date);
    }

    /**
     * 按格式输出DateTime到string
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    /**
     * 按格式输出date到string
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    /**
     * 判断时间是否在这个区间
     *
     * @param date
     * @param min
     * @param max
     * @return
     */
    public static boolean between(Date date, Date min, Date max) {
        return MathUtils.between(date.getTime(), min.getTime(), max.getTime());
    }

    /**
     * 获取该日期所在当天的开始时间
     *
     * @param date
     * @return
     */
    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    /**
     * 获取该日期所在当天的开始时间
     *
     * @param date
     * @return
     */
    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    /**
     * 返回年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return getYear(toCalendar(date));
    }

    /**
     * 返回年份
     *
     * @param calendar 日历
     * @return
     */
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return getMonth(toCalendar(date));
    }

    /**
     * 返回月份
     *
     * @param calendar
     * @return
     */
    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日份
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        return getDay(toCalendar(date));
    }

    /**
     * 返回日份
     *
     * @param calendar
     * @return
     */
    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return getHour(toCalendar(date));
    }

    /**
     * 返回小时
     *
     * @param calendar
     * @return
     */
    public static int getHour(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return getMinute(toCalendar(date));
    }

    /**
     * 返回分钟
     *
     * @param calendar
     * @return
     */
    public static int getMinute(Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return getSecond(toCalendar(date));
    }

    /**
     * 返回秒钟
     *
     * @param calendar
     * @return
     */
    public static int getSecond(Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取当周第一天日期
     *
     * @param date
     * @return
     */
    public static Date getWeekBegin(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取当周最后一天日期
     *
     * @param date
     * @return
     */
    public static Date getWeekEnd(Date date) {
        Calendar calendar = toCalendar(getWeekBegin(date));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        return calendar.getTime();
    }

    /**
     * 获取当月第一天日期
     *
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar calendar = toCalendar(date);
        return getMonthBegin(getYear(calendar), getMonth(calendar));
    }

    /**
     * 获取指定月第一天日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthBegin(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }


    /**
     * 获取当月最后一天日期
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = toCalendar(date);
        return getMonthEnd(getYear(calendar), getMonth(calendar));
    }

    /**
     * 获取指定月最后一天日期
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static Date getMonthEnd(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(year, month - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取当月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 判断是否处于今天
     *
     * @param targetTime
     * @param compareTime
     * @return
     */
    public static boolean IsToday(Date targetTime, Date compareTime) {
        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarDayOfYear = tarCalendar.get(Calendar.DAY_OF_YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comDayOfYear = compareCalendar.get(Calendar.DAY_OF_YEAR);

        if (tarDayOfYear - comDayOfYear == 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否处于昨天
     *
     * @param targetTime
     * @param compareTime
     * @return
     */
    public static boolean IsYesterday(Date targetTime, Date compareTime) {
        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarDayOfYear = tarCalendar.get(Calendar.DAY_OF_YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comDayOfYear = compareCalendar.get(Calendar.DAY_OF_YEAR);

        if (tarDayOfYear - comDayOfYear == 1) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否处于昨天
     *
     * @param targetTime
     * @param compareTime
     * @return
     */
    public static boolean IsBeforeYesterday(Date targetTime, Date compareTime) {
        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarDayOfYear = tarCalendar.get(Calendar.DAY_OF_YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comDayOfYear = compareCalendar.get(Calendar.DAY_OF_YEAR);

        if (tarDayOfYear - comDayOfYear == 2) {
            return true;
        }

        return false;
    }

    /**
     * 相隔天数，endTime - startTime
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int differentDays(Date startTime, Date endTime) {
        int days = (int) ((endTime.getTime() - startTime.getTime()) / (1000 * 3600 * 24));
        return days;
    }

}
