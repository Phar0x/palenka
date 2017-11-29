package sk.palenka.entity;

public class GameObject {
    Long x;
    Long y;

    public GameObject() {
        this.x = 0L;
        this.y = 0L;
    }

    public GameObject(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y) {
        this.x = (long) x;
        this.y = (long) y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
