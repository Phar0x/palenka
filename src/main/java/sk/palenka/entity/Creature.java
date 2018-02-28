package sk.palenka.entity;

public class Creature extends GameObject {

    Long hp;
    Long dmg;


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
