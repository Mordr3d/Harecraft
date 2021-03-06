package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Locale;

import static fi.tamk.tiko.harecraft.GameScreen.GameState.END;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.START;

/**
 * Created by musta on 19.4.2018.
 */

public class ConfirmDelete extends ScreenAdapter {

    GameMain game;
    Skin skin;
    Stage stage;
    OrthographicCamera camera;
    Preferences profilesData;
    Boolean yesSelect = false;
    Boolean noSelect = false;
    String tempString;
    Locale locale;

    public ConfirmDelete(GameMain game, String deleteString) {

        this.game = game;
        skin = Assets.skin_menu;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);
        stage = new Stage(new StretchViewport(1280, 800, camera));
        profilesData = Gdx.app.getPreferences("ProfileFile");
        tempString = deleteString;

        ProfileInfo.determineGameLanguage(); //check language data
        locale = ProfileInfo.gameLanguage;
        I18NBundle localizationBundle = I18NBundle.createBundle(Gdx.files.internal("Localization"), locale);

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle style2;

        if(localizationBundle.get("yesButton").equals("Yes")) {
            style2 = new Label.LabelStyle(
                    Assets.font4,
                    new Color(1f, 1f, 1f, 1f)
            );
        }
        else {
            style2 = new Label.LabelStyle(
                    Assets.font5,
                    new Color(1f, 1f, 1f, 1f)
            );
        }

        Label questionLabel = new Label(localizationBundle.get("sureToDelete"), style2);
        questionLabel.setPosition(640 -questionLabel.getWidth()/2,520);
        questionLabel.setName("difficultylabel");

        style2 = new Label.LabelStyle(
                Assets.font2,
                new Color(1f,1f,1f,1f)
        );

        Label nameLabel = new Label((tempString), style2);
        nameLabel.setPosition(640 -nameLabel.getWidth()/2,400);
        nameLabel.setName("namelabel");

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                Assets.skin_menu.getDrawable("button"),
                Assets.skin_menu.getDrawable("button pressed"),
                Assets.skin_menu.getDrawable("button"),
                Assets.font5
        );
        style.pressedOffsetX = 4;
        style.pressedOffsetY = -4;
        style.downFontColor = new Color(0.59f,0.59f,0.59f,1f);
        style.fontColor = new Color(1f,1f,1f,1f);

        TextButton yesButton = new TextButton(localizationBundle.get("yesButton"), style);
        yesButton.setPosition(1280/2 +200 -yesButton.getWidth()/2,300 -yesButton.getHeight()/2);
        yesButton.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched)
                    yesSelect = true;
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        TextButton noButton = new TextButton(localizationBundle.get("noButton"), style);
        noButton.setPosition(1280/2 -200 -noButton.getWidth()/2,300 -noButton.getHeight()/2);
        noButton.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched)
                    noSelect = true;
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        stage.addActor(noButton);
        stage.addActor(yesButton);
        stage.addActor(questionLabel);
        stage.addActor(nameLabel);
    }

    public void render (float delta) {
        Gdx.gl.glClearColor(68f/255f, 153f/255f, 223f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (yesSelect) {    //Delete starts

            for (int i = 0; i < 200; i++) { //selvitetään KEY
                if(tempString.equals(profilesData.getString("username" +i, "novalue"))) {
                    profilesData.remove("username"+i);
                }
            }   //delete data
            profilesData.remove(tempString +"StaticHolds");
            profilesData.remove(tempString +"Difficulty");
            profilesData.remove(tempString + "Duration");
            profilesData.remove(tempString +"Sensitivity");
            profilesData.remove(tempString +"Score");
            profilesData.remove(tempString +"Invert");
            profilesData.remove(tempString+"LastLevel");
            for ( int i = 0; i < 6; i++) {
                profilesData.remove(tempString + "VectorX" + i);
                profilesData.remove(tempString + "VectorY" + i);
            }
            profilesData.flush();

            game.setScreen(new ProfileMenu(game));
        }
        if (noSelect) {
            game.setScreen(new ProfileMenu(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            noSelect = true;
        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
