package sk.palenka.entity;

import sk.palenka.display.Assets;
import sk.palenka.display.graphics.*;

public class Background extends GameObject {

    public Background(){
        vao = new VertexArrayObject( this.vertices, this.tex, this.indices );
        vaoId = vao.getVao();
        texture = new Texture( Assets.environment[0][0] );
    }

    @Override
    public void render() {
        Shader.background.enable();
        texture.bind();
        vao.render();
        Shader.background.disable();
        texture.unbind();
    }

    @Override
    public void update() {
        super.update();
    }
}
