package simulation.model;

import simulation.model.entities.Entity;
import simulation.model.generators.EntityGenerator;
import simulation.model.tiles.Tile;
import simulation.model.generators.TileGenerator;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    Tile[][] matrix;

    // The order in the list counts. A tick is divided in turns. The entities that come first in the list take their
    // turn before the entities that come after.
    // TODO: create a initiative value on which to sort the entities to make turns succession dynamic
    List<Entity> entities;

    private int rows;
    private int columns;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        matrix = new Tile[rows][columns];
        entities = new ArrayList<>();
    }

    public void generate(TileGenerator generator) {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                matrix[row][column] = generator.generate(row, column);
    }

    public void populate(EntityGenerator generator, int maxPop) {
        entities = generator.generate(rows, columns, maxPop);
    }

    public void tick() {
        entities.forEach(Entity::tick);
    }

}
