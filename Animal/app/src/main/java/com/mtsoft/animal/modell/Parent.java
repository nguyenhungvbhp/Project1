package com.mtsoft.animal.modell;

import java.io.Serializable;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class Parent implements Serializable{
    private int id;
    private String name;
    private int idImage;
    private int pronounce;
    private int like;

    public Parent(int id,String name, int idImage, int pronounce, int like) {
        this.id = id;
        this.name = name;
        this.idImage = idImage;
        this.pronounce = pronounce;
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdImage() {
        return idImage;
    }


    public int getPronounce() {
        return pronounce;
    }

    public void setPronounce(int idSound) {
        this.pronounce = pronounce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
