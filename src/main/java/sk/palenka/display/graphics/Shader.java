package sk.palenka.display.graphics;

import sk.palenka.utils.graphics.ShaderUtils;
import sk.palenka.utils.math.Matrix4f;
import sk.palenka.utils.math.Vector3f;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public static Shader background;
    public static Shader player;

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load( vertex, fragment );
    }

    public static void loadAll() {
        background = new Shader( "shaders/background.vert", "shaders/background.frag" );
        player = new Shader("shaders/player.vert", "shaders/player.frag");
    }

    public int getUniform(String name) {
        if (locationCache.containsKey( name )) {
            return locationCache.get( name );
        }
        int result = glGetUniformLocation( ID, name );
        if (result == -1) {
            System.err.println( "could not find uniform name = " + name );
        } else {
            locationCache.put( name, result );
        }
        return result;
    }

    public void setUniform1i(String name, int value) {
        glUniform1i( getUniform( name ), value );
    }

    public void setUniform1f(String name, float value) {
        glUniform1f( getUniform( name ), value );
    }

    public void setUniform2f(String name, float x, float y) {
        glUniform2f( getUniform( name ), x, y );
    }

    public void setUniform3f(String name, Vector3f v) {
        glUniform3f( getUniform( name ), v.x, v.y, v.z );
    }

    public void setUniformMat4f(String name, Matrix4f mat) {
        glUniformMatrix4fv( getUniform( name ), false, mat.toFloatBuffer() );
    }

    public void enable() {
        glUseProgram( ID );
    }

    public void disable() {
        glUseProgram( 0 );
    }

}
