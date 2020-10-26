package simulation.model.entities;

import simulation.model.Grid;

public class Basic extends Entity {

    protected int xMax;
    protected int yMax;

    public Basic(int x, int y, int xMax, int yMax) {
        super(x, y);

        this.xMax = xMax;
        this.yMax = yMax;
    }

    public void tick() {
        int xMove = engine.nextInt(3) - 1;
        int yMove = engine.nextInt(3) - 1;

        int oldX = x;
        int oldY = y;

        x += xMove;
        y += yMove;

        if (x < 0 || x >= xMax) x -= 2*xMove;
        if (y < 0 || y >= yMax) y -= 2*yMove;

        if (!Grid.Collisions.free(this)) {
            x = oldX;
            y = oldY;
            tick();
        }
    }

}
