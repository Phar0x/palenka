package sk.palenka.display;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private static final Logger LOG = Logger.getLogger(ImageLoader.class);

    private BufferedImage image;

    public ImageLoader(String path){

        this.image = loadImage(path);
    }

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read( ImageLoader.class.getResource(path));
        } catch (IOException e) {
            LOG.error("Error while loading image on path: " + path);
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
