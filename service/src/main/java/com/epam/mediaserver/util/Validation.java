package com.epam.mediaserver.util;

import com.epam.mediaserver.constant.Validate;

import java.sql.Time;
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

        if (login == null || login.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_PASSWORD, password)) {

            return false;
        }

        if (name == null || name.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, name)) {
            return false;
        }

        if (surname == null || surname.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, surname)) {
            return false;
        }

        if (email == null || email.isEmpty()) {
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

        if (login == null || login.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }

        if (name == null || name.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, name)) {
            return false;
        }

        if (surname == null || surname.isEmpty()) {
            return false;
        }

        if (!checkField(Validate.FIELD_NAME, surname)) {
            return false;
        }

        if (email == null || email.isEmpty()) {
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

    public static boolean userCheck(String login, Long password) {

        if (login == null) {
            return false;
        }

        if (!checkField(Validate.FIELD_LOGIN, login)) {

            return false;
        }
        if (password == null) {
            return false;
        }

        return true;
    }

    /**
     * Validate form for add new album
     */

    public static boolean albumCheck(String title, String year, String description, String image) {

        if (title == null) {
            return false;
        }
        if (year == null) {
            return false;
        }
        if (!checkField(Validate.FIELD_YEAR, year)) {
            return false;
        }
        if (description == null) {
            return false;
        }
        if (image == null) {
            return false;
        }

        return true;
    }

    /**
     * Validate form for add new artist
     */

    public static boolean artistCheck(String title, String description, String image) {

        if (title == null) {
            return false;
        }

        if (description == null) {
            return false;
        }

        if (image == null) {
            return false;
        }
        return true;
    }

    /**
     * Validate form for add new bonus
     */

    public static boolean bonusCheck(String title, String description, String discount, String code) {

        if (title == null) {
            return false;
        }

        if (description == null) {
            return false;
        }

        if (discount == null) {
            return false;
        }
        if (code == null) {
            return false;
        }

        return true;
    }


    /**
     * Validate form for add new genre
     */

    public static boolean genreCheck(String title, String description) {

        if (title == null) {
            return false;
        }

        if (description == null) {
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

        if (user == null) {
            return false;
        }

        return true;
    }

    public static boolean orderCheck(double price, String user, int number) {

        if (price < 0) {
            return false;
        }

        if (user == null) {
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

        if (title == null) {
            return false;
        }

        if (album == null) {
            return false;
        }

        if (price == null) {
            return false;
        }

        try {
            Time.valueOf(duration);
        } catch (Exception e) {
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
