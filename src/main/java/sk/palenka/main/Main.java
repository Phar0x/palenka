package sk.palenka.main;

import org.apache.log4j.Logger;
import sk.palenka.game.Game;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Game g = new Game("Title", 640, 640);
        g.start();
    }

}
