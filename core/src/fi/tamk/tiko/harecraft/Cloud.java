package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mika on 23.2.2018.
 */

public class Cloud extends GameObject {

    float width = Assets.texR_cloud.getRegionWidth()/100f;
    float height = Assets.texR_cloud.getRegionHeight()/100f;
    Vector2 projection = new Vector2();

    public Cloud(float x, float y, float z) {
        decal = Decal.newDecal(width * 8f, height * 8f, Assets.texR_cloud, true);
        decal.setPosition(x,y,z);
    }

    public void update(float delta) {
        projection.x = decal.getPosition().x;
        projection.y = decal.getPosition().y;
        stateTime += delta;
        opacity = stateTime < 1f ? stateTime : 1f;
        if(decal.getPosition().z < 0f && projection.dst(GameScreen.player.decal.getPosition().x, GameScreen.player.decal.getPosition().y) < 2f) opacity = 0.4f;
        decal.setColor(1f,1f,1f, opacity);
        decal.translateZ(-10f * delta);
    }
}
