package com.example.demo3;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class ImageGalleryApp extends Application {

    private static final int THUMBNAIL_SIZE = 120;
    private static final int FULL_IMAGE_SIZE = 400;

    private int currentIndex = 0;
    private final Image[] images = {
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 1.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 2.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 3.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 4.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 5.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 6.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 7.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 8.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 9.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 10.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 11.jpg"),
            loadImage("C:/Users/KEKE MOLISE/IdeaProjects/demo3/src/main/resources/com/example/demo3/images/image 12.jpg"),

    };

    private final ImageView imageView = new ImageView();

    @Override

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        FlowPane thumbnailPane = createThumbnailPane();

        Button prevButton = new Button("<");
        prevButton.setOnAction(e -> showPreviousImage());

        Button nextButton = new Button(">");
        nextButton.setOnAction(e -> showNextImage());

        BorderPane.setAlignment(prevButton, Pos.CENTER_LEFT);
        BorderPane.setAlignment(nextButton, Pos.CENTER_RIGHT);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(prevButton);
        bottomPane.setRight(nextButton);

        root.setCenter(imageView);
        root.setTop(thumbnailPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm()); // Relative path to CSS file
        primaryStage.setTitle("MY GALLERY");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FlowPane createThumbnailPane() {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        for (int i = 0; i < images.length; i++) {
            ImageView thumbnail = new ImageView(images[i]);
            thumbnail.setFitWidth(THUMBNAIL_SIZE);
            thumbnail.setFitHeight(THUMBNAIL_SIZE);
            int index = i;
            thumbnail.setOnMouseClicked(e -> showFullImage(index));
            pane.getChildren().add(thumbnail);
        }

        return pane;
    }

    private void showFullImage(int index) {
        currentIndex = index;
        imageView.setImage(images[index]);
        imageView.setFitWidth(FULL_IMAGE_SIZE);
        imageView.setFitHeight(FULL_IMAGE_SIZE);
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private Image loadImage(String path) {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load image: " + path);
            alert.showAndWait();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}











