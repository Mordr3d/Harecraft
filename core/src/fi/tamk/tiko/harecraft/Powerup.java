package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static fi.tamk.tiko.harecraft.GameScreen.global_Multiplier;
import static fi.tamk.tiko.harecraft.World.player;

/**
 * Created by Mika on 11/03/2018.
 */

public class Powerup extends GameObject {
    final float COLLECTED_SPEED = 10f;
    Vector2 transposedPosition = new Vector2();
    Vector2 transposedDirection = new Vector2();
    boolean isCollected = false;

    public Powerup() {
        position = new Vector3();
        velocity = new Vector3();

        velocity.y = MathUtils.random(3.5f,5.5f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        velocity.y -= delta * stateTime/5.5f;
        if(velocity.y < 0f) velocity.y = 0f;

        if(!isCollected)decal.translateY(velocity.y * delta);

        transposedPosition.x = position.x;
        transposedPosition.y = position.y + height / 4f;

        if(!isCollected) {
            if(decal.getPosition().z < 0.5f && decal.getPosition().z > -1.5f && (transposedPosition.dst(player.position.x, player.position.y) < 1.85f)) {
                isCollected = true;

                Assets.sound_cloud_hit.play();
                decal.setPosition(position.x, position.y,0.5f);
            }
        }
        else {
            decal.setScale(decal.getScaleX() - delta * stateTime / 10f);
            if(decal.getScaleX() < 0f) decal.setScale(0f);
            transposedDirection = transposedPosition.cpy().sub(position.x, position.y);
            //velocity.x = transposedDirection.nor().x * COLLECTED_SPEED * Math.abs(transposedDirection.x);
            velocity.y = transposedDirection.nor().y * COLLECTED_SPEED * Math.abs(transposedDirection.y);

            decal.translateX(velocity.x * delta);
            decal.translateY(velocity.y * delta);
        }

        //Opacity
        setOpacity();

        //Movement Z
        if(!isCollected || decal.getScaleX() == 0f)moveZ(delta);
    }
}