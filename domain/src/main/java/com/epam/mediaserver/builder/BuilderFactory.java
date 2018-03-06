package com.epam.mediaserver.builder;

import com.epam.mediaserver.builder.impl.ArtistBuilder;
import com.epam.mediaserver.builder.impl.CommentBuilder;
import com.epam.mediaserver.builder.impl.OrderBuilder;
import com.epam.mediaserver.builder.impl.UserBuilder;
import com.epam.mediaserver.entity.Order;

public class BuilderFactory {

    private BuilderFactory(){}

    public static UserBuilder getUserBuilder() {
        return new UserBuilder();
    }

    public static ArtistBuilder getArtistBuilder(){
        return new ArtistBuilder();
    }

    public static OrderBuilder getOrderBuilder(){return new OrderBuilder();}

    public static CommentBuilder getCommentBuilder(){return new CommentBuilder();}

}
