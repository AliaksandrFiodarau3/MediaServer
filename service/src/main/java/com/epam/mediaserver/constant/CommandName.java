package com.epam.mediaserver.constant;

/**
 * Enum constant for available commands
 */

public enum CommandName {
    /*Admin command*/
    UPDATE_USER_TABLE, UPDATE_BONUS_TABLE,

    UPDATE_GENRE_LIST, UPDATE_ALBUM_LIST, UPDATE_ARTIST_LIST, UPDATE_SONG_LIST,
    //Create
    ADD_ALBUM, ADD_ARTIST, ADD_BONUS, ADD_GENRE, ADD_ORDER, ADD_SONG, ADD_USER,
    //Read
    SHOW_ALBUM, SHOW_ARTIST, SHOW_BONUS, SHOW_GENRE, SHOW_ORDER, SHOW_SONG, SHOW_USER, SHOW_GOODS, SHOW_COMMENT, SHOW_USER_GOODS,

    SHOW_USER_LIST, GET_PAGES,
    //Update
    EDIT_ALBUM, EDIT_ARTIST, EDIT_BONUS, EDIT_GENRE, EDIT_ORDER, EDIT_SONG, EDIT_USER,
    //Delete
    DELETE_ALBUM, DELETE_ARTIST, DELETE_BONUS, DELETE_GENRE, DELETE_ORDER, DELETE_SONG, DELETE_USER, DELETE_GOOD, DELETE_USER_GOOD,

    GET_GENRE,
    /*User command*/
    ALBUM_SHOW, ARTIST_SHOW, SONG_SHOW,/* COMMENT_SHOW,*/ CONTENT_SHOW, ORDER_SHOW, SEND_MESSAGE,

    CHECK_EMAIL, CHECK_LOGIN, ADD_GOOD, USE_BONUS, BUY,

    /*Guest command*/
    HOME,
    SIGN_OUT,
    SIGN_UP,
    SIGN_IN,
    CHANGE_LOCALE

}
