package com.example.jobms.job;

public class Review {
    private int id;
    private int likes;
    private int dislikes;
    private String comment;
    private boolean lovedOrNot;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isLovedOrNot() {
        return lovedOrNot;
    }

    public void setLovedOrNot(boolean lovedOrNot) {
        this.lovedOrNot = lovedOrNot;
    }
}
