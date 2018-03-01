package sk.palenka.display;

import org.apache.log4j.Logger;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import sk.palenka.display.graphics.Shader;
import sk.palenka.entity.*;
import sk.palenka.input.KeyboardHandler;
import sk.palenka.utils.math.Matrix4f;
import sk.palenka.utils.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Display {

    private static final Logger LOG = Logger.getLogger( Display.class );

    private static String title;
    private static Integer width;
    private static Integer height;
    private static long window;

    private static List<GameObject> gameObjects = new ArrayList<>();

    public static void createWindow(String title, Integer height, Integer width) {
        Display.title = title;
        Display.height = height;
        Display.width = width;


        glfwSetErrorCallback( GLFWErrorCallback.createPrint( System.err ) );

        if (!glfwInit()) {
            throw new IllegalStateException( "Unable to initialize GLFW" );
        }

        glfwDefaultWindowHints();
        glfwWindowHint( GLFW_VISIBLE, GL_FALSE );
        glfwWindowHint( GLFW_RESIZABLE, GL_FALSE );

        window = glfwCreateWindow( width, height, title, NULL, NULL );
        if (window == NULL) {
            throw new RuntimeException( "Failed to create the GLFW window" );
        }
        GLFWKeyCallback keyCallback;
        glfwSetKeyCallback( window, keyCallback = new KeyboardHandler() );

        GLFWVidMode vidmode = glfwGetVideoMode( glfwGetPrimaryMonitor() );
        glfwSetWindowPos(
                window,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );

        glfwMakeContextCurrent( window );
//        glfwSwapInterval( 1 );

        glfwShowWindow( window );
        GL.createCapabilities();

        glEnable( GL_BLEND );
        glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
        glEnable( GL_TEXTURE_2D );
        glActiveTexture( GL_TEXTURE1 );

        String version = glGetString( GL_VERSION );
        System.out.println( "OpenGL:" + version );

        Shader.loadAll();

        Shader.background.enable();
        Matrix4f prMatrix = Matrix4f.orthographic( -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f );
        Shader.background.setUniformMat4f( "pr_matrix", prMatrix );
        Shader.background.setUniform1i( "tex", 1 );
        Shader.background.disable();

        Shader.player.enable();
        Shader.player.setUniformMat4f( "pr_matrix", prMatrix );
        Shader.player.setUniform1i( "tex", 1 );
        Shader.player.enable();


        Background bg = new Background();
        gameObjects.add( bg );
        Player player = new Player( new Vector3f( -0.5f, 0.5f, 0.0f ) );
        gameObjects.add( player );
        Enemy enemy = new Enemy( new Vector3f( 0.5f, -0.5f, 0.0f ) );
        gameObjects.add( enemy );

        LOG.debug( "created new window with title = " + title + " , width = " + width + " and height = " + height );
    }

    public static void destroy() {
        glfwDestroyWindow( window );
        glfwTerminate();
    }

    public static void update() {
        glfwPollEvents();

        glClear( GL_COLOR_BUFFER_BIT );
        for (GameObject go : gameObjects) {
            go.update();
        }

        glfwSwapBuffers( window );
    }

    public static void render() {
        glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

        for (GameObject go : gameObjects) {
            go.render();
        }
        glfwSwapBuffers( window );
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Display.title = title;
    }

    public static Integer getWidth() {
        return width;
    }

    public static void setWidth(Integer width) {
        Display.width = width;
    }

    public static Integer getHeight() {
        return height;
    }

    public static void setHeight(Integer height) {
        Display.height = height;
    }

    public static long getWindow() {
        return window;
    }

    public static void setWindow(long window) {
        Display.window = window;
    }
}
