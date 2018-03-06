package com.epam.mediaserver.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Comment extends Model {

    private User user;
    private Song song;
    private String commentText;
    private Date commentDate;
    private Time commentTime;

    public Comment() {
        super();
    }

    public Comment(User user, Song song, String commentText, Date commentDate, Time commentTime) {
        this.user = user;
        this.song = song;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.commentTime = commentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Time getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Time commentTime) {
        this.commentTime = commentTime;
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
        Comment comment = (Comment) o;
        return Objects.equals(user, comment.user) &&
               Objects.equals(song, comment.song) &&
               Objects.equals(commentText, comment.commentText) &&
               Objects.equals(commentDate, comment.commentDate) &&
               Objects.equals(commentTime, comment.commentTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), user, song, commentText, commentDate, commentTime);
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", user=" + user +
               ", song=" + song +
               ", commentText='" + commentText +
               ", commentDate=" + commentDate +
               ", commentTime=" + commentTime +
               '}';
    }
}

