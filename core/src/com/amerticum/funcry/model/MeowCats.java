package com.amerticum.funcry.model;

import com.amerticum.funcry.Constants;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Java-программист on 24.10.2016.
 */

public class MeowCats {
    private int posX;
    private int posY;
    private boolean active;
    private int defaultWidth;
    private int defaultHeight;
    private TextureAtlas meowCatAtlas;
    private Animation meowCatAnimation;
    private Music meow;
    private int meowCatScreens;

    public MeowCats(TextureAtlas meowCatAtlas, Music meow, int posX, int posY, boolean active) {
        this.meowCatAtlas = meowCatAtlas;
        this.meow = meow;
        meowCatAnimation = new Animation(Constants.MEOW_ANIMATION_SPEED,meowCatAtlas.getRegions());
        defaultWidth = meowCatAtlas.findRegion("cat1").getRegionWidth();
        defaultHeight = meowCatAtlas.findRegion("cat1").getRegionHeight();
        this.posX = posX;
        this.posY = posY;
        this.active = active;
    }

    public Animation getMeowCatAnimation(){
        return meowCatAnimation;
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
        this.active = active;
        if(active) {
            if (!meow.isPlaying()){
                meow.play();
            }
        }
        else{
            if (meow.isPlaying()) {
                meow.pause();
            }
        }
    }

    public void dispose(){
        meow.dispose();
        meowCatAtlas.dispose();
    }
}
