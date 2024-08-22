package com.example.reviewms.Reviews;

public class dtoReview {
    private int id;
    private int likes;
    private int dislikes;
    private String comment;
    private boolean lovedOrNot;
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isLovedOrNot() {
        return lovedOrNot;
    }

    public void setLovedOrNot(boolean lovedOrNot) {
        this.lovedOrNot = lovedOrNot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
