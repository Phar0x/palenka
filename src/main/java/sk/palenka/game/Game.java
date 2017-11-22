package sk.palenka.game;

import org.apache.log4j.Logger;
import sk.palenka.display.Display;
import sk.palenka.display.ImageLoader;
import sk.palenka.display.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.LocalTime;

public class Game implements Runnable {

    private static final Logger LOG = Logger.getLogger( Game.class );

    private Integer width;
    private Integer height;
    private String title;
    private Display display;
    private ImageLoader imageLoader;
    private BufferedImage playerImage;
    private SpriteSheet playerSpriteSheet;

    private boolean running = false;

    private Thread thread;

    public Game(String title, Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.imageLoader = new ImageLoader();
        this.playerImage = imageLoader.getPlayer();
        this.playerSpriteSheet = new SpriteSheet(this.playerImage);
    }

    private void init() {
        this.display = new Display( title, width, height );
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
                String str =  "FPS: " + ticks;
                this.display.getFpsTextArea().setText(str);
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

        //graphics.fillRect( 50, 50, 50, 50 );
        graphics.drawImage(playerSpriteSheet.crop(0,0, 64, 64),0 ,5, null);
        graphics.drawImage(playerSpriteSheet.crop(0,64, 64, 64), 64,5, null);
        graphics.drawImage(playerSpriteSheet.crop(0,128, 64, 64), 128,5, null);
        graphics.drawImage(playerSpriteSheet.crop(0,192, 64, 64), 192,5, null);


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
