package sk.palenka.display;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;
    private static ImageLoader imageLoaderPlayer = new ImageLoader("/spritesheet/player/player.png");
    //private static ImageLoader imageLoaderTree = new ImageLoader("/spritesheet/trees/trees.png");
    private static ImageLoader imageLoaderTiles = new ImageLoader("/spritesheet/environment/tiles/Tiles1.png");

    public static BufferedImage player, tree, enviroment;

    public static void init(){

        SpriteSheet playerSheet = new SpriteSheet(imageLoaderPlayer.getPlayer());
        //SpriteSheet treeSheet = new SpriteSheet(imageLoaderTree.getPlayer());
       SpriteSheet tileSheet = new SpriteSheet(imageLoaderTiles.getPlayer());


        player = playerSheet.crop(0,0,width,height);

        enviroment = tileSheet.crop(0,height * 2,width,height);
    }
}
