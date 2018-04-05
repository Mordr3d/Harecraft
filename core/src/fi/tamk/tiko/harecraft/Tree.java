package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Mika on 28/02/2018.
 */

public class Tree extends GameObject {
    public Tree(float x, float y, float z) {
        TextureRegion textureRegion;
        float transposedY = y - 1f;

        if(MathUtils.random(0, 9) < 8) {
            if(MathUtils.random(0,9) < 8) {
                textureRegion = Assets.texR_tree_big_light;
            }
            else textureRegion = Assets.texR_tree_big_dark;
        }
        else {
            if(MathUtils.random(0, 5) < 4) {
                textureRegion = Assets.texR_tree_small_light;
            }
            else textureRegion = Assets.texR_tree_small_dark;

            transposedY -= 1.5f;
        }


        width = textureRegion.getRegionWidth() / 100f;
        height = textureRegion.getRegionHeight() / 100f;
        width *= 17f;
        height *= 17f;

        decal = Decal.newDecal(width, height, textureRegion, true);
        decal.setPosition(x,transposedY,z);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        //Opacity
        setOpacity();

        //Movement Z
        moveZ(delta);
    }
}
