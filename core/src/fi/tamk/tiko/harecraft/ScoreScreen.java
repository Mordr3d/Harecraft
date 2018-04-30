package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static fi.tamk.tiko.harecraft.GameMain.orthoCamera;
import static fi.tamk.tiko.harecraft.GameMain.sBatch;
import static fi.tamk.tiko.harecraft.GameMain.shapeRenderer;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.END;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.EXIT;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.START;
import static fi.tamk.tiko.harecraft.GameScreen.SCREEN_HEIGHT;
import static fi.tamk.tiko.harecraft.GameScreen.SCREEN_WIDTH;
import static fi.tamk.tiko.harecraft.GameScreen.game;
import static fi.tamk.tiko.harecraft.GameScreen.gameState;
import static fi.tamk.tiko.harecraft.GameScreen.gameStateTime;
import static fi.tamk.tiko.harecraft.GameScreen.paused;
import static fi.tamk.tiko.harecraft.GameScreen.playerScore;
import static fi.tamk.tiko.harecraft.GameScreen.stage;
import static fi.tamk.tiko.harecraft.GameScreen.world;
import static fi.tamk.tiko.harecraft.MainMenu.localizationBundle;
import static fi.tamk.tiko.harecraft.MainMenu.profiles;

public class ScoreScreen extends ScreenAdapter implements GestureDetector.GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    Stage stage;

    float scoreboard_opacity;
    float yPos = SCREEN_HEIGHT/1.5f;
    float originalLineHeight3 = Assets.font3.getLineHeight();
    float originalLineHeight4 = Assets.font4.getLineHeight();

    public ScoreScreen() {
        sBatch.setProjectionMatrix(orthoCamera.combined);
        shapeRenderer.setProjectionMatrix(orthoCamera.combined);
        stage = new Stage(new ScreenViewport(orthoCamera));
        Gdx.input.setInputProcessor(stage);


        Assets.font3.getData().setLineHeight(Assets.font3.getLineHeight()/1.3f);
        Assets.font4.getData().setLineHeight(Assets.font3.getLineHeight()/1.3f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                Assets.skin_menu.getDrawable("listbutton"),
                Assets.skin_menu.getDrawable("listbutton pressed"),
                Assets.skin_menu.getDrawable("listbutton"),
                Assets.font3);

        style.pressedOffsetX = 4;
        style.pressedOffsetY = -4;
        style.downFontColor = new Color(0.59f,0.59f,0.59f,1f);
        style.fontColor = new Color(1f,1f,1f,1f);
        TextButton btnNewGame = new TextButton(localizationBundle.get("btnNewGameText"), style);
        if(localizationBundle.get("btnNewGameText").equals("uusi\nlento")) btnNewGame.setWidth(280f);
        else btnNewGame.setWidth(280f);
        btnNewGame.setHeight(160f);
        if(SCREEN_WIDTH >= 1280f) btnNewGame.setPosition(SCREEN_WIDTH/2f - btnNewGame.getWidth()/2f,SCREEN_HEIGHT/6f - btnNewGame.getHeight()/2f);
        else btnNewGame.setPosition(SCREEN_WIDTH/2f - btnNewGame.getWidth()/2f,SCREEN_HEIGHT/7f - btnNewGame.getHeight()/2f);
        btnNewGame.setName("btnNewGame");
        btnNewGame.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched) {
                    Assets.font3.getData().setLineHeight(originalLineHeight3);
                    Assets.font4.getData().setLineHeight(originalLineHeight4);
                    game.setScreen(new GameScreen(game, GameScreen.index));
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        style = new TextButton.TextButtonStyle(
                Assets.skin_menu.getDrawable("button"),
                Assets.skin_menu.getDrawable("button pressed"),
                Assets.skin_menu.getDrawable("button"),
                Assets.font4);

        style.pressedOffsetX = 4;
        style.pressedOffsetY = -4;
        style.downFontColor = new Color(0.59f,0.59f,0.59f,1f);
        style.fontColor = new Color(1f,1f,1f,1f);
        TextButton btnCourseSelect = new TextButton(localizationBundle.get("btnCourseSelectText"), style);
        if(localizationBundle.get("btnNewGameText").equals("uusi\nlento")) btnCourseSelect.setWidth(240f);
        else btnCourseSelect.setWidth(220f);
        btnCourseSelect.setHeight(120f);
        if(SCREEN_WIDTH >= 1280f)  btnCourseSelect.setPosition(3f/4f * SCREEN_WIDTH - btnCourseSelect.getWidth()/2f,SCREEN_HEIGHT/6f - btnCourseSelect.getHeight()/2f);
        else btnCourseSelect.setPosition(5f/6f * SCREEN_WIDTH - btnCourseSelect.getWidth()/2f,SCREEN_HEIGHT/7f - btnCourseSelect.getHeight()/2f);
        btnCourseSelect.setName("btnReset");
        btnCourseSelect.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched) {
                    Assets.font4.getData().setLineHeight(originalLineHeight4);
                    Assets.font3.getData().setLineHeight(originalLineHeight3);
                    game.setScreen(new LevelSelectMenu(game, profiles));
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        TextButton btnMainMenu = new TextButton(localizationBundle.get("btnMainMenuText"), style);
        if(localizationBundle.get("btnNewGameText").equals("uusi\nlento")) btnMainMenu.setWidth(240f);
        else btnMainMenu.setWidth(220f);
        btnMainMenu.setHeight(120f);
        if(SCREEN_WIDTH >= 1280f) btnMainMenu.setPosition(SCREEN_WIDTH/4f - btnMainMenu.getWidth()/2f,SCREEN_HEIGHT/6f - btnMainMenu.getHeight()/2f);
        else btnMainMenu.setPosition(SCREEN_WIDTH/6f - btnMainMenu.getWidth()/2f,SCREEN_HEIGHT/7f - btnMainMenu.getHeight()/2f);
        btnMainMenu.setName("btnMainMenu");
        btnMainMenu.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched) {
                    Assets.font4.getData().setLineHeight(originalLineHeight4);
                    Assets.font3.getData().setLineHeight(originalLineHeight3);
                    game.setScreen(new MainMenu(game,false));
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        stage.addActor(btnNewGame);
        stage.addActor(btnCourseSelect);
        stage.addActor(btnMainMenu);

        if(world instanceof WorldSea) {
            Gdx.gl.glClearColor(32/255f, 137/255f, 198/255f, 1f);
        }
        else if(world instanceof WorldSummer) {
            Gdx.gl.glClearColor(137/255f, 189/255f, 255/255f, 1f);
        }
        else if(world instanceof WorldTundra) {
            Gdx.gl.glClearColor(60/255f, 140/255f, 208/255f, 1f);
        }
    }

    public void drawScoreboard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f, 0f, 0f, 0.3f * scoreboard_opacity);

        float x = SCREEN_WIDTH/10f;
        float y = SCREEN_HEIGHT/3f;
        float height = SCREEN_HEIGHT/1.7f;
        float width = SCREEN_WIDTH/1.25f;

        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.arc(x, y,10f, 180f, 90f);
        shapeRenderer.arc(x,y + height,10f, 90f, 90f);
        shapeRenderer.arc(x + width, y,10f, 270f, 90f);
        shapeRenderer.arc(x + width,y + height,10f, 0f, 90f);
        shapeRenderer.rect(x,y - 10f, width,10f);
        shapeRenderer.rect(x,y + height, width,10f);
        shapeRenderer.rect(x - 10f, y,10f, height);
        shapeRenderer.rect(x + width, y,10f, height);
        shapeRenderer.end();

        sBatch.begin();
        Assets.font3.setColor(1f,1f,1f, scoreboard_opacity);
        Assets.font3.draw(sBatch,"Score:", 300f, 500f);

        Assets.font1.setColor(1f,1f,1f, scoreboard_opacity);
        Assets.font1.draw(sBatch,"" + playerScore, 650f, 600f);
        sBatch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    public void update(float delta) {
        scoreboard_opacity += delta;
        if(scoreboard_opacity > 1f) scoreboard_opacity = 1f;
        stage.act();
    }

    public void draw(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);

        drawScoreboard();
        stage.draw();

        sBatch.begin();

        sBatch.end();
    }

    public void hide() {

    }

    public void dispose() {

    }
}