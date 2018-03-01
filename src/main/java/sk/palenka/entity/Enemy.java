package sk.palenka.entity;

import sk.palenka.display.Assets;
import sk.palenka.display.graphics.Texture;
import sk.palenka.utils.math.Vector3f;

public class Enemy extends Creature {

    public Enemy() {
        texture = new Texture( Assets.player[3][3] );
    }

    public Enemy(Vector3f position) {
        texture = new Texture( Assets.player[3][3] );
        this.position = position;
    }

}
