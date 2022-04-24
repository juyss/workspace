package com.icepoint.base.web.resource.component.query;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public enum Operation {

    EQ(
            "equals",
            "= %s",
            strings("eq", "is", "=", "==")
    ),
    NEQ(
            "not equals",
            "!= %s",
            strings("neq", "ne", "not", "!=")
    ),
    GT(
            "greater than",
            "> %s",
            strings("gt", ">")
    ),
    GE(
            "greater than or equals",
            ">= %s",
            strings("ge", "gte", ">=")
    ),
    LT(
            "less than",
            "< %s",
            strings("lt", "<")
    ),
    LE(
            "less than or equals",
            "<= %s",
            strings("le", "lte", "<=")
    ),
    IN(
            "in",
            "IN(%s)",
            strings("in")
    ),
    BTW(
            "between",
            "BETWEEN %s AND %s",
            strings("btw")
    ),
    STARTS_WITH(
            "starts with",
            "LIKE '%s%%'",
            strings("startsWith", "sw", "=*")
    ),
    ENDS_WITH(
            "ends with",
            "LIKE '%%%s'",
            strings("endsWith", "ew", "*=")
    ),
    CONTAINS(
            "contains",
            "LIKE '%%%s%%'",
            strings("contains", "like", "*=*")
    ),
    REG(
            "regular expression",
            "REGEXP %s",
            strings("reg", "regexp")
    );

    private final String description;
    private final String formatter;
    private final String[] aliases;

    private static String[] strings(String... strings) {
        Arrays.sort(strings);
        return strings;
    }

    public static Operation from(String op) {
        Assert.hasText(op, "op must not be null or empty");
        return Arrays.stream(values())
                .filter(operation ->
                        Arrays.stream(operation.aliases()).anyMatch(alias -> Objects.equals(alias, op.trim())))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Operation.class, op));
    }

    public String description() {
        return description;
    }

    public String[] aliases() {
        return aliases;
    }

    public String formatter() {
        return formatter;
    }
}