package com.icepoint.framework.web.core.support;

import com.icepoint.framework.core.support.TimeRange;
import com.icepoint.framework.core.util.TimeRanges;
import com.icepoint.framework.core.util.TimestampUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Jiawei Zhao
 */
public class StringToTimeRangeConverter implements Converter<String, TimeRange> {

    private static final char LPAREN = '(';
    private static final char RPAREN = ')';

    @Nullable
    @Override
    public TimeRange convert(String source) {

        if (!StringUtils.hasText(source)) {
            return null;
        }

        Type type = Type.matches(source);
        switch (type) {
            case START_AND_END:

                String[] split = source.split(",");
                Assert.isTrue(split.length == 2,
                        "TimeRange必须也必须只能有配置开始或者结束时间, 获取到的表达式: " + source);

                long start = parseTimestamp(split[0].trim());
                long end = parseTimestamp(split[0].trim());

                return TimeRange.of(start, end);
            case HOUR:
                return TimeRanges.hour(parseLocalDateTime(source));
            case DAY:
                return TimeRanges.day(parseLocalDateTime(source));
            case WORKDAY:
                return TimeRanges.workday(parseLocalDateTime(source));
            case WEEK:
                return TimeRanges.week(parseLocalDateTime(source));
            case MONTH:
                return TimeRanges.month(parseLocalDateTime(source));
            case YEAR:
                return TimeRanges.year(parseLocalDateTime(source));
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private long parseTimestamp(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("TimeRange配置的时间必须是数字类型, 13位时间戳, 获取到的值: " + str);
        }
    }

    public LocalDateTime parseLocalDateTime(String str) {

        int fromIndex = str.indexOf(LPAREN);
        int toIndex = str.indexOf(RPAREN);

        if (fromIndex < 0 || toIndex < 0) {
            return LocalDateTime.now();
        }

        String timeStr = str.substring(fromIndex + 1, toIndex);
        return TimestampUtils.toLocalDateTime(parseTimestamp(timeStr));
    }

    enum Type {

        /**
         * 自定义开始和结束时间, 格式: "(1627553688000, 1629368143000)"
         */
        START_AND_END("^[\\s]*\\([0-9]*[\\s]*,[\\s]*[0-9]*[\\s]*\\)[\\s]*$"),

        /**
         * 指定的小时时间范围, 格式: "hour(1627553688000)" <br/>
         * 服务器当前时间的小时时间范围, 格式: "hour"
         */
        HOUR("^hour[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|hour$"),

        /**
         * 指定的天时间范围, 格式: "day(1627553688000)" <br/>
         * 服务器当前时间的天时间范围, 格式: "day"
         */
        DAY("^day[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|day$"),

        /**
         * 指定的工作日时间范围, 格式: "workday(1627553688000)" <br/>
         * 服务器当前时间的工作日时间范围, 格式: "workday"
         */
        WORKDAY("^workday[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|workday$"),

        /**
         * 指定的周时间范围, 格式: "week(1627553688000)" <br/>
         * 服务器当前时间的周时间范围, 格式: "week"
         */
        WEEK("^week[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|week$"),

        /**
         * 指定的月时间范围, 格式: "month(1627553688000)" <br/>
         * 服务器当前时间的月时间范围, 格式: "month"
         */
        MONTH("^month[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|month$"),

        /**
         * 指定的年时间范围, 格式: "year(1627553688000)" <br/>
         * 服务器当前时间的年时间范围, 格式: "year"
         */
        YEAR("^year[\\s]*\\([0-9]*[\\s]*\\)[\\s]*|year$")

        ;

        private final Pattern pattern;

        Type(String pattern) {
            this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        }

        public static Type matches(String str) {
            return Arrays.stream(values())
                    .filter(type -> type.pattern.matcher(str).matches())
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("TimeRange解析失败"));
        }
    }
}
