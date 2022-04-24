package util;

import org.springframework.util.Assert;
import util.StringUtil;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * 时间工具类
 * Copyright (C), 2020-2020, IoT数据采集测试
 *
 * @author zhangyao
 * @version 1.0
 * date:     2020/8/22 11:28
 * history:
 */
public abstract class TimeUtil {

    /**
     * 获取当前时间
     *
     * @return Timestamp
     * @author zhangyao
     * date: 2020/8/22 11:36
     */
    public static Timestamp now() {
        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Assert.notNull(date, "Date不能为空");
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将日期转换为格式为yyyy-MM-dd HH:mm:ss的日期字符串
     *
     * @param date 日期
     * @return String
     * @author zhangyao
     * date: 2020/8/24 13:46
     */
    public static String formatDateyyyyMMddHHmmss(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 将日期转换为格式为yyyyMMdd的日期字符串
     *
     * @param date 日期
     * @return String
     * @author zhangyao
     * date: 2020/8/24 13:46
     */
    public static String formatDateyyyyMMddSpecial(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 将日期转换为格式为yyyy的日期字符串
     *
     * @param date 日期
     * @return String
     */
    public static String formatDateyyyySpecial(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 将日期转换为格式为yyyy-MM的日期字符串
     *
     * @param date 日期
     * @return String
     */
    public static String formatDateyyyyMM(Date date) {
        return new SimpleDateFormat("yyyy-MM").format(date);
    }


    /**
     * 将日期转换为格式为yyyy-MM-dd的日期字符串
     *
     * @param date 日期
     * @return String
     * @author xiaozhiwei
     * date: 2020/8/24 13:46
     */
    public static String formatDateyyyyMMdd(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 将日期转换为格式为HH:mm:ss的日期字符串
     *
     * @param date 日期
     * @return String
     * @author zhangyao
     * date: 2020/11/13 9:29
     */
    public static String formatDateHHMMSS(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }

    /**
     * 获取date的整点的开始时间，如  00:00
     *
     * @return String
     * @author xiaozhiwei
     * date: 2020/8/24 13:46
     */
    public static String[] getHourStart() {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        String[] hours = new String[h + 1];
        for (int i = 0; i <= h; i++) {
            hours[i] = (i < 10 ? ("0" + i) : i) + ":00";
        }
        return hours;
    }

    /**
     * 获取date的整点的结束时间，如 00:59
     *
     * @return String
     * @author xiaozhiwei
     * date: 2020/8/24 13:46
     */
    public static String[] getHourEnd() {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        String[] hours = new String[h + 1];
        for (int i = 0; i <= h; i++) {
            hours[i] = (i < 10 ? ("0" + i) : i) + ":59";
        }
        return hours;
    }

    /**
     * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串转换为Date
     *
     * @param dateStr 日期字符串
     * @return Date
     * @throws Exception exception
     * @author zhangyao
     * date: 2020/8/24 9:35
     */
    public static Date parseForyyyyMMddHHmmss(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? new Date() : date;
    }

    public static Date parseForString(String dateStr, String format) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? new Date() : date;
    }

    /**
     * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串转换为Date
     *
     * @param dateStr 日期字符串
     * @return Date
     * @throws Exception exception
     * @author zhangyao
     * date: 2020/8/24 9:35
     */
    public static Date parseForyyyyMMdd(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? new Date() : date;
    }

    /**
     * 日期相减(返回秒值)
     *
     * @param startDate 结束日期
     * @param endDate   开始日期
     * @return 两个日期相差的秒数
     * @author kangbo
     * date: 2020/8/24 9:35
     */
    public static Long diffDateTime(Date startDate, Date endDate) {
        return (Long) ((getMillis(endDate) - getMillis(startDate)) / 1000);
    }

    /**
     * 获取日期的毫秒数
     *
     * @param date 日期
     * @return long
     * @author kangbo
     * date: 2020/8/24 9:35
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取一周的日期数组
     *
     * @param date 开始日期
     * @return Date[]
     * @author zhangyao
     * date: 2020/11/8 19:42
     */
    public static Date[] getDateOfAweek(Date date) {
        Date[] dateArray = new Date[7];
        Date frist = getMonday(date);
        for (int i = 0; i < 7; i++) {
            dateArray[i] = after(frist, i);
        }
        return dateArray;
    }

    /**
     * 获取星期一日期
     *
     * @param date 日期
     * @return Date
     * @author zhangyao
     * date: 2020/11/8 19:43
     */
    public static Date getMonday(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 获取后续日期
     *
     * @param d       当前日期
     * @param squence 完后多长时间
     * @return Date
     * @author zhangyao
     * date: 2020/11/8 19:45
     */
    public static Date after(Date d, int squence) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + squence
                * 24);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 获取两日期区间
     *
     * @param d1 日期
     * @param d2 日期
     * @return int
     * @author zhangyao
     * date: 2020/11/9 14:04
     */
    public static int dayDistance(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalAccessError("the date must be not null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Long result = Math.abs((cal2.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24));
        return Integer.valueOf(result.toString()).intValue();
    }

    public static int dayDistance2(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalAccessError("the date must be not null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Long result = (cal1.getTime().getTime() - cal2.getTime().getTime()) / (1000 * 60 * 60 * 24);
        return Integer.valueOf(result.toString()).intValue();
    }

    /**
     * 比较日期
     *
     * @param d1 Date
     * @param d2 Date
     * @return boolean
     * @author zhangyao
     * date: 2020/11/9 17:40
     */
    public static boolean compare(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.getTimeInMillis() > c2.getTimeInMillis();
    }

    /**
     * 比较日期 大于相等
     *
     * @param d1 Date
     * @param d2 Date
     * @return boolean
     */
    public static boolean compare2(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.getTimeInMillis() >= c2.getTimeInMillis();
    }


    /**
     * description 得到一天的开始时间
     *
     * @param d 某天时间
     * @return Timestamp 该天开始时间
     * @author YS
     * date:   2020/11/13 0013 18:42
     * history:
     */
    public static Timestamp getDayStart(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        // c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * description 得到一天的结束时间
     *
     * @param d 某天时间
     * @return Timestamp 该天结束时间
     * @author YS
     * date:   2020/11/13 0013 18:42
     * history:
     */
    public static Timestamp getDayEnd(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        // c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());

    }


    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return String
     * @author xiaozhiwei
     * date: 2020/1/2 16:54
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }


    /**
     * description 判断是否为同一天
     *
     * @param date1 第一个时间参数
     * @param date2 第二个时间参数
     * @return boolean
     * @author YS
     * date:   2020/11/13 0013 18:42
     * history:
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if ((date1 == null) || (date2 == null)) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }


    /**
     * description根据cal1判断是否为同一天
     *
     * @param cal1 时间
     * @param cal2 时间
     * @return boolean
     * @author YS
     * date:   2020/11/13 0013 18:42
     * history:
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if ((cal1 == null) || (cal2 == null)) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return ((cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1
                .get(6) == cal2.get(6)));
    }


    /**
     * @param s String
     * @return Timestamp
     */
    public static Timestamp parseTime(String s) {
        if (!StringUtil.isInvalid(s)) {
            s.replace(" ", " ");// 中文空格转换为英文空格
            if (s.length() == 10) {
                s += " 00:00:00";
            }
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            try {
                return new Timestamp(format.parse(s).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取某年月最后一天
     *
     * @param year  年
     * @param month 月
     * @return Date
     * @author xiaozhiwei
     * date: 2020/1/16 16:25
     */
    public static Date getMonthLastDay(@NotNull Integer year, @NotNull Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取某年月第一天
     *
     * @param year  年
     * @param month 月
     * @return Date
     * @author xiaozhiwei
     * date: 2020/1/16 16:25
     */
    public static Date getMonthStartDay(@NotNull Integer year, @NotNull Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某年第一天
     *
     * @param year 年
     * @return Date
     * @author xiaozhiwei
     * date: 2020/1/16 16:25
     */
    public static Date getYearStartDay(@NotNull Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取某年最后一天
     *
     * @param year 年
     * @return Date
     * @author xiaozhiwei
     * date: 2020/1/16 16:25
     */
    public static Date getYearEndDay(@NotNull Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当前时间的前一天
     *
     * @return java.util.Date
     * @author chengjin
     * date:
     */
    public static Timestamp getBeforeDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    public static Timestamp getBeforeDay1(Date date) {
        /*Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, date.get.getHours());
        c.set(Calendar.MINUTE, date.getMinutes());
        c.set(Calendar.SECOND, date.getSeconds());
        (date.getTime()-24*60*60*1000)*/
        return new Timestamp((date.getTime() - 24 * 60 * 60 * 1000));
    }

    public static Timestamp getDateByHours(Date date, int hours) {
        /*Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, date.get.getHours());
        c.set(Calendar.MINUTE, date.getMinutes());
        c.set(Calendar.SECOND, date.getSeconds());
        (date.getTime()-24*60*60*1000)*/
        return new Timestamp((date.getTime() + hours * 60 * 60 * 1000));
    }

    /**
     * 获取当前时间的前一周
     *
     * @return java.util.Date
     * @author chengjin
     * date: 2020/3/19 15:42
     */
    public static Timestamp getBeforeWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -6);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当前时间的前一月
     *
     * @return java.util.Date
     * @author chengjin
     * date:
     */
    public static Timestamp getBeforeMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        c.add(Calendar.DATE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当前时间的前一年
     *
     * @return java.sql.Timestamp
     * @author chengjin
     * date: 2020/3/19 19:16
     */
    public static Timestamp getBeforYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        return new Timestamp(c.getTimeInMillis());
    }

    public static Date fromDateString(String dateString, String patten) {

        Date d = null;
        try {
            d = new SimpleDateFormat(patten).parse(dateString);
        } catch (ParseException e) {
        }
        return d != null ? d : new Date();
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 将一周时间段转为一周日期列表
     *
     * @param startTime 周开始时间
     * @param endTime   周结束时间
     * @return List<String>
     * @author zhangzhong
     * 2020/7/8 13:13
     */
    public static List<String> converedPeriodToWeek(Date startTime, Date endTime) {
        List<String> week = new ArrayList<>(7);

        Calendar satrtCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        satrtCal.setTime(startTime);
        endCal.setTime(endTime);
        while (satrtCal.before(endCal)) {
            week.add(formatDateyyyyMMdd(satrtCal.getTime()));
            satrtCal.add(Calendar.DAY_OF_WEEK, 1);
            if (week.size() > 7) {
                return null;
            }
        }
        ;
        week.add(formatDateyyyyMMdd(endCal.getTime()));
        return week;
    }

    /**
     * 根据星期数对应获取时间
     *
     * @param curWeekDay 转换星期某一日期
     * @param oldDate    被转换星期日期
     * @return Date
     * @author zhangzhong
     * 2020/7/8 14:12
     */
    public static Date weekDayMap(Date curWeekDay, Date oldDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(oldDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        c.setTime(curWeekDay);
        int weekYear = c.get(Calendar.YEAR);
        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
        if (dayOfWeek == 1) {
            weekOfYear++;
        }
        c.setWeekDate(weekYear, weekOfYear, dayOfWeek);
        return c.getTime();
    }

    /**
     * 获取当前时间的年-周数字符串
     *
     * @return String
     * @author zhangzhong
     * 2020年7月22日 下午6:24:30
     */
    public static String parseWeekYear() {
        return parseWeekYear(null);
    }

    /**
     * 根据基准时间获取年-周数字符串
     *
     * @param baseTime 基准时间
     * @return String
     * @author zhangzhong
     * 2020年7月22日 下午6:23:33
     */
    public static String parseWeekYear(Date baseTime) {
        StringBuilder yearWeek = new StringBuilder();
        Calendar c = Calendar.getInstance();
        if (baseTime != null) {
            c.setTime(baseTime);
        }
        c.add(Calendar.DAY_OF_YEAR, -1);
        int year = c.getWeekYear();
        yearWeek.append(year).append("-");
        int weekOfDay = c.get(Calendar.WEEK_OF_YEAR);

        if (weekOfDay < 10) {
            yearWeek.append("0");
        }
        yearWeek.append(weekOfDay);

        return yearWeek.toString();
    }

    /**
     * 根据基准时间获取年-月字符串
     *
     * @param baseTime 基准时间
     * @return String
     * @author zhangzhong
     * 2020年7月22日 下午7:13:00
     */
    public static String parseMonthYear(Date baseTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(baseTime);
    }

    /**
     * 获取某月的天数
     *
     * @param baseTime 基准时间
     * @return int
     * @author zhangzhong
     * 2020/7/23 10:00
     */
    public static int daysOfMonth(Date baseTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseTime);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date weekDayMapByLocal(Date curWeekDay, Date oldDate) {

        LocalDate oldLocalDate = oldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate curLocalDate = curWeekDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(curLocalDate.with(ChronoField.DAY_OF_WEEK, oldLocalDate.getDayOfWeek().getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());

//                        Date workDay = Date.from(localDate.plusDays(daysIncrement).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
