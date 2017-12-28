package hr.fer.tzadro.nenr.lab5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    private int width = 300;
    private int height = 300;

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int M = 20;
        String DATASET_PATH = "./materijali/zad5-datasets/zad5-dataset-" + M + ".txt";
        int[] HIDDEN_LAYERS = new int[]{40, 40, 20};

        controller = new Controller(M, HIDDEN_LAYERS, DATASET_PATH);

        primaryStage.setTitle("Greek alphabet classifier");
        Group root = new Group();
        root.getChildren().add(createCanvas());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Canvas createCanvas() {
        Canvas canvas = new Canvas(width, height);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    controller.newPoint(event.getX(), event.getY());

                    gc.clearRect(0, 0, width, height);
                    gc.beginPath();
                    gc.moveTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    controller.newPoint(event.getX(), event.getY());

                    gc.lineTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> controller.mouseReleased());

        return canvas;
    }
}