package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import simulation.model.Grid;
import simulation.model.entities.Basic;
import simulation.model.generators.TerrainGenerator;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation/views/basicGrid.fxml"));
        Parent root = loader.load();

        Grid grid = new Grid(10, 10);
        grid.generate(new TerrainGenerator());
        grid.populate();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
