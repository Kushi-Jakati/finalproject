package edu.guilford;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This class is the main class for the Fishing Game. It creates the window and
 * sets the background image. It also creates the KeyPane and FishingPane
 * objects and adds them to the window.
 * This project was created by Sarah Sheets, Kushi Jakati, and Connor Moran.
 * It was created for the Guilford College CTIS 310 class in the Spring of 2023.
 * The purpose of this project was to expand our knowledge of programming in Java. 
 * We created an interactive fishing game that allows the user to catch fish, along with other 
 * fun elements. We used the JavaFX library to create the GUI and animations, and we used inheritance
 * to create abstract super classes and subclasses. Exception handling was used to catch errors
 * and prevent the program from crashing. 
 */

/** Extends Javafx Animation class **/
public class MainFinal extends Application {

    private static Scene scene;
    private static Scene key;

    /**
     * This method creates the window and sets the background image. It also creates
     * the KeyPane and FishingPane objects and adds them to the window.
     * 
     * @param PrimaryStage the window
     * @throws IOException        if the file is not found
     * @throws URISyntaxException if the URI is not found
     */

    @Override
    public void start(Stage PrimaryStage) throws IOException, URISyntaxException {
        /** Instantiate the controller **/
        StackPane root = new StackPane();
        scene = new Scene(root, 900, 828);
        SeaCreatures seaCreature = new SeaCreatures();
        /** Set background image using the ocean.png file through the Image class and the
        BackgroundImage class **/
        Image image = new Image(new File(MainFinal.class.getResource("ocean_2.png").getPath()).toURI().toString());
        /** Resize the image **/
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        /** Set the background image **/
        root.setBackground(new Background(backgroundImage));

        PrimaryStage.setScene(scene);
        PrimaryStage.setTitle("Fishing Game");
        PrimaryStage.show();

        /** This makes it so that the program stops when the window is closed **/
        PrimaryStage.setOnCloseRequest(e -> System.exit(0));

        /** Use StackPane to overlap the panes */
        VBox keyPane = new VBox();
        StackPane overlap = new StackPane(keyPane);
        keyPane.setOpacity(1);

        /** Make the primary window non-resizable **/
        PrimaryStage.setResizable(false);

        /** Add the panes to the window **/
        root.getChildren().addAll(new FishingPane(seaCreature), new KeyPane());

    }

    /**
     * This method is the main method. It launches the program.
     * 
     * @param args
     */

    public static void main(String[] args) {
        launch();
    }

}