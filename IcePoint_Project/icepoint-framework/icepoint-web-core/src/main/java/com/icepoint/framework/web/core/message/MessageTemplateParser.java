package com.icepoint.framework.web.core.message;

/**
 * @author Jiawei Zhao
 */
public interface MessageTemplateParser {

    String parse(final String template, Exception e) throws MessageParseException;
}
