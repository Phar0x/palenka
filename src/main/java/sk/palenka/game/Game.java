package sk.palenka.game;

import org.apache.log4j.Logger;
import sk.palenka.display.Assets;
import sk.palenka.display.Display;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements Runnable {

    private static final Logger LOG = Logger.getLogger( Game.class );

    private Integer width;
    private Integer height;
    private String title;

    private boolean running = false;
    private boolean render = false;

    private Thread thread;

    public Game(String title, Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        Assets.init();
        Display.createWindow( title, height, width );
    }

    public void run() {
        init();

        Integer fps = 60;
        double timePerUpdate = 1000000000 / fps;
        Double delta = 0D;
        Long now;
        long timer = 0;
        Integer ticks = 0;
        Long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer > 1000000000) {
                Display.setTitle( "FPS: " + ticks );
                ticks = 0;
                timer = 0;
            }

            if (glfwWindowShouldClose( Display.getWindow() )) {
                running = false;
            }
        }
        stop();
    }

    private void render() {
        Display.render();
    }

    private void update() {
        Display.update();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        this.running = true;
        thread = new Thread( this );
        thread.start();
    }

    public synchronized void stop() {
        Display.destroy();
        try {
            if (!running) {
                return;
            }
            thread.join();
        } catch (InterruptedException e) {
            LOG.error( "Exception during thread stopping: " + e );
            e.printStackTrace();
        }
    }
}
