package com.juyss.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyLocaleResolver
 * @Desc:
 * @package com.juyss.config
 * @project Kuang-EMS
 * @date 2020/10/23 15:49
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getParameter("lang");
        Locale locale = Locale.getDefault();

        if (!StringUtils.isEmpty(language)){
            String[] s = language.split("_");
            locale = new Locale(s[0],s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
