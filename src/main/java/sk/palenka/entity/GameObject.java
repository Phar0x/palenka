package sk.palenka.entity;

import sk.palenka.display.graphics.*;

public class GameObject {

    float[] vertices = {
            -1f, -1f, 0,
            -1f, 1f, 0,
            1f, 1f, 0,
            1f, -1f, 0,
    };

    float[] tex = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1,
    };

    byte[] indices = new byte[]{
            0, 1, 2,
            2, 3, 0
    };

    int vaoId;
    private float SIZE = 1.0f;
    Texture texture;
    VertexArrayObject vao;
    
    public void update() {
    }

    public void render() {
    }

    public int getVaoId() {
        return vaoId;
    }

    public void setVaoId(int vaoId) {
        this.vaoId = vaoId;
    }

    public VertexArrayObject getVao() {
        return vao;
    }

    public void setVao(VertexArrayObject vao) {
        this.vao = vao;
    }

}
