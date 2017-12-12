package hr.fer.tzadro.nenr.lab5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Greek alphabet classifier");
        Group root = new Group();
        root.getChildren().add(createCanvas());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Canvas createCanvas() {
        Canvas canvas = new Canvas(300, 300);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    // todo: add coordinate to gesture path
                    gc.beginPath();
                    gc.moveTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    // todo: add coordinate to gesture path
                    gc.lineTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    // todo: start algorithm
                });

        return canvas;
    }
}