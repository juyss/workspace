package com.icepoint.base.sql;

import java.util.Map;
import java.util.regex.Pattern;

public interface SqlFormat {

    String getFormat();

    String formatAll(Map<String, String> formatMap);

    String format(String formatTarget, String sql);

    Pattern getFormatPattern();

    boolean resolved();
}
