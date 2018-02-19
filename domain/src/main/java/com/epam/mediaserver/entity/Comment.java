package com.epam.mediaserver.entity;

import java.sql.Date;
import java.sql.Time;

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
        if (!(o instanceof Comment)) {
            return false;
        }

        Comment comment = (Comment) o;

        if (getUser() != null ? !getUser().equals(comment.getUser()) : comment.getUser() != null) {
            return false;
        }
        if (getSong() != null ? !getSong().equals(comment.getSong()) : comment.getSong() != null) {
            return false;
        }
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText())
                                     : comment.getCommentText() != null) {
            return false;
        }
        if (getCommentDate() != null ? !getCommentDate().equals(comment.getCommentDate())
                                     : comment.getCommentDate() != null) {
            return false;
        }
        return getCommentTime() != null ? getCommentTime().equals(comment.getCommentTime())
                                        : comment.getCommentTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getSong() != null ? getSong().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        result = 31 * result + (getCommentTime() != null ? getCommentTime().hashCode() : 0);
        return result;
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

