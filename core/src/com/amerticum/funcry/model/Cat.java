package com.amerticum.funcry.model;

import com.amerticum.funcry.Constants;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Java-программист on 01.11.2016.
 */

public class Cat {
    private TextureAtlas atlas;
    private Music rrr;
    private int posX;
    private int posY;
    private boolean active;
    private int defaultWidth;
    private int defaultHeight;
    private Texture activeFace;
    private Music activeCry;
    private int characterState;

    public Cat(TextureAtlas atlas, Music rrr, int posX, int posY, boolean active) {
        characterState = 0;
        this.atlas = atlas;
        this.rrr = rrr;
        this.posX = posX;
        this.posY = posY;
        this.active = active;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX - (defaultWidth/2);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY - (defaultHeight/2);
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active){
    }

    public void dispose(){
    }

}
