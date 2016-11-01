package com.amerticum.funcry.screens;

import com.amerticum.funcry.Constants;
import com.amerticum.funcry.model.PussyCats;
import com.amerticum.funcry.model.TouchInfo;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Java-программист on 24.10.2016.
 */

public class PussyCatsPlayScreen implements Screen, InputProcessor {
    private Array<PussyCats> CryingEntities;
    private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();
    private int defaultHeight;
    private int counter=0;
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Vector3 touchPos;

    public PussyCatsPlayScreen(Game game) {
        this.game = game;
        touchPos = new Vector3();

        for (int i = 0; i < Constants.CHARACTER_COUNT; i++) {
            touches.put(i, new TouchInfo());
        }

        CryingEntities = new Array<PussyCats>();
        for (int i = 1; i <= Constants.CHARACTER_COUNT; i++) {
            Array<Texture> tf = new Array<Texture>();
            Array<Music> ac = new Array<Music>();
            for (int j = 1; j <= Constants.CRIES_COUNT; j++) {
                Music cry = Gdx.audio.newMusic(Gdx.files.internal("cats/cry/sounds/"+i + "cry" + j + ".mp3"));
                cry.setLooping(true);
                cry.setVolume(0.1f);
                ac.add(cry);
                if(j==1){
                    defaultHeight = new Texture("cats/cry/images/"+i+"image" + j + ".png").getHeight();
                }
                tf.add(new Texture("cats/cry/images/"+i+"image" + j + ".png"));
            }

            CryingEntities.add(new PussyCats(tf, ac, 0, 0, false));
        }
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

    }
    @Override
    public void show(){

    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)53/255, 0f, (float)49/255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.update();
		//batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (PussyCats ce : CryingEntities) {
            if (ce.isActive()) {
                batch.draw(ce.getFace(), ce.getPosX() , Constants.SCREEN_HEIGHT-ce.getPosY());
            }
        }
        batch.end();

    }
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
    @Override
    public void dispose() {
        for (PussyCats ce : CryingEntities) ce.dispose();
        touches.clear();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer < Constants.CHARACTER_COUNT) {
            //camera.unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0));

            CryingEntities.get(pointer).setActive(true);
            CryingEntities.get(pointer).setPosX(Gdx.input.getX(pointer));
            CryingEntities.get(pointer).setPosY(Gdx.input.getY(pointer));
            counter++;
            if(counter > Constants.VELOCITY){
                CryingEntities.get(pointer).increaseCharacterState();
                counter=0;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < Constants.CHARACTER_COUNT){
            CryingEntities.get(pointer).setActive(false);
            CryingEntities.get(pointer).setPosX(0);
            CryingEntities.get(pointer).setPosY(0);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer < Constants.CHARACTER_COUNT) {
            //camera.unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0));

            CryingEntities.get(pointer).setActive(true);
            CryingEntities.get(pointer).setPosX(Gdx.input.getX(pointer));
            CryingEntities.get(pointer).setPosY(Gdx.input.getY(pointer));
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
