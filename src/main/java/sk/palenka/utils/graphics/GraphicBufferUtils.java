package sk.palenka.utils.graphics;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GraphicBufferUtils {

    public static FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer( data.length );
        buffer.put( data );
        buffer.flip();

        return buffer;
    }

    public static ByteBuffer createByteBuffer(byte[] data) {
        ByteBuffer buffer = BufferUtils.createByteBuffer( data.length );
        buffer.put( data );
        buffer.flip();

        return buffer;
    }

    public static IntBuffer createIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer( data.length );
        buffer.put( data );
        buffer.flip();

        return buffer;
    }

}


