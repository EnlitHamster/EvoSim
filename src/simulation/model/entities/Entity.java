package simulation.model.entities;

import java.util.Random;

public abstract class Entity {

    protected int x;
    protected int y;

    protected Random engine;

    Entity(int x, int y) {
        this.x = x;
        this.y = y;

        engine = new Random();
    }

    public abstract void tick();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
