package com.icepoint.framework.core.support;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.core.util.TimestampUtils;
import org.springframework.data.util.Lazy;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 包含开始时间和结束时间的时间类
 * <p>
 * 基于{@link LocalDateTime}为底层实现的
 *
 * @author Jiawei Zhao
 */
public class TimeRange implements Serializable {

    private static final String DEFAULT_STRING_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_STRING_PATTERN);

    private final LocalDateTime start;

    private final LocalDateTime end;

    private final transient Lazy<Long> startMill = Lazy.of(() -> TimestampUtils.toMills(getStart()));

    private final transient Lazy<Long> endMill = Lazy.of(() -> TimestampUtils.toMills(getEnd()));

    private transient DateTimeFormatter formatter = DEFAULT_FORMATTER;

    private String startStr;

    private String endStr;

    private TimeRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 基于{@link LocalDateTime}，传入开始时间和结束时间来构建一个{@link TimeRange}
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return TimeRange
     */
    public static TimeRange of(LocalDateTime start, LocalDateTime end) {

        Assert.notNull(start, MessageTemplates.notNull("开始时间"));
        Assert.notNull(end, MessageTemplates.notNull("结束时间"));
        Assert.isTrue(start.isBefore(end), "结束时间不能比开始时间更早");

        return new TimeRange(start, end);
    }

    /**
     * 基于13位毫秒级时间戳，传入开始时间和结束时间来构建一个{@link TimeRange}
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return TimeRange
     */
    public static TimeRange of(long start, long end) {
        return of(TimestampUtils.toLocalDateTime(start), TimestampUtils.toLocalDateTime(end));
    }

    /**
     * 获取开始时间
     *
     * @return LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * 获取开始时间的13位毫秒级时间戳
     *
     * @return 13位毫秒级时间戳
     */
    public long getStartMills() {
        return startMill.get();
    }

    /**
     * 获取结束时间
     *
     * @return LocalDateTime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * 获取结束时间的13位毫秒级时间戳
     *
     * @return 13位毫秒级时间戳
     */
    public long getEndMills() {
        return endMill.get();
    }

    /**
     * 获取开始时间的字符串，默认格式为: {@link #DEFAULT_STRING_PATTERN}，
     * 可以通过{@link #setDefaultFormatter(DateTimeFormatter)}进行配置
     *
     * @return 获取开始时间的字符串
     */
    public String getStartString() {
        if (startStr == null) {
            startStr = formatter.format(start);
        }
        return startStr;
    }

    /**
     * 获取开始时间的字符串，根据传入的DateTimeFormatter的格式进行返回
     *
     * @return 获取开始时间的字符串
     */
    public String getStartString(DateTimeFormatter formatter) {
        Assert.notNull(formatter, "时间格式器不能为空");
        return formatter.format(start);
    }

    /**
     * 获取结束时间的字符串，默认格式为: {@link #DEFAULT_STRING_PATTERN}，
     * 可以通过{@link #setDefaultFormatter(DateTimeFormatter)}进行配置
     *
     * @return 获取结束时间的字符串
     */
    public String getEndString() {
        if (endStr == null) {
            endStr = formatter.format(end);
        }
        return endStr;
    }

    /**
     * 获取结束时间的字符串，根据传入的DateTimeFormatter的格式进行返回
     *
     * @return 获取结束时间的字符串
     */
    public String getEndString(DateTimeFormatter formatter) {
        Assert.notNull(formatter, "时间格式器不能为空");
        return formatter.format(end);
    }

    /**
     * 返回当前默认的DateTimeFormatter
     *
     * @return DateTimeFormatter
     */
    public DateTimeFormatter getDefaultFormatter() {
        return formatter;
    }

    /**
     * 配置默认的DateTimeFormatter
     *
     * @param formatter DateTimeFormatter
     */
    public void setDefaultFormatter(DateTimeFormatter formatter) {
        Assert.notNull(formatter, "formatter不能为空");
        this.startStr = null;
        this.endStr = null;
        this.formatter = formatter;
    }

    /**
     * 返回开始时间和结束时间之间的时间长度
     *
     * @return 返回时间长度的Duration对象
     */
    public Duration getDuration() {
        return Duration.between(start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeRange range = (TimeRange) o;
        return start.equals(range.start) && end.equals(range.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
