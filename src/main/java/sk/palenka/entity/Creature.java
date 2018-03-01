package sk.palenka.entity;

import sk.palenka.display.graphics.Shader;
import sk.palenka.display.graphics.VertexArrayObject;
import sk.palenka.utils.math.Matrix4f;
import sk.palenka.utils.math.Vector3f;

public class Creature extends GameObject {

    float SIZE = 0.2f;
    Vector3f position = new Vector3f();

    Long hp;
    Long dmg;

    Creature() {
        this.dmg = 5L;
        this.hp = 100L;
        this.vertices = new float[]{
                -SIZE / 2.0f, -SIZE / 2.0f, 0.0f,
                -SIZE / 2.0f, SIZE / 2.0f, 0.0f,
                SIZE / 2.0f, SIZE / 2.0f, 0.0f,
                SIZE / 2.0f, -SIZE / 2.0f, 0.0f,
        };
        vao = new VertexArrayObject( this.vertices, this.tex, this.indices );
        vaoId = vao.getVao();
    }

    Creature(Vector3f position) {
        this.dmg = 5L;
        this.hp = 100L;
        this.vertices = new float[]{
                -SIZE / 2.0f, -SIZE / 2.0f, 0.0f,
                -SIZE / 2.0f, SIZE / 2.0f, 0.0f,
                SIZE / 2.0f, SIZE / 2.0f, 0.0f,
                SIZE / 2.0f, -SIZE / 2.0f, 0.0f,
        };
        vao = new VertexArrayObject( this.vertices, this.tex, this.indices );
        vaoId = vao.getVao();
        this.position = position;
    }

    Creature(Long hp, Long dmg) {
        this.dmg = dmg;
        this.hp = hp;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public Long getDmg() {
        return dmg;
    }

    public void setDmg(Long dmg) {
        this.dmg = dmg;
    }


    public float getSIZE() {
        return SIZE;
    }

    public void setSIZE(float SIZE) {
        this.SIZE = SIZE;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        Shader.player.enable();
        Shader.player.setUniformMat4f( "ml_matrix", Matrix4f.translate( position ) );
        texture.bind();
        vao.render();
        Shader.player.disable();
        texture.unbind();
    }
}
