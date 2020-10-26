package simulation.model;

import simulation.model.entities.Entity;
import simulation.model.generators.EntityGenerator;
import simulation.model.generators.TileGenerator;
import simulation.model.tiles.Tile;

import java.util.Iterator;
import java.util.List;

public class Grid {

    public static class Collisions {

        protected static Grid grid;

        public static boolean free(Entity test) {
            return grid.entities.stream().allMatch(e -> e == test || (e.getX() != test.getX() && e.getY() != test.getY()));
        }

    }


    Tile[][] matrix;

    // The order in the list counts. A tick is divided in turns. The entities that come first in the list take their
    // turn before the entities that come after.
    // TODO: create a initiative value on which to sort the entities to make turns succession dynamic
    List<Entity> entities;

    private final int rows;
    private final int columns;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        matrix = new Tile[rows][columns];
    }

    public void generate(TileGenerator generator) {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                matrix[row][column] = generator.generate(row, column);
    }

    public void populate(EntityGenerator generator, int maxPop) {
        entities = generator.generate(rows, columns, maxPop);
        Collisions.grid = this;
    }

    public void tick() {
        entities.forEach(Entity::tick);
    }

    public Iterator<Entity> getEntities() {
        return entities.iterator();
    }

}
