package com.epam.mediaserver.builder.impl;


import com.epam.mediaserver.builder.Builder;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class CommentBuilder implements Builder<Comment> {

    private Comment comment;

    public CommentBuilder() {

        comment = new Comment();
        comment.setCommentTime(Time.valueOf(LocalTime.now()));
        comment.setCommentDate(Date.valueOf(LocalDate.now()));
    }

    public CommentBuilder setId(Long id){
        comment.setId(id);
        return this;
    }

    public CommentBuilder setUser(User user) {
        comment.setUser(user);
        return this;
    }

    public CommentBuilder setSong(Song song) {
        comment.setSong(song);
        return this;
    }

    public CommentBuilder setCommentText(String commentText) {
        comment.setCommentText(commentText);
        return this;
    }

    public CommentBuilder setData(Date date) {
        comment.setCommentDate(date);
        return this;
    }

    public CommentBuilder setTime(Time time) {
        comment.setCommentTime(time);
        return this;
    }

    @Override
    public Comment build() {
        return comment;
    }
}
