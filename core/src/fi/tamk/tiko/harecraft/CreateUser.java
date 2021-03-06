package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Locale;

/**
 * Created by musta on 6.4.2018.
 */


public class CreateUser extends ScreenAdapter {

    GameMain game;
    Skin skin;
    Stage stage;
    OrthographicCamera camera;
    Preferences profilesData;
    TextField textField;
    Boolean mainMenuSaving = false;
    Boolean profilesMenuWithoutSaving = false;
    Locale locale;

    public CreateUser(GameMain game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 800);
        stage = new Stage(new StretchViewport(1280, 800, camera));
        //skin = new Skin(Gdx.files.internal("json/glassy-ui.json"));
        skin = Assets.skin_menu;
        profilesData = Gdx.app.getPreferences("ProfileFile");

        TextField.TextFieldStyle style3 = new TextField.TextFieldStyle(
                Assets.font6,
                new Color(0f,0f,0f,1f),
                Assets.skin_menu.getDrawable("black"),
                Assets.skin_menu.getDrawable("pale-blue"),
                Assets.skin_menu.getDrawable("textfield")
        );

        textField = new TextField("" , style3);
        textField.setWidth(500);
        //button.getLabel().setFontScale(2f);
        //textField.setHeight(200);
        textField.setPosition(1280/2 - textField.getWidth()/2,800/1.4f);
        textField.setName("textfield");
        //Gdx.input.setOnscreenKeyboardVisible(true);
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if ((c == '\r' || c == '\n')){
                    mainMenuSaving = true;
                }
            }
        });
        textField.setAlignment(Align.center);
        textField.setMaxLength(10);

        ProfileInfo.determineGameLanguage(); //check language data
        locale = ProfileInfo.gameLanguage;
        I18NBundle localizationBundle = I18NBundle.createBundle(Gdx.files.internal("Localization"), locale);


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

        TextButton backButton = new TextButton(localizationBundle.get("backButtonText"), style);
        backButton.setPosition(450 -backButton.getWidth()/2,400);
        backButton.setName("backbutton");
        backButton.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched)
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    profilesMenuWithoutSaving = true;
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        TextButton acceptButton = new TextButton(localizationBundle.get("acceptButtonText"), style);
        acceptButton.setPosition(830 -backButton.getWidth()/2,400);
        acceptButton.setName("acceptbutton");
        acceptButton.addListener(new InputListener() {
            Boolean touched = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { //touchdown täytyy palauttaa true jotta touchup voi toimia
                touched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (touched)
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    mainMenuSaving = true;
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor button)
            {
                touched = false;
            }
        });

        Label.LabelStyle style2 = new Label.LabelStyle(
                Assets.font3,
                new Color(1f,1f,1f,1f)
        );

        Label label1 = new Label(localizationBundle.get("createUserLabel"), style2);
        label1.setPosition(640 -label1.getWidth()/2,670);
        label1.setFontScale(1);

        stage.addActor(textField);
        stage.addActor(acceptButton);
        stage.addActor(backButton);
        stage.addActor(label1);

        Gdx.input.setInputProcessor(stage);
    }

    public void render (float delta) {
        Gdx.gl.glClearColor(68f/255f, 153f/255f, 223f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.app.log("nabbi", Gdx.input.isKeyPressed());

        stage.act();
        stage.draw();

        TextField tempActor = stage.getRoot().findActor("textfield");  //Set selected playerprofile to gamescreen.
        String tmpTxt = tempActor.getText();
        tmpTxt = tmpTxt.toUpperCase();
        //if (tmpTxt.length() > 10) {
        //    tmpTxt = tmpTxt.substring(0 , 10);
        //    tempActor.setText(tmpTxt);
        //    tempActor.setCursorPosition(10);
        //}
        //tempActor.getDefaultInputListener().enter();
        //textField.getDefaultInputListener().keyDown(Input.Keys.ENTER);
        //String string = (String) tempActor.getSelected();

        if (mainMenuSaving) {
            Boolean isThereDuplicate = false;
            int firstAvailableID = 0;
            for (int i = 0; i < 200; i++) {     //duplicate username checkkaus
                if (profilesData.getString("username"+i, "novalue").equals(tmpTxt)  ) {
                    isThereDuplicate = true;
                }
            }
            for (int i = 0; i < 200; i++) {     //find first available id
                if(profilesData.getString("username"+i, "novalue").equals("novalue") ) {
                    firstAvailableID = i;
                    break;
                }
            }

            if (isThereDuplicate == false) {
                profilesData.putString("username" + firstAvailableID, "" + tmpTxt); //muista flushaa
                profilesData.flush();
                ProfileInfo.selectedPlayerProfile = tmpTxt;
            }
                game.setScreen(new MainMenu(game,false));
        }

        if (profilesMenuWithoutSaving) {
            game.setScreen(new ProfileMenu(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            profilesMenuWithoutSaving = true;
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
