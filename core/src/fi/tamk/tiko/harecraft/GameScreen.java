package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

import static fi.tamk.tiko.harecraft.World.WORLD_HEIGHT;
import static fi.tamk.tiko.harecraft.World.WORLD_WIDTH;
import static fi.tamk.tiko.harecraft.World.player;
import static fi.tamk.tiko.harecraft.WorldBuilder.spawnDistance;

/**
 * Created by musta on 23.2.2018.
 */

public class GameScreen extends ScreenAdapter {
    FPSLogger logger = new FPSLogger();

    enum State {
        START, RACE, FINISH
    }

    GameMain game;
    World world;
    WorldBuilder builder;
    WorldRenderer renderer;
    static DecalBatch dBatch;
    static PerspectiveCamera camera;
    static float fieldOfView = 45f;
    static float cameraRotation = 0f;

    static State state;
    static float timer;
    static float global_Speed = -13f;
    static float global_Multiplier = 1f;

    public GameScreen(GameMain game, World world) {
        this.game = game;
        this.world = world;
        builder = new WorldBuilder(world);
        renderer = new WorldRenderer(world);

        camera = new PerspectiveCamera(fieldOfView, WORLD_WIDTH, WORLD_HEIGHT);
        camera.near = 0.1f;
        camera.far = 400f;
        camera.position.set(0f,0f,-5f);

        dBatch = new DecalBatch(new MyGroupStrategy(camera));

        state = State.START;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        update(delta);
        renderer.renderWorld();
        drawSprites();
    }

    public void update(float delta) {
        logger.log();

        timer += delta;
        if(global_Multiplier > 1f) global_Multiplier -= 0.35f * delta;
        else global_Multiplier = 1f;

        builder.update(delta);
        updateCamera();
    }

    public void drawSprites() {
        game.sBatch.begin();
        if(player.velocity.x != 0f || player.velocity.y != 0f) player.pfx_scarf.draw(game.sBatch);
        game.sBatch.end();
    }

    public void updateCamera() {
        camera.position.set(player.decal.getPosition().x/1.15f, player.decal.getPosition().y/1.05f,-5f);
        //Needs work
        //camera.rotate(player.velocity.x / 20f,1f,1f,1f);
        camera.lookAt(0f,0f, spawnDistance/2f);
        camera.fieldOfView = fieldOfView;
        camera.update();
    }

    @Override
    public void dispose() {
        dBatch.dispose();
        world.dispose();
    }
}
