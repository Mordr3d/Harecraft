package fi.tamk.tiko.harecraft;

import static fi.tamk.tiko.harecraft.GameScreen.GameState.END;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.FINISH;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.START;
import static fi.tamk.tiko.harecraft.GameScreen.camera;
import static fi.tamk.tiko.harecraft.GameScreen.dBatch;
import static fi.tamk.tiko.harecraft.GameScreen.gameState;
import static fi.tamk.tiko.harecraft.GameScreen.gameStateTime;
import static fi.tamk.tiko.harecraft.GameScreen.orthoCamera;
import static fi.tamk.tiko.harecraft.World.player;
import static fi.tamk.tiko.harecraft.WorldBuilder.spawnDistance;

/**
 * Created by Mika on 01/03/2018.
 */

public class WorldRenderer {
    GameMain game;
    World world;

    public WorldRenderer(World world, GameMain game) {
        this.world = world;
        this.game = game;
    }

    public void renderWorld() {
        drawDecals();
        drawParticles();
    }

    public void drawDecals() {
        dBatch.add(world.decal_background);
        dBatch.add(world.decal_sun1);
        dBatch.add(world.decal_sun2);
        dBatch.add(world.decal_foreground);

        for(Cloud c : world.clouds_LUp) {
            dBatch.add(c.decal);
        }
        for(Cloud c : world.clouds_LDown) {
            dBatch.add(c.decal);
        }
        for(Cloud c : world.clouds_RUp) {
            dBatch.add(c.decal);
        }
        for(Cloud c : world.clouds_RDown) {
            dBatch.add(c.decal);
        }

        for(Ring l : world.rings) {
            dBatch.add(l.decal);
        }

        for(Tree t : world.trees_L) {
            dBatch.add(t.decal);
        }
        for(Tree t : world.trees_R) {
            dBatch.add(t.decal);
        }
        for(Hill h : world.hills_L) {
            dBatch.add(h.decal);
        }
        for(Hill h : world.hills_R) {
            dBatch.add(h.decal);
        }

        for(Lake l : world.lakes_L) {
            dBatch.add(l.decal);
        }
        for(Lake l : world.lakes_R) {
            dBatch.add(l.decal);
        }

        for(Opponent o : world.opponents) {
            if(o.isDrawing || o.opacity != 0f) dBatch.add(o.decal);
        }

        if(player.isDrawing || player.opacity != 0f) dBatch.add(player.decal);
        //////////////////////////////////////////////
        dBatch.flush();
    }

    public void drawParticles() {
        game.sBatch.begin();
        if((player.velocity.x != 0f || player.velocity.y != 0f) && gameState != END) player.pfx_scarf.draw(game.sBatch);
        game.sBatch.end();
    }
}
