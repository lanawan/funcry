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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Java-программист on 03.11.2016.
 */

public class Test implements Screen, InputProcessor {
    private Game game;
    private Texture background;
    private int counter=0;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPos;
    private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();
    private Array<PussyCats> CryingEntities;
    private int defaultHeight;

    public Test(Game game) {
        this.game = game;
        background = new Texture("menu/bg.png");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        //viewport = new FitViewport((float)Constants.SCREEN_WIDTH,(float)Constants.SCREEN_HEIGHT,camera);
        viewport = new StretchViewport((float) Constants.SCREEN_WIDTH,(float)Constants.SCREEN_HEIGHT,camera);
        viewport.apply();
        camera.position.set(camera.viewportHeight/2, camera.viewportHeight/2,0);
        camera.update();

        touchPos = new Vector3();

        for (int i = 0; i < Constants.CHARACTER_COUNT; i++) {
            touches.put(i, new TouchInfo());
        }

        CryingEntities = new Array<PussyCats>();
        for (int i = 1; i <= Constants.CHARACTER_COUNT; i++) {
            Array<Texture> tf = new Array<Texture>();
            Array<Music> ac = new Array<Music>();
            for (int j = 1; j <= Constants.CRIES_COUNT; j++) {
                Music cry = Gdx.audio.newMusic(Gdx.files.internal("cats/cry/sounds/" + i + "cry" + j + ".mp3"));
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
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void dispose() {
        background.dispose();
        for (PussyCats ce : CryingEntities) ce.dispose();
        touches.clear();
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)53/255, 0f, (float)49/255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0,0);
        for (PussyCats ce : CryingEntities) {
            if (ce.isActive()) {

                float screenRatioX = (float)viewport.getScreenWidth()/(float)camera.viewportWidth;
                float screenRatioY = (viewport.getScreenHeight()/camera.viewportHeight);

                batch.draw(ce.getFace(), ce.getPosX()/screenRatioX, camera.viewportHeight - (ce.getPosY()/screenRatioY));
            }
        }
        batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
            CryingEntities.get(pointer).setActive(true);
            CryingEntities.get(pointer).setPosX(screenX);
            CryingEntities.get(pointer).setPosY(screenY);
            counter++;
            if(counter > Constants.CRIES_VELOCITY){
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
            CryingEntities.get(pointer).setActive(true);
            CryingEntities.get(pointer).setPosX(screenX);
            CryingEntities.get(pointer).setPosY(screenY);
            counter++;
            if(counter > Constants.CRIES_VELOCITY){
                CryingEntities.get(pointer).increaseCharacterState();
                counter=0;
            }
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
