package com.epam.mediaserver.builder.impl;

import com.epam.mediaserver.builder.Builder;
import com.epam.mediaserver.constant.Path;
import com.epam.mediaserver.entity.User;



public class UserBuilder implements Builder<User>{

    private User user;

    public UserBuilder() {
        user = new User();
        user.setLogin("EMPTY");
        user.setPassword("EMPTY");
        user.setName("EMPTY");
        user.setSurname("EMPTY");
        user.setEmail("EMPTY");
        user.setPhoto(Path.DEFAULT_USER);
        user.setAdminRoot(false);
    }

    public UserBuilder setId(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder setLogin(String login){
        user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder setName(String name){
        user.setName(name);
        return this;
    }

    public UserBuilder setSurname(String surname){
        user.setSurname(surname);
        return this;
    }

    public UserBuilder setEmail(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder setPhoto(String photo){
        user.setPhoto(photo);
        return this;
    }

    public UserBuilder setRoot(Boolean root){
        user.setAdminRoot(root);
        return this;
    }

    @Override
    public User build() {
       return user;
    }

}
