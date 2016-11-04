package com.amerticum.funcry.screens;


import com.amerticum.funcry.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Java-программист on 25.10.2016.
 */

public class MainMenu implements Screen {
    private Game game;
    private Stage stage;
    private Texture background;
    private ImageButton buttonFun, buttonCry;
    private TextureAtlas buttonAtlas;
    private ImageButtonStyle buttonStyleFun, buttonStyleCry;
    private Skin skin;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;


    public MainMenu(Game game) {
        this.game = game;
        background = new Texture("menu/bg.png");
        buttonAtlas = new TextureAtlas("buttons/button.pack");
        skin = new Skin(buttonAtlas);
        //skin.addRegions(buttonAtlas);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        //viewport = new FitViewport((float)Constants.SCREEN_WIDTH,(float)Constants.SCREEN_HEIGHT,camera);
        viewport = new StretchViewport((float)Constants.SCREEN_WIDTH,(float)Constants.SCREEN_HEIGHT,camera);
        viewport.apply();
        camera.position.set(camera.viewportHeight/2, camera.viewportHeight/2,0);
        camera.update();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void dispose() {
        background.dispose();
        buttonAtlas.dispose();
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void show() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.bottom();

        //mainTable.setBackground(background);

        buttonStyleFun = new ImageButtonStyle();
        buttonStyleFun.up = skin.getDrawable("buttonFunUp");
        buttonStyleFun.checked = skin.getDrawable("buttonFunOver");
        buttonStyleFun.over = skin.getDrawable("buttonFunOver");
        buttonStyleFun.down = skin.getDrawable("buttonFunOver");

        buttonStyleCry = new ImageButtonStyle();
        buttonStyleCry.up = skin.getDrawable("buttonCryUp");
        buttonStyleCry.checked = skin.getDrawable("buttonCryOver");
        buttonStyleCry.over = skin.getDrawable("buttonCryOver");
        buttonStyleCry.down = skin.getDrawable("buttonCryOver");

        buttonFun = new ImageButton(buttonStyleFun);
        float posButFun = 30;
        buttonFun.setPosition(posButFun,20);
        buttonCry = new ImageButton(buttonStyleCry);
        float posButCry = Constants.SCREEN_WIDTH - posButFun - buttonFun.getWidth();
        buttonCry.setPosition(posButCry,20);

        mainTable.add(buttonFun);
        mainTable.row();
        mainTable.add(buttonCry);

        stage.addActor(buttonFun);
        stage.addActor(buttonCry);

        buttonFun.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new RrrCatPlayScreen(game));
                stage.clear();
                return true;
            }
        });
        buttonCry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MeowCatsPlayScreen(game));
                stage.clear();
                return true;
            }
        });
        Gdx.input.setCatchBackKey(false);
      }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)53/255, 0f, (float)49/255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        stage.act();
        stage.draw();

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
}