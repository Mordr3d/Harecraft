package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Mika on 23.2.2018.
 */

public class Player extends GameObject {

    static final float ACCEL_Y_OFFSET = 5f;

    float width = Assets.texR_player.getRegionWidth()/100f;
    float height = Assets.texR_player.getRegionHeight()/100f;

    final float SPEED = 5f;
    final float MAX_SPEED = 5f;

    public Player(float x, float y, float z) {
        decal = Decal.newDecal(width,height,Assets.texR_player, true);
        decal.setPosition(x,y,z);
        velocity = new Vector3();
    }

    public void update(float delta, float accelX, float accelY) {
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            velocity.x = accelX * 1.5f;
            velocity.y = (accelY - ACCEL_Y_OFFSET) * 1.5f;

            decal.setRotationZ(velocity.x * 5f);
            System.out.println(Math.abs(decal.getRotation().z));
        }
        else {
            checkInput(delta);
            decal.setRotationZ(velocity.x * 15f);
        }

        stateTime += delta;
        if(decal.getPosition().x < 20f && velocity.x < 0f || decal.getPosition().x > -20f && velocity.x > 0f)
        decal.translateX(-velocity.x * delta * Math.abs(decal.getRotation().z) * 2f);

        if(decal.getPosition().y < 10f && velocity.y > 0f || decal.getPosition().y > -10f && velocity.y < 0f)
        decal.translateY(velocity.y * delta);
    }

    public void checkInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(velocity.x > -MAX_SPEED)
            velocity.x -= SPEED * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(velocity.x < MAX_SPEED)
            velocity.x += SPEED * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(velocity.y < MAX_SPEED)
            velocity.y += SPEED * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(velocity.y > -MAX_SPEED)
            velocity.y -= SPEED * delta;
        }
    }
}
