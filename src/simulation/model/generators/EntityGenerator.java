package simulation.model.generators;

import simulation.model.entities.Entity;

import java.util.List;

public interface EntityGenerator {

    List<Entity> generate(int rows, int columns, int maxPop);

}
