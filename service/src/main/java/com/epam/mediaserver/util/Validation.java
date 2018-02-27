package com.epam.mediaserver.util;

import com.epam.mediaserver.constant.Validate;
import com.epam.mediaserver.exception.ValidateException;

import java.sql.Time;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util for validate form
 */

public class Validation {

    /**
     * Validate form for add new user
     */

    public static boolean userCheck(String login, String password, String name, String surname, String email) {

        if (Objects.isNull(login) || login.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }

        if (Objects.isNull(password) || password.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_PASSWORD, password)) {

            return false;
        }

        if (Objects.isNull(name) || name.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, name)) {
            return false;
        }

        if (Objects.isNull(surname) || surname.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, surname)) {
            return false;
        }

        if (Objects.isNull(email) || email.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_EMAIL, email)) {
            return false;
        }

        return true;
    }

    /**
     * Validate form for update user field.
     */


    public static boolean userCheck(int id, String login, String name, String surname, String email) {

        if (id <= 0) {
            return false;
        }

        if (Objects.isNull(login) || login.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }

        if (Objects.isNull(name) || name.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, name)) {
            return false;
        }

        if (Objects.isNull(surname) || surname.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, surname)) {
            return false;
        }

        if (Objects.isNull(email) || email.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_EMAIL, email)) {
            return false;
        }

        return true;
    }


    /**
     * Validate form for authorization user
     */

    public static boolean userCheck(String login, String password) {

        if (login == null) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }
        if (password == null) {
            return false;
        }

        if (!checkField(Validate.FIELD_PASSWORD, password)) {

            return false;
        }

        return true;
    }

    /**
     * Validate form for add new album
     */

    public static boolean albumCheck(String title, String year, String description, String image) {

        if (Objects.isNull(title)) {
            return false;
        }
        if (Objects.isNull(year)) {
            return false;
        }
        if (!checkField(Validate.FIELD_YEAR, year)) {
            return false;
        }
        if (Objects.isNull(description)) {
            return false;
        }
        if (Objects.isNull(image)) {
            return false;
        }

        return true;
    }

    /**
     * Validate form for add new artist
     */

    public static boolean artistCheck(String title, String description, String image) {

        if (Objects.isNull(title)) {
            return false;
        }

        if (Objects.isNull(description)) {
            return false;
        }

        if (Objects.isNull(image)) {
            return false;
        }
        return true;
    }

    /**
     * Validate form for add new bonus
     */

    public static boolean bonusCheck(String title, String description, String discount, String code) {

        if (Objects.isNull(title)) {
            return false;
        }

        if (Objects.isNull(description)) {
            return false;
        }

        if (Objects.isNull(discount)) {
            return false;
        }
        if (Objects.isNull(code)) {
            return false;
        }

        return true;
    }


    /**
     * Validate form for add new genre
     */

    public static boolean genreCheck(String title, String description) {

        if (Objects.isNull(title)) {
            return false;
        }

        if (Objects.isNull(description)) {
            return false;
        }

        return true;
    }

    /**
     * Validate form for add new order
     */

    public static boolean orderCheck(double price, String user) {

        if (price < 0) {
            return false;
        }

        if (Objects.isNull(user)) {
            return false;
        }

        return true;
    }

    public static boolean orderCheck(double price, String user, int number) {

        if (price < 0) {
            return false;
        }

        if (Objects.isNull(user)) {
            return false;
        }

        if (number < 0) {
            return false;
        }

        return true;
    }


    /**
     * Validate form for add new song
     */

    public static boolean songCheck(String title, String album, String duration, String price) {

        if (Objects.isNull(title)) {
            return false;
        }

        if (Objects.isNull(album)) {
            return false;
        }

        if (Objects.isNull(price)) {
            return false;
        }

        if (Objects.isNull(Time.valueOf(duration))) {
            return false;
        }

        return true;
    }

    /**
     * Method for comparison reg exp and input string
     */

    private static boolean checkField(String reg, String testString) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(testString);

        return m.matches();
    }

}
