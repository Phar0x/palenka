package sk.palenka.entity;

import sk.palenka.display.graphics.Shader;
import sk.palenka.display.graphics.VertexArrayObject;

public class GameObject {

    private int vaoId;
    private float SIZE = 1.0f;

    float[] vertices = {
            -1f, 1f, 0, //TOP LEFT     0
            1f, 1f, 0,  //TOP RIGHT    1
            1f, -1f, 0, //BOTTOM RIGHT 2
            -1f, -1f, 0,//BOTTOM LEFT  3
    };

    float[] texture = new float[]{
            0, 0,
            1, 0,
            1, 1,
            0, 1,
    };

    byte[] indices = new byte[]{
            0, 1, 2,
            2, 3, 0
    };

    private VertexArrayObject vao;

    public GameObject() {
        vao = new VertexArrayObject( this.vertices, this.texture, this.indices );
        this.vaoId = vao.getVao();
    }

    public void render() {
        Shader.background.enable();
        vao.render();
        Shader.background.disable();
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
