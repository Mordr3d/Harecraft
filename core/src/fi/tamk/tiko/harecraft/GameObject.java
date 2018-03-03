package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static fi.tamk.tiko.harecraft.GameScreen.GameState.RACE;
import static fi.tamk.tiko.harecraft.GameScreen.GameState.START;
import static fi.tamk.tiko.harecraft.GameScreen.gameState;
import static fi.tamk.tiko.harecraft.GameScreen.global_Multiplier;
import static fi.tamk.tiko.harecraft.GameScreen.global_Speed;

/**
 * Created by Mika on 23.2.2018.
 */

abstract class GameObject {
    Decal decal;

    Vector3 velocity;
    Vector3 direction;
    Vector3 position;
    Vector3 rotation;

    float stateTime;
    float opacity;

    public GameObject() {

    }

    //Use this for clouds, trees, etc
    public void moveZ(float delta) {
        decal.translateZ(velocity.z * delta);
    }

    public void setOpacity() {
        opacity = stateTime < 1f ? stateTime : 1f;
        decal.setColor(1f,1f,1f, opacity);
    }

    public void update(float delta) {
        stateTime += delta;
        position = decal.getPosition();
        if(!(this instanceof Opponent)) velocity.z = global_Speed - global_Multiplier * 3f;
    }
}
