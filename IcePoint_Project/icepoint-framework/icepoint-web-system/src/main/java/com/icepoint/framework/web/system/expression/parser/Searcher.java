package com.icepoint.framework.web.system.expression.parser;

import com.icepoint.framework.web.system.expression.ExpressionContext;
import org.springframework.expression.ParseException;
import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jiawei Zhao
 */
public abstract class Searcher<T> {

    private static final String PATTERN_FORMAT = "\\s+\\Q%s\\E\\s+";

    private static final char TRIM_CHAR = ' ';

    private final String expressionString;

    private final ExpressionContext context;

    protected Searcher(String expressionString, ExpressionContext context) {
        this.expressionString = expressionString;
        this.context = context;
    }

    @Nullable
    public T first(int fromIndex) throws ParseException {

        T result = null;
        int lastStartPos = 0;

        for (String def : getDefinitions()) {

            Matcher matcher = Pattern.compile(String.format(PATTERN_FORMAT, def), Pattern.CASE_INSENSITIVE)
                    .matcher(expressionString);

            if (matcher.find(fromIndex)) {

                String substring = expressionString.substring(matcher.start(), matcher.end());
                int startOffset = getStartOffset(substring);
                int endOffset = getEndOffset(substring);

                int startPos = matcher.start() + startOffset;
                int endPos = matcher.end() + endOffset;

                // 比之前查询的结果更前面的
                if (result == null || startPos < lastStartPos) {
                    result = create(def, startPos, endPos);
                    lastStartPos = startPos;
                }
            }
        }

        return result;
    }

    private int getStartOffset(String substring) {
        int offset = 0;
        for (char c : substring.toCharArray()) {
            if (c == TRIM_CHAR) {
                offset++;
            } else {
                break;
            }
        }
        return offset;
    }

    private int getEndOffset(String def) {
        int offset = 0;
        char[] charArray = def.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] == TRIM_CHAR) {
                offset--;
            } else {
                break;
            }
        }
        return offset;
    }

    protected abstract String[] getDefinitions();

    protected abstract T create(String def, int startPos, int endPos);

    protected ExpressionContext getContext() {
        return this.context;
    }
}
