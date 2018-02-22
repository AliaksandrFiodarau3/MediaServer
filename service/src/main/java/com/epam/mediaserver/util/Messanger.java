package com.epam.mediaserver.util;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Resourse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is util for output massages
 * *
 */

public class Messanger {

    /**
     * Generates message for output
     *
     * @param message keep path in message resource bundle
     * @return String
     */

    public static void sendMessage(HttpServletResponse response, String message) throws IOException {

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        ResourceBundle bundle = ResourceBundle.getBundle(Resourse.MESSAGE);

        if (bundle.getLocale().toString().equals(Attribute.ATTRIBUTE_LOCALE_RU)) {
            bundle = ResourceBundle.getBundle(String.valueOf(Resourse.RU_MESSAGES));
        } else {
            bundle = ResourceBundle.getBundle(String.valueOf(Resourse.EN_MESSAGES));
        }

        out.print(bundle.getString(message));


    }

    public static String sendMessage(String message) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle(Resourse.MESSAGE);

        if (bundle.getLocale().equals(Attribute.ATTRIBUTE_LOCALE_EN)) {
            bundle = ResourceBundle.getBundle(String.valueOf(Resourse.EN_MESSAGES));
        }
        if (bundle.getLocale().equals(Attribute.ATTRIBUTE_LOCALE_RU)) {
            bundle = ResourceBundle.getBundle(String.valueOf(Resourse.RU_MESSAGES));
        }
        return bundle.getString(message);
    }

}
