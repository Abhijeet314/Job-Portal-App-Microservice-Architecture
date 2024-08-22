package com.example.reviewms.Reviews;

import jakarta.persistence.*;

@Entity
public class review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int likes;
    private int dislikes;
    private String comment;
    private boolean lovedOrNot;
    private int companyId;


    public review() {

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

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
