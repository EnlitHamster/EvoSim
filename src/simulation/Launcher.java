package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import simulation.controllers.BasicController;
import simulation.model.Grid;
import simulation.model.entities.Basic;
import simulation.model.entities.Entity;
import simulation.model.generators.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation/views/basicGrid.fxml"));
        BorderPane root = loader.load();

        Grid grid = new Grid(100, 100);
        grid.generate(new TerrainGenerator());
        grid.populate((rows, cols, max) -> {
            List<Entity> entities = new ArrayList<>();
            entities.add(new Basic(0, 0, rows, cols));
            entities.add(new Basic(rows-1, 0, rows, cols));
            entities.add(new Basic(0, cols-1, rows, cols));
            entities.add(new Basic(rows-1, cols-1, rows, cols));
            return entities;
        }, 4);

        BasicController controller = loader.getController();
        controller.initModel(grid);

        stage.setTitle("EvoSim :: Basics");
        stage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
        stage.setMinWidth(root.getPrefWidth());
        stage.setMinHeight(root.getPrefHeight());
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.requestFocus();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
