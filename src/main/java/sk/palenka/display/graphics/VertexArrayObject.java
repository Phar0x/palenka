package sk.palenka.display.graphics;

import sk.palenka.utils.graphics.GraphicBufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class VertexArrayObject {

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    private int vao, vbo, ibo, tco;
    private int count;

    public VertexArrayObject(float[] vertices, float[] texture, byte[] indices) {
        createArrayObject( vertices, texture, indices );
    }

    private void createArrayObject(float[] vertices, float[] texture, byte[] indices) {
        count = indices.length;
        vao = glGenVertexArrays();
        glBindVertexArray( vao );

        createVerticesBuffer( vertices );
        createTextureBuffer( texture );
        createIndicesBuffer( indices );

        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.err.println(error);
        glBindVertexArray( 0 );
    }

    private void createVerticesBuffer(float[] vertices) {
        int vboId = glGenBuffers();
        glBindBuffer( GL_ARRAY_BUFFER, vboId );
        glBufferData( GL_ARRAY_BUFFER, GraphicBufferUtils.createFloatBuffer( vertices ), GL_STATIC_DRAW );
        glVertexAttribPointer( VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0 );
        glBindBuffer( GL_ARRAY_BUFFER, 0 );
        vbo = vboId;
    }

    private void createTextureBuffer(float[] texture) {
        int texId = glGenBuffers();
        glBindBuffer( GL_ARRAY_BUFFER, texId );
        glBufferData( GL_ARRAY_BUFFER, GraphicBufferUtils.createFloatBuffer( texture ), GL_STATIC_DRAW );
        glVertexAttribPointer( TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0 );
        glBindBuffer( GL_ARRAY_BUFFER, 0 );
        tco = texId;
    }

    private void createIndicesBuffer(byte[] indices) {
        int iboId = glGenBuffers();
        glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, iboId );
        glBufferData( GL_ELEMENT_ARRAY_BUFFER, GraphicBufferUtils.createByteBuffer( indices ), GL_STATIC_DRAW );
        glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, 0 );
        ibo = iboId;
    }

    public void render() {
        bind();
        glEnableVertexAttribArray( VERTEX_ATTRIB );
        glEnableVertexAttribArray( TCOORD_ATTRIB );

        glDrawElements( GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0 );

        glDisableVertexAttribArray( VERTEX_ATTRIB );
        glDisableVertexAttribArray( TCOORD_ATTRIB );
        unbind();
    }

    public void bind() {
        glBindVertexArray( vao );
        glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, ibo );

    }

    public void unbind() {
        glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, 0 );
        glBindVertexArray( 0 );
    }

    protected void finalize() throws Throwable {
        glDeleteVertexArrays( vao );
        super.finalize();
    }

    public int getVao() {
        return vao;
    }
}
