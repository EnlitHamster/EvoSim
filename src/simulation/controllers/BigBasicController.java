package simulation.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import simulation.model.Grid;
import simulation.model.entities.Entity;

import java.util.*;

public class BigBasicController {

    @FXML BorderPane parent;
    @FXML GridPane map;
    @FXML Button tickSwitch;
    @FXML Button tickNext;
    @FXML Label tickCount;

    Grid grid;
    Map<Entity, FlowPane> entitiesPods;
    int ticks;
    boolean running;
    final long tps = 4;
    Timer timer;

    public void initModel(Grid grid) {
        this.grid = grid;
        entitiesPods = new HashMap<>();
        ticks = 0;
        running = false;

        Iterator<Entity> entities = grid.getEntities();
        int iE = 0;

        while (entities.hasNext()) {
            FlowPane ePane = new FlowPane();
            Entity e = entities.next();
            ePane.setPrefHeight(10);
            ePane.setMinHeight(10);
            ePane.setMaxHeight(10);
            ePane.setPrefWidth(10);
            ePane.setMinWidth(10);
            ePane.setMaxWidth(10);

            ePane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            ePane.getChildren().add(new Label(Integer.toString(iE++)));
            entitiesPods.put(e, ePane);
            map.add(ePane, e.getX(), e.getY());
        }

        System.out.println(">> generated " + (iE+1) + " pods.");
    }

    public void tick() {
        Platform.runLater(() -> {
            grid.tick();
            update();
            tickCount.setText("Tick: " + (++ticks));
        });
    }

    private void update() {
        Iterator<Entity> entities = grid.getEntities();

        while (entities.hasNext()) {
            Entity e = entities.next();
            FlowPane p = entitiesPods.get(e);

            map.getChildren().remove(p);
            map.add(p, e.getX(), e.getY());
        }
    }

    public void swtch() {
        if (!running) {
            running = true;
            tickSwitch.setText("Stop");
            tickNext.setDisable(true);

            timer = new Timer();
            timer.scheduleAtFixedRate(tickTask(), Calendar.getInstance().getTime(), 1000 / tps);
        } else {
            running = false;
            tickSwitch.setText("Start");
            tickNext.setDisable(false);

            timer.cancel();
            timer = null;
        }
    }

    private TimerTask tickTask() {
        return new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        };
    }

}
