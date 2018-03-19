package com.epam.mediaserver.entity;

public class User extends Model {

    private String login;
    private long password;
    private String name;
    private String surname;
    private String email;
    private String photo;
    private boolean adminRoot = false;

    public User() {
    }

    public User(Long id, String login, long password, String name, String surname, String email, String photo,
                boolean adminRoot) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photo = photo;
        this.adminRoot = adminRoot;
    }

    public User(String login, long password, String name, String surname, String email, boolean adminRoot) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.adminRoot = adminRoot;
    }

    public User(String login, long password, String name, String surname, String email, String photo,
                boolean adminRoot) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photo = photo;
        this.adminRoot = adminRoot;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
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

    public boolean isAdminRoot() {
        return adminRoot;
    }

    public void setAdminRoot(boolean adminRoot) {
        this.adminRoot = adminRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (getPassword() != user.getPassword()) {
            return false;
        }
        if (isAdminRoot() != user.isAdminRoot()) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        return getPhoto() != null ? getPhoto().equals(user.getPhoto()) : user.getPhoto() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (int) (getPassword() ^ (getPassword() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoto() != null ? getPhoto().hashCode() : 0);
        result = 31 * result + (isAdminRoot() ? 1 : 0);
        return result;
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
