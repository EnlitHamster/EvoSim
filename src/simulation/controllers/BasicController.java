package simulation.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import simulation.model.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class BasicController {

    @FXML BorderPane parent;
    @FXML GridPane map;
    @FXML Button tickNext;
    @FXML Label tickCount;

    List<Pane> entitiesPods;

    public BasicController(List<Entity> entities) {
        entitiesPods = new ArrayList<>();

        for (int i = 0; i < entities.size(); ++i) {
            FlowPane ePane = new FlowPane();
            ePane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            ePane.getChildren().add(new Label(Integer.toString(i)));
            entitiesPods.add(ePane);
            Entity e = entities.get(i);
            map.add(ePane, e.getX(), e.getY());
        }
    }

    public void tick() {

    }

}
