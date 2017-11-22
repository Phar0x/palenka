package sk.palenka.display;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private static final Logger LOG = Logger.getLogger(ImageLoader.class);

    private BufferedImage player;

    public ImageLoader(){
        this.player = loadImage( "player.png" );
    }


    public static BufferedImage loadImage(String name){
        try {
            return ImageIO.read( ImageLoader.class.getResource( "/spritesheet/player/" + name ));
        } catch (IOException e) {
            LOG.error("Error while loading image on path: /textures/" + name);
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getPlayer() {
        return player;
    }

    public void setPlayer(BufferedImage player) {
        this.player = player;
    }
}
