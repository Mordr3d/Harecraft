package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.MathUtils;

import static fi.tamk.tiko.harecraft.WorldBuilder.spawnDistance;

/**
 * Created by Mika on 02/03/2018.
 */

public class Lake extends GameObject {
    public Lake(float x, float y, float z) {
        width = Assets.texR_lake.getRegionWidth() / 9f;
        height = Assets.texR_lake.getRegionHeight() / 9f;

        float randomSize = MathUtils.random(1f, 2.5f);
        width *= randomSize;
        height *= randomSize;

        decal = Decal.newDecal(width, height, Assets.texR_lake, true);
        decal.setPosition(x,y - 5f,z + height);
        decal.rotateX(90f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(position.z + height/3f > spawnDistance) stateTime = 0;

        //Opacity
        setOpacity();

        //Movement Z
        moveZ(delta);
    }
}
