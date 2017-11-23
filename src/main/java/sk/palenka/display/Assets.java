package sk.palenka.display;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;

public class Assets {

    private static final Logger LOG = Logger.getLogger( Assets.class );


    private static final int width = 64, height = 64;
    private static ImageLoader imageLoaderPlayer = new ImageLoader("/spritesheet/player/player.png");
    //private static ImageLoader imageLoaderTree = new ImageLoader("/spritesheet/trees/trees.png");
    private static ImageLoader imageLoaderTiles = new ImageLoader("/spritesheet/environment/tiles/Tiles1.png");

    public static BufferedImage[][] player, tree, enviroment;

    public static void init(){

        SpriteSheet playerSheet = new SpriteSheet(imageLoaderPlayer.getImage());
//        SpriteSheet treeSheet = new SpriteSheet(imageLoaderTree.getPlayer());
        SpriteSheet tileSheet = new SpriteSheet(imageLoaderTiles.getImage());
        LOG.debug( "processing player sheet");
        player = splitSheet(playerSheet, width, height );
        LOG.debug("processing environment sheet");
        enviroment = splitSheet(tileSheet,width, height);
    }

    private static BufferedImage[][] splitSheet(SpriteSheet sheet, int width, int height) {

        int sheetHeight = sheet.getSheet().getHeight();
        int sheetWidth = sheet.getSheet().getWidth();

        BufferedImage[][] image = new BufferedImage[sheetWidth / width][sheetHeight / height];

        LOG.debug("sheetHeight = " + sheetHeight + " sheetWidth = " + sheetWidth);
        for (int i = 0; i < sheetWidth / width; i++) {
            for (int j =0; j < sheetHeight / height; j++) {
                image[i][j] = sheet.crop(0 + (i * height), 0 + (j * width), width, height);
            }
        }

        return image;
    }
}
