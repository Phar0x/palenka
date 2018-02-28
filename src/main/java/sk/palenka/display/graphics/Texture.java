package sk.palenka.display.graphics;

import sk.palenka.utils.graphics.GraphicBufferUtils;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;


public class Texture {

    private int textureObject;
    private int width;
    private int height;

    public Texture(BufferedImage bufferedImage) {

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        int[] pixels = new int[width * height];
        bufferedImage.getRGB( 0, 0, width, height, pixels, 0, width );

        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        textureObject = glGenTextures();

        bind();
        glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );
        glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );

        glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, GraphicBufferUtils.createIntBuffer( data ) );
        unbind();
    }

    @Override
    protected void finalize() throws Throwable {
        glDeleteTextures( textureObject );
        super.finalize();
    }

    public void bind() {
        glBindTexture( GL_TEXTURE_2D, textureObject );
    }

    public void unbind() {
        glBindTexture( GL_TEXTURE_2D, 0 );
    }
}
