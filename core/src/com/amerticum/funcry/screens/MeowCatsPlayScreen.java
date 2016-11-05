package com.amerticum.funcry.screens;

import com.amerticum.funcry.Constants;
import com.amerticum.funcry.model.MeowCats;
import com.amerticum.funcry.model.TouchInfo;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Java-программист on 03.11.2016.
 */

public class MeowCatsPlayScreen implements Screen, InputProcessor {
    private Game game;
    private Texture background;
    private int counter=0;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPos;
    private float elapsedTime = 0;
    private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();
    private Array<MeowCats> meowCats;
    private int defaultHeight;

    public MeowCatsPlayScreen(Game game) {
        this.game = game;
        background = new Texture("menu/bg.png");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport((float) Constants.SCREEN_WIDTH,(float)Constants.SCREEN_HEIGHT,camera);
        viewport.apply();
        camera.position.set(camera.viewportHeight/2, camera.viewportHeight/2,0);
        camera.update();

        touchPos = new Vector3();

        for (int i = 0; i < Constants.CAT_COUNT; i++) {
            touches.put(i, new TouchInfo());
        }

        meowCats = new Array<MeowCats>();

        for (int i = 1; i <= Constants.CAT_COUNT; i++) {
            TextureAtlas catAtlas = new TextureAtlas("cats/cry/images/cat"+i+".pack");
            Music meow = Gdx.audio.newMusic(Gdx.files.internal("cats/cry/sounds/cry" + i + ".mp3"));
            meow.setLooping(true);
            meow.setVolume(0.1f);
            meowCats.add(new MeowCats(catAtlas, meow, 0, 0, false));
        }
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }
    @Override
    public void dispose() {
        background.dispose();
        for (MeowCats ce : meowCats) ce.dispose();
        touches.clear();
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        elapsedTime += delta;

        if(elapsedTime>=Constants.MAX_FRAMES){ elapsedTime = 0; }

        Gdx.gl.glClearColor((float)53/255, 0f, (float)49/255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0,0);
        for (MeowCats ce : meowCats) {
            if (ce.isActive()) {

                float screenRatioX = (float)viewport.getScreenWidth()/camera.viewportWidth;
                float screenRatioY = (float)viewport.getScreenHeight()/camera.viewportHeight;

                batch.draw(ce.getMeowCatAnimation().getKeyFrame(elapsedTime, true), ce.getPosX()/screenRatioX, camera.viewportHeight - (ce.getPosY()/screenRatioY));
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
        if(keycode == Keys.BACK) {
            dispose();
            game.setScreen(new MainMenu(game));
            return true;
        }
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
        if (pointer < Constants.CAT_COUNT) {
            meowCats.get(pointer).setActive(true);
            meowCats.get(pointer).setPosX(screenX);
            meowCats.get(pointer).setPosY(screenY);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < Constants.CAT_COUNT){
            meowCats.get(pointer).setActive(false);
            meowCats.get(pointer).setPosX(0);
            meowCats.get(pointer).setPosY(0);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer < Constants.CAT_COUNT) {
            meowCats.get(pointer).setActive(true);
            meowCats.get(pointer).setPosX(screenX);
            meowCats.get(pointer).setPosY(screenY);
            //meowCats.get(pointer).set
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
