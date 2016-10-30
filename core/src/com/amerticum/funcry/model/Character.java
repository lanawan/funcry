package com.amerticum.funcry.model;

import com.amerticum.funcry.Constants;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Java-программист on 24.10.2016.
 */

public class Character {
    private Array<Texture> faces;
    private Array<Music> cries;
    private int posX;
    private int posY;
    private boolean active;
    private int defaultWidth;
    private int defaultHeight;
    private Texture activeFace;
    private Music activeCry;
    private int characterState;

    public Character(Array<Texture> faces, Array<Music> cries, int posX, int posY, boolean active) {
        characterState = 0;
        defaultWidth = faces.get(0).getWidth();
        defaultHeight = faces.get(0).getHeight();
        activeFace = faces.get(characterState);
        activeCry = cries.get(characterState);
        this.faces = faces;
        this.cries = cries;
        this.posX = posX;
        this.posY = posY;
        this.active = active;
    }

/*
    private void updateMotion() {
        if (leftMove) {
            posX -= 5 * Gdx.graphics.getDeltaTime();
        }
        if (rightMove)
        {
            posX += 5 * Gdx.graphics.getDeltaTime();
        }
    }
*/


    public Texture getFace(){
        return faces.get(characterState);
    }


    public void increaseCharacterState(){
        if (cries.get(characterState).isPlaying()) {
            cries.get(characterState).stop();
        }
        characterState++;
        if(characterState >= Constants.CHARACTER_COUNT){
            characterState = 0;
        }
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
            if (!cries.get(characterState).isPlaying()){
                cries.get(characterState).play();
            }
        }
        else{
            if (cries.get(characterState).isPlaying()) {
                cries.get(characterState).pause();
            }
        }
    }

    public void dispose(){
        for(Texture t : faces) t.dispose();
        for(Music m : cries) m.dispose();
        dispose();
    }
}
