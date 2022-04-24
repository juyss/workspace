package com.icepoint.framework.web.core.message;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 默认的消息模版解析器，可以解析大括号内容的变量，会从异常类对象中取其变量名对应的getter方法获取值，
 * 如果不存在getter或者值是null，内容会被替换为null
 *
 * @author Jiawei Zhao
 */
@Component
public class DefaultMessageTemplateParser implements MessageTemplateParser {

    private static final Pattern MESSAGE_VAR_FINDER = Pattern.compile("\\{[^}]*}");

    @Override
    public String parse(final String template, Exception e) throws MessageParseException {

        Matcher matcher = MESSAGE_VAR_FINDER.matcher(template);

        List<ReplaceContent> replaceContents = new ArrayList<>();
        int offset = 0;
        // 标记当前替换内容是否第一个替换内容，用作计算偏移量用的
        boolean first = true;

        while (matcher.find()) {

            String varName = matcher.group();
            varName = varName.substring(1, varName.length() - 1);

            String str;
            PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(e.getClass(), varName);
            if (descriptor != null) {
                try {
                    Object obj = ReflectionUtils.invokeMethod(descriptor.getReadMethod(), e);
                    str = obj != null && obj.getClass().isArray()
                            ? Arrays.toString((Object[]) obj)
                            : String.valueOf(obj);
                } catch (Exception exception) {
                    throw new MessageParseException(exception);
                }
            } else {
                str = "null";
            }

            // 字符串被更新后，更新内容与原本的内容长度不一致，会导致要替换的下标发生变化
            // 这里需要计算原内容与新内容之间的偏移量，到时候替换字符串的时候算进去
            int start = matcher.start();
            int end = matcher.end();

            // 前一个替换内容的偏移量是给下一个替换内容做偏移用的
            // 所以要记录上一个偏移量，再更新当前偏移量，给下一个替换内容做偏移
            // 第一个替换内容没有偏移量
            replaceContents.add(ReplaceContent.of(start, end, first ? 0 : offset, str));
            offset = str.length() - (end - start);

            first = false;
        }

        // 替换消息模版为解析后的消息
        StringBuilder sb = new StringBuilder(template);
        replaceContents.forEach(replaceContent -> replaceContent.replace(sb));

        return sb.toString();

    }

    @RequiredArgsConstructor(staticName = "of")
    @Data
    private static class ReplaceContent {

        private final int start;

        private final int end;

        private final int offset;

        private final String content;

        public void replace(StringBuilder sb) {
            sb.replace(start + offset, end + offset, content);
        }
    }
}
