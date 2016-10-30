package com.amerticum.funcry.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Java-программист on 25.10.2016.
 */

public class MainMenu implements Screen {
    private Stage stage;
    private Texture background;
    private ImageButton buttonFun, buttonCry;
    private TextureAtlas buttonAtlas;
    private ImageButtonStyle buttonStyleFun, buttonStyleCry;
    private Skin skin;
    private SpriteBatch batch;
    private Game game;


    public MainMenu(Game game) {
        this.game = game;
    }


    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        background = new Texture("bg.png");

        skin = new Skin();
        buttonAtlas = new TextureAtlas("buttons/button.pack");
        skin.addRegions(buttonAtlas);

        buttonStyleFun = new ImageButtonStyle();
        buttonStyleFun.up = skin.getDrawable("buttonFunUp");
        buttonStyleFun.over = skin.getDrawable("buttonFunOver");
        buttonStyleFun.down = skin.getDrawable("buttonFunOver");

        buttonStyleCry = new ImageButtonStyle();
        buttonStyleCry.up = skin.getDrawable("buttonCryUp");
        buttonStyleCry.over = skin.getDrawable("buttonCryOver");
        buttonStyleCry.down = skin.getDrawable("buttonCryOver");

        buttonFun = new ImageButton(buttonStyleFun);
        float posButFun = (Gdx.graphics.getWidth() - 2*buttonFun.getWidth()) / 3;
        buttonFun.setPosition(posButFun,20);
        buttonCry = new ImageButton(buttonStyleCry);
        float posButCry = 2*posButFun + buttonFun.getWidth();
        buttonCry.setPosition(posButCry,20);

        stage.addActor(buttonFun);
        stage.addActor(buttonCry);
        Gdx.input.setInputProcessor(stage);

        buttonFun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                stage.clear();
                return true;
            }
        });

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        batch.begin();
        stage.draw();
        batch.end();

    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

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
}