package sk.palenka.entity;

import org.lwjgl.glfw.GLFW;
import sk.palenka.display.Assets;
import sk.palenka.display.graphics.Texture;
import sk.palenka.input.KeyboardHandler;
import sk.palenka.utils.math.Vector3f;

public class Player extends Creature {

    Long xp;

    public Player() {
        xp = 0L;
        texture = new Texture( Assets.player[0][0] );
    }

    public Player(Vector3f position) {
        xp = 0L;
        texture = new Texture( Assets.player[0][0] );
        this.position = position;
    }

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    @Override
    public void update() {
        if (KeyboardHandler.isKeyDown( GLFW.GLFW_KEY_W ))
            position.y += 0.03f;
        if (KeyboardHandler.isKeyDown( GLFW.GLFW_KEY_S ))
            position.y -= 0.03f;
        if (KeyboardHandler.isKeyDown( GLFW.GLFW_KEY_A ))
            position.x -= 0.03f;
        if (KeyboardHandler.isKeyDown( GLFW.GLFW_KEY_D ))
            position.x += 0.03f;
    }

}
