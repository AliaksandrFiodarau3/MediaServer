package by.epam.command.impl;

import by.epam.command.Command;
import by.epam.constant.Attribute;
import by.epam.constant.LocaleConst;
import by.epam.constant.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/** Command for change locale RU or EN*/

public class LocaleChange implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String prevQuery = (String) request.getSession().getAttribute(Attribute.ATTRIBUTE_PREV_QUERY);

        String lang = request.getParameter(String.valueOf(LocaleConst.LANG).toLowerCase());

        if (lang.equals(String.valueOf(LocaleConst.RU).toLowerCase())) {

            Config.set(request.getSession(), Config.FMT_LOCALE, Attribute.ATTRIBUTE_LOCALE_RU);
            request.getSession().setAttribute(String.valueOf(LocaleConst.BUNDLE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_RU);
            request.getSession().setAttribute(String.valueOf(Attribute.ATTRIBUTE_LOCALE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_RU);
        }
        if (lang.equals(String.valueOf(LocaleConst.EN).toLowerCase())) {

            Config.set(request.getSession(), Config.FMT_LOCALE, Attribute.ATTRIBUTE_LOCALE_EN);
            request.getSession().setAttribute(String.valueOf(LocaleConst.BUNDLE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_EN);
            request.getSession().setAttribute(String.valueOf(Attribute.ATTRIBUTE_CURRENT_LOCALE).toLowerCase(), Attribute.ATTRIBUTE_LOCALE_EN);
        }
        if (prevQuery != null) {
            response.sendRedirect(prevQuery);
        } else {
            response.sendRedirect(Path.PATH_TO_INDEX_PAGE);
        }
    }
}

