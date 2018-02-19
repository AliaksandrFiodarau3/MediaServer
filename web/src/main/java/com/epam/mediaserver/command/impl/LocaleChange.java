package com.epam.mediaserver.command.impl;


import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.LocaleConst;
import com.epam.mediaserver.constant.Path;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Command for change locale RU or EN
 */

public class LocaleChange implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String prevQuery = (String) request.getSession().getAttribute(Attribute.ATTRIBUTE_PREV_QUERY);

        String lang = request.getParameter(String.valueOf(LocaleConst.LANG).toLowerCase());

        if (lang.equals(String.valueOf(LocaleConst.RU).toLowerCase())) {

            Config.set(request.getSession(), Config.FMT_LOCALE, Attribute.ATTRIBUTE_LOCALE_RU);
            request.getSession()
                .setAttribute(String.valueOf(LocaleConst.BUNDLE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_RU);
            request.getSession()
                .setAttribute(String.valueOf(Attribute.ATTRIBUTE_LOCALE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_RU);
        }
        if (lang.equals(String.valueOf(LocaleConst.EN).toLowerCase())) {

            Config.set(request.getSession(), Config.FMT_LOCALE, Attribute.ATTRIBUTE_LOCALE_EN);
            request.getSession()
                .setAttribute(String.valueOf(LocaleConst.BUNDLE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_EN);
            request.getSession().setAttribute(String.valueOf(Attribute.ATTRIBUTE_CURRENT_LOCALE).toLowerCase(),
                                              Attribute.ATTRIBUTE_LOCALE_EN);
        }
        if (prevQuery != null) {
            response.sendRedirect(prevQuery);
        } else {
            response.sendRedirect(Path.PATH_TO_HOME_PAGE);
        }
    }
}

