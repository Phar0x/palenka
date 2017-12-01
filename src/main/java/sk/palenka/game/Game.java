package sk.palenka.game;

import org.apache.log4j.Logger;
import sk.palenka.display.Assets;
import sk.palenka.display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game implements Runnable {

    private static final Logger LOG = Logger.getLogger( Game.class );

    private Integer width;
    private Integer height;
    private String title;
    private Display display;

    private boolean running = false;
    private boolean render = false;

    private Thread thread;

    public Game(String title, Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        this.display = new Display( title, width, height );
        Assets.init();
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
                String str = "FPS: " + ticks;
                this.display.getFpsTextArea().setText( str );
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy buffStrategy = display.getCanvas().getBufferStrategy();
        if (buffStrategy == null) {
            display.getCanvas().createBufferStrategy( 3 );
            return;
        }
        Graphics graphics = buffStrategy.getDrawGraphics();

        if(!render) {
            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 10; i++) {
                    int x = 0 + (int) (Math.random() * 10);
                    
                    int y = 0 + (int) (Math.random() * 5);
                    graphics.drawImage(Assets.enviroment[x][y], 64 * i, j * 64, null);
                }
            }
            render = true;
        }
        graphics.drawImage( Assets.player[2][3], 0, 0, null );

        buffStrategy.show();
        graphics.dispose();

    }

    private void update() {

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
