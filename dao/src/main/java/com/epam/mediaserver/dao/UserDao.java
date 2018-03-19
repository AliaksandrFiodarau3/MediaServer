package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.DAOException;

public interface UserDao{



    /**
     * Method add user photo in Data Base
     *
     * @param login for search a field in the database by user login
     * @param photo keep URL with address photo in Internet
     * @return entity of user {@link User}
     * @throws DAOException if database error was detected
     */

    void setPhoto(String photo, String login) throws DAOException;

    /**
     * Method add user photo in Data Base
     *
     * @param id for search a field in the database by user ID
     * @param root keep user rights
     * @return entity of user {@link User}
     * @throws DAOException if database error was detected
     *//*

    void setRoot(boolean root, int id) throws DAOException;*/


    /**
     * Method search user by login in Date Base and return User
     *
     * @param login for search a field in the database by name
     * @throws DAOException if database error was detected
     */


    User authorisation(String login) throws DAOException;

    /**
     * Method add new user in Data Base {@link User}
     *
     * @param login    user login
     * @param password user password
     * @param email    user email
     * @param name     user name
     * @param surname  user surname
     * @return entity of artist {@link User}
     * @throws DAOException if database error was detected
     */

    User registration(String login, String password, String email, String name, String surname) throws DAOException;

    boolean checkLogin(String login) throws DAOException;

    boolean checkEmail(String email) throws DAOException;
}
