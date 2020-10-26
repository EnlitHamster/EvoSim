package simulation.model.generators;

import simulation.model.tiles.Terrain;
import simulation.model.tiles.Tile;

public class TerrainGenerator implements TileGenerator {

    public Tile generate(int x, int y) {
        return new Terrain();
    }

}
