package sk.palenka.entity;

public class Player extends Creature {

    Long xp;

    public Player() {
        super();
        this.xp = 0L;
    }

    public Player(Long x, Long y) {
        super( x, y );
        this.xp = 0L;
    }

    public Player(Long x, Long y, Long hp, Long dmg) {
        super( x, y, hp, dmg );
        this.xp = 0L;
    }

}
