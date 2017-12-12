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
        final NeuralNetworkAlgorithm algorithm = new NeuralNetworkAlgorithm();

        primaryStage.setTitle("Greek alphabet classifier");
        Group root = new Group();
        root.getChildren().add(createCanvas(algorithm));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Canvas createCanvas(NeuralNetworkAlgorithm algorithm) {
        Canvas canvas = new Canvas(300, 300);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    algorithm.addToPath(event.getX(), event.getY());

                    gc.beginPath();
                    gc.moveTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    algorithm.addToPath(event.getX(), event.getY());

                    gc.lineTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    algorithm.predict();
                });

        return canvas;
    }
}