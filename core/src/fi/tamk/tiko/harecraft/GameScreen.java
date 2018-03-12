package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

import static fi.tamk.tiko.harecraft.GameScreen.GameState.END;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.FINISH;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.RACE;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.START;
import static fi.tamk.tiko.harecraft.World.player;
import static fi.tamk.tiko.harecraft.WorldBuilder.spawnDistance;

/**
 * Created by musta on 23.2.2018.
 */

public class GameScreen extends ScreenAdapter {
    FPSLogger logger = new FPSLogger();

    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth() / 100f;
    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight() / 100f;

    enum GameState {
        START, RACE, FINISH, END
    }

    GameMain game;
    World world;
    WorldBuilder builder;
    WorldRenderer renderer;
    static DecalBatch dBatch;
    static PerspectiveCamera camera;
    static OrthographicCamera orthoCamera;
    static float fieldOfView = 45f;

    static GameState gameState;
    static float gameStateTime;
    static float global_Speed = -13f;
    static float global_Multiplier = 1f;

    static String string = "3";
    boolean isCountdown;
    float volume = 0.15f;

    public GameScreen(GameMain game, World world) {
        this.game = game;
        this.world = world;
        builder = new WorldBuilder(world);
        renderer = new WorldRenderer(world, game);

        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera = new PerspectiveCamera(fieldOfView, SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.near = 0.1f;
        camera.far = 400f;
        camera.position.set(0f,0f,-5f);

        dBatch = new DecalBatch(new MyGroupStrategy(camera));
        game.sBatch.setProjectionMatrix(orthoCamera.combined);

        gameState = GameState.START;

        //Compressed audio files causes a slight delay when set to play, so better do it while the game is still loading
        //and reset the position and volume when it is actually supposed to play.
        Assets.music_course_1.play();
        Assets.music_course_1.setVolume(0f);
        Assets.sound_airplane_engine.loop(volume);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(104f/255f, 202f/255f, 230f/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        update(delta);
        renderer.renderWorld();
        drawHUD();
    }

    public void update(float delta) {
        logger.log();

        updateState(delta);
        builder.update(delta);
        updateCamera();
        updateHUD();
    }

    public void updateState(float delta) {
        gameStateTime += delta;
        if(global_Multiplier > 1f) global_Multiplier -= 0.35f * delta;
        else global_Multiplier = 1f;

        if(gameState == START && gameStateTime >= 6.6) {
            gameState = RACE;
            gameStateTime = 0f;
            player.distance = 0f;
            player.acceleration = 0f;
        }
        else if(gameState == START) {
            global_Multiplier = 3f;

            if(gameStateTime > 5.4f) {
                string = "GO!";
                if(volume > 0f) volume -= 0.08f * delta;
                if(volume <= 0f) {
                    volume = 0f;
                }
            }
            else if(gameStateTime > 4.2f) string = "1";
            else if(gameStateTime > 3f) string = "2";
            else if(gameStateTime > 2f && !isCountdown) {
                Assets.sound_countdown.play(0.25f);
                isCountdown = true;
            }
            Assets.sound_airplane_engine.setVolume(0,volume);
        }
        else if(gameState == RACE && player.distance > world.finish) {
            gameState = FINISH;
            gameStateTime = 0f;
        }
        else if(gameState == FINISH && player.distance > world.end) {
            gameState = END;
            gameStateTime = 0f;
        }

        if(gameState == RACE && gameStateTime == 0f) {
            world.rings.add(new Ring(0f, 0f, spawnDistance/3.25f));
            world.rings.add(new Ring(2f, 2f, spawnDistance/1.35f));
            Assets.sound_airplane_engine.stop();
            Assets.music_course_1.setPosition(0f);
            Assets.music_course_1.setVolume(1f);
            for(Opponent o : world.opponents) {
                o.position.z = o.spawnZ;
            }
        }
    }

    public void updateCamera() {
        camera.position.set(player.decal.getPosition().x/1.15f, player.decal.getPosition().y/1.05f,-5f);
        camera.lookAt(0f,0f, spawnDistance/2f);
        camera.up.set(player.getRotationAverage(), 20f, 0f);
        camera.fieldOfView = fieldOfView;
        camera.update();
    }

    public void updateHUD() {

    }

    public void drawHUD() {
        game.sBatch.begin();
        //Countdown numbers
        if(gameState == START && ((gameStateTime > 2f && gameStateTime < 3f) || (gameStateTime > 3.2f && gameStateTime < 4.2f) || (gameStateTime > 4.4f && gameStateTime < 5.4f)
                || (gameStateTime > 5.6f && gameStateTime < 6.6f))) {
            Assets.font.draw(game.sBatch, string,orthoCamera.viewportWidth/2f - Assets.font.getSpaceWidth() * string.length(),orthoCamera.viewportHeight/2f + 150f);
        }
        game.sBatch.end();
    }

    @Override
    public void dispose() {
        dBatch.dispose();
        world.dispose();
    }
}
