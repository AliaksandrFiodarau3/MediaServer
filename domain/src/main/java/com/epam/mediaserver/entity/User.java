package com.epam.mediaserver.entity;

import java.util.Objects;
public class User extends Model {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String photo;

    private Boolean adminRoot = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getAdminRoot() {
        return adminRoot;
    }

    public void setAdminRoot(Boolean adminRoot) {
        this.adminRoot = adminRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login) &&
               Objects.equals(password, user.password) &&
               Objects.equals(name, user.name) &&
               Objects.equals(surname, user.surname) &&
               Objects.equals(email, user.email) &&
               Objects.equals(photo, user.photo) &&
               Objects.equals(adminRoot, user.adminRoot);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), login, password, name, surname, email, photo, adminRoot);
    }

    @Override
    public String toString() {
        return "User{" +
               " id=" + id +
               ", user='" + login + '\'' +
               ", password=" + password +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", adminRoot=" + adminRoot +
               '}';
    }
}
