package simulation.model.generators;

import simulation.model.tiles.Tile;

public interface TileGenerator {

    Tile generate(int row, int column);

}
