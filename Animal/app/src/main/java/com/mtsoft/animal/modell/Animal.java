package com.mtsoft.animal.modell;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class Animal extends Parent {
    private int sound;
    public Animal(int id, String name, int idImage, int pronounce,int like, int sound) {
        super(id, name, idImage, pronounce, like);
        this.sound = sound;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }


}
