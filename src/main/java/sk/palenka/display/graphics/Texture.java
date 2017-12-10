package sk.palenka.display.graphics;

import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;


public class Texture {

    private int textureObject;
    private int width;
    private int height;

    public Texture(BufferedImage bufferedImage) {

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        int[] pixels_raw = new int[width * height * 4];
        pixels_raw = bufferedImage.getRGB( 0, 0, width, height, null, 0, width );

        ByteBuffer pixels = BufferUtils.createByteBuffer( width * height * 4 );

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = pixels_raw[i * width + j];
                pixels.put( (byte) ((pixel >> 16) & 0xFF) ); // RED
                pixels.put( (byte) ((pixel >> 8) & 0xFF) );  // GREEN
                pixels.put( (byte) (pixel & 0xFF) );          // BLUE
                pixels.put( (byte) ((pixel >> 24) & 0xFF) ); // ALPHA
            }
        }

        pixels.flip();

        textureObject = glGenTextures();

        glBindTexture( GL_TEXTURE_2D, textureObject );

        glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );
        glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );

        glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels );

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
