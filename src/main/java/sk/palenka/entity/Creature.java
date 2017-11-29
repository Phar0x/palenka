package sk.palenka.entity;

public class Creature extends GameObject {

    Long hp;
    Long dmg;

    public Creature() {
        super();
        this.hp = 100L;
        this.dmg = 5L;
    }

    public Creature(Long x, Long y) {
        super( x, y );
    }

    public Creature(Long x, Long y, Long hp, Long dmg) {
        super( x, y );
        this.hp = hp;
        this.dmg = dmg;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public Long getDmg() {
        return dmg;
    }

    public void setDmg(Long dmg) {
        this.dmg = dmg;
    }
}
