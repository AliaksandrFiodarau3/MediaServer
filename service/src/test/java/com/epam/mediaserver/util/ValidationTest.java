/*
package com.epam.mediaserver.util;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Validation test case")
class ValidationTest {

    @ParameterizedTest
    @CsvSource({"15, LOGIN, NAME, SURNAME, Name_Surname@gmail.com"})
    void userCheck1(int id, String login, String name, String surname, String email) {
        assertThat(Validation.userCheck(id, login, name, surname, email), equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"f, 1, 2, 3, 4, 5 ",
                "1, null, null, null, null",
                "1, login, name, surname,email"})
    void userCheckWrong1(String id, String login, String name, String surname, String email) {
        assertThat(Validation.userCheck(id, login, name, surname, email),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"LOGIN, Qwerty1234, NAME, SURNAME, Name_Surname@gmail.com"})
    void userCheck2(String login, String password, String name, String surname, String email) {

        assertThat(Validation.userCheck(login, password, name, surname, email), equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"f, 1, 2, 3, 4, 5 ",
                "1, null, null, null, null",
                "login, password, name, surname,email"})
    void userCheckWrong2(String login, String password, String name, String surname, String email) {
        assertThat(Validation.userCheck(login, password, name, surname, email),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"LOGIN, Qwerty1234"})
    void userCheck3(String login, String password) {
        assertThat(Validation.userCheck(login, password), equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"foo, 1", "qu, 3", "q, sas", "null,null"})
    void userCheckWrong3(String login, String password) {
        assertThat(Validation.userCheck(login, password),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"Nirvana, 1990, Nirvana, Image"})
    void albumCheck(String title, String year, String description, String image) {
        assertThat(Validation.albumCheck(title, year, description, image),
                   equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"Nirvana, , Nirvana, Image",
                ", 1990, Nirvana, Image",
                "Nirvana, 1990, , Image",
                "Nirvana, 1990, Nirvana, "})
    void albumCheckWrong(String title, String year, String description, String image) {
        assertThat(Validation.albumCheck(title, year, description, image),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"title, description, image"})
    void artistCheck(String title, String description, String image) {
        assertThat(Validation.artistCheck(
            title, description, image),
                   equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({", description, image",
                "title, , image",
                "title, description, "})
    void artistCheckWrong(String title, String description, String image) {
        assertThat(Validation.artistCheck(
            title, description, image),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"title, description, 15 ,code"})
    void bonusCheck(String title, String description, String discount, String code) {
        assertThat(Validation.bonusCheck(title, description, discount, code),
                   equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({" , description, 15 ,code",
                "title, , 15 ,code",
                "title, description,  ,code",
                "title, description, 15 , "})
    void bonusCheckWrong(String title, String description, String discount, String code) {
        assertThat(Validation.bonusCheck(title, description, discount, code),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"title, description"})
    void genreCheck(String title, String description) {

        assertThat(Validation.genreCheck(title, description), equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({", description",
                "title, "})
    void genreCheckWrong(String title, String description) {

        assertThat(Validation.genreCheck(title, description), equalTo(false));
    }


    @ParameterizedTest
    @CsvSource({"2.5, title, 5"})
    void orderCheck1(double price, String user, int number) {
        assertThat(Validation.orderCheck(
            price, user, number),
                   equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"-5.5, title, 15",
                "2.5, , 15",
                "2.5, title,-3"})
    void orderCheckWrong1(double price, String user, int number) {
        assertThat(Validation.orderCheck(
            price, user, number),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"5.5, User"})
    void orderCheck2(double price, String user) {
        assertThat(Validation.orderCheck(price, user),
                   equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"-3, User",
                "-3, "})
    void orderCheckWrong2(double price, String user) {
        assertThat(Validation.orderCheck(price, user),
                   equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({"title, album, 00:00:15, 15"})
    void songCheck(String title, String album, String duration, String price) {
        assertThat(Validation.songCheck(title, album, duration, price), equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({", album, 00:00:15, 15",
                "title, , 00:00:15, 15",
                "title, album, , 15",
                "title, album, 00:00:15, "})
    void songCheckWrong(String title, String album, String duration, String price) {
        assertThat(Validation.songCheck(title, album, duration, price), equalTo(false));
    }
}*/
