package simulation.model.entities;

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

        x += xMove;
        y += yMove;

        if (x < 0 || x >= xMax) x -= 2*xMove;
        if (y < 0 || y >= yMax) y -= 2*yMove;
    }

}
