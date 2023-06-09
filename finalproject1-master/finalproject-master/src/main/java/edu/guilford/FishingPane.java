package edu.guilford;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/** Public Class Fishing Pane is where all the SeaCreature objects and from other classes will be instantiated into our mulitple panes
 * @param GAME_OVER_TIME is the time that the game will end
 * @param totalPoints is the total points that the user has earned
 * @param seaCreaturesArray is the array that will hold all the SeaCreature objects
 * @param scoreLabel is the label that will display the score
 * @param seaCreature is the SeaCreature object that will be instantiated
 * @param catchCircle is the circle that will be used to catch the SeaCreature objects
 * @param lineTransition is the transition of the line
 * @param circleTransition is the transition of the circle
 * @param caught is the ArrayList that will hold all the SeaCreature objects that have been caught
 * @param caughtCreatures is the label that will display the SeaCreature objects that have been caught
 */

public class FishingPane extends Pane {

        /** "caught" static ArrayList that will be called in the HelpAnalysis Pane class  */
        static ArrayList<SeaCreatures> caught = new ArrayList<SeaCreatures>();

        /** create a getter for "caught" static ArrayList in order to reference it in methods  */
        public ArrayList<SeaCreatures> getCaught() {
                return caught;
        }

        final int GAME_OVER_TIME = 300;

        static int totalPoints;

        /** SeaCreature Array for the FishingPane class that generates all the objects on the screen*/ 
        ArrayList<SeaCreatures> seaCreaturesArray = new ArrayList<SeaCreatures>();

        /** Add a getter for the SeaCreatures array for the same reason we added a getter for the caught array */
        public ArrayList<SeaCreatures> getSeaCreaturesArray() {
                return seaCreaturesArray;
        }

        /** Instantiate a score label */
        Label scoreLabel = new Label("Score: 0");

        /** Instantiate a mode label. We never implemented modes for the game, thus it is commented out. */
        // Label modeLabel = new Label("Mode: Easy");

        /** Instantiate a SeaCreature object */
        SeaCreatures seaCreature = new SeaCreatures();

        /** Instantiate a circle to be used as the catch circle (AKA hook) */
        Circle catchCircle;

        /** Instantiate a line transition */
        public Circle getCatchCircle() {
                return catchCircle;
        }

        boolean gameComplete = false;

        Random random = new Random();
        Timer timer;

        /**Instantiate a FishingLine object */
        FishingLine fishingLine = new FishingLine();

        /** Constructor for the whole pane */
        public FishingPane(SeaCreatures seaCreature) {
                this.seaCreature = seaCreature;

                 /**@param numOfSeaCreatures is the number of SeaCreature objects that will be generated on the screen
                  * This variable is for the for loop that will generate 50 SeaCreature objects and add them to the SeaCreature array*/
                int numOfSeaCreatures = 50;

                Timer countingTimer = new Timer();

                TimerTask task = new TimerTask() {

                        private int secondsPassed = 0;
                        Label timerLabel = new Label("Time: " + 0);

                        /** This method involving time are constructed in order to create a timer that will be used to display the time passed on the program screen. */
                        @Override
                        public void run() {
                                if (secondsPassed >= 0) {
                                        secondsPassed = secondsPassed + 1;
                                }
                                if (secondsPassed > GAME_OVER_TIME) {
                                        System.out.println("Game Over");
                                        countingTimer.cancel();
                                        gameComplete = true;
                                        fishingLine.transitionLineUpward();
                                        //have it so that the poin
                                }
                                Platform.runLater(() -> {
                                        if (secondsPassed >= GAME_OVER_TIME) {
                                                timerLabel.setText("Game Over");
                                                // set the proportions of the label so it can fit gameover
                                                timerLabel.setPrefSize(120, 50);
                                                // have label scooch over to the left to be aligned with the score label
                                                timerLabel.setLayoutX(380);
                                        } else {
                                                timerLabel.setText("Time: " + secondsPassed);
                                                timerLabel.setFont(new Font("Arial", 20));
                                                timerLabel.setTextFill(Color.WHITE);
                                                timerLabel.setLayoutX(400);
                                                timerLabel.setLayoutY(75);
                                                // set the size of the label
                                                timerLabel.setPrefSize(100, 50);
                                                // set the background of time label to be cadet blue
                                                timerLabel.setStyle("-fx-background-color: darkblue");
                                                // center the text
                                                timerLabel.setAlignment(Pos.CENTER);
                                                Glow glow = new Glow();
                                                glow.setLevel(10);
                                                timerLabel.setEffect(glow);
                                                try {
                                                        getChildren().add(timerLabel);
                                                } catch (Exception e) {
                                                }
                                        }
                                });
                        }
                };

                countingTimer.scheduleAtFixedRate(task, 0, 1000);


                /** Instantiates the Fisherman from the Fisherman class and sets the position of the image. */
                Fisherman fisherman = new Fisherman();
                fisherman.setPosition(fisherman.getXPosition(), fisherman.getYPosition());

                /** Create an arraylist of randomized sea creatures */
                for (int number = 0; number < numOfSeaCreatures; number++) {
                        SeaCreatures seaCreatureObject = new SeaCreatures();
                        seaCreaturesArray.add(seaCreatureObject);
                        this.getChildren().add(seaCreatureObject.getImage());
                }

                /** iterate through the array list and use transition method to animate each sea creature */
                for (int number = 0; number < seaCreaturesArray.size(); number++) {
                        SeaCreatures seaCreatureObject = (SeaCreatures) seaCreaturesArray.get(number);
                        seaCreatureObject.transition(seaCreatureObject.getImage());
                }

                /** This is where we add all the elements of the pane */

                this.getChildren().add(fisherman.getImage());
                this.getChildren().add(fishingLine.getLine());

                /** Setting circle properties so it follows the fishingline add a circle to the line that follows the end of the line */
                Line line = fishingLine.getLine();
                catchCircle = new Circle(line.getEndX(), line.getEndY(), 5);
                this.getChildren().add(catchCircle);
                catchCircle.centerXProperty().bind(line.endXProperty());
                catchCircle.centerYProperty().bind(line.endYProperty());

                
                /** Adding the score label to the pane and setting its properties */
                this.getChildren().add(scoreLabel);
                // add it to the top middle
                scoreLabel.setLayoutX(400);
                scoreLabel.setLayoutY(10);
                // add purple background to score label
                scoreLabel.setStyle("-fx-background-color: PURPLE;");
                // add white text to score label
                scoreLabel.setTextFill(Color.WHITE);
                // make font size 20
                scoreLabel.setFont(new Font(20));
                // make label same size as button
                scoreLabel.setPrefSize(100, 50);
                // make text centered
                scoreLabel.setAlignment(Pos.CENTER);

                /** This was code for the mode label if we were to further develop this project and implement modes */
                // this.getChildren().add(modeLabel);
                // // add it to the bottom left corner
                // modeLabel.setLayoutX(10);
                // modeLabel.setLayoutY(700);
                // // add purple background to mode label
                // modeLabel.setStyle("-fx-background-color: PURPLE;");
                // // add white text to mode label
                // modeLabel.setTextFill(Color.WHITE);
                // // make font size 20
                // modeLabel.setFont(new Font(20));
                // // make label fit text
                // modeLabel.setPrefSize(130, 50);
                // // make text centered
                // modeLabel.setAlignment(Pos.CENTER);


                /** We had to make the line in the right position and with the right width. Paramaters of the line object represent x1, y1, x2, y2, which are the starting and ending points of
                 * the line. We had to set the starting and ending points of the line to the same point so that the line would be straight. We had to set the width of the line to 2 so that it look nice.
                   */
                line = new Line(339, 183, 339, 650);
                line.setStrokeWidth(2);
                line.setStartX(339);
                line.setStartY(183);
                line.setEndX(317);
                line.setEndY(827);

                /** This is adding all the necessary buttons the pane. */
                // add help button to the top right of the pane
                Button helpButton = new Button("Help");
                helpButton.setLayoutX(780);
                helpButton.setLayoutY(10);
                helpButton.setPrefSize(100, 20);
                // set color to purple and text to white
                helpButton.setStyle("-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                this.getChildren().add(helpButton);

                // add save button the top left of the pane. This button is slightly weird in the manner that it needs to be there in order for the user to continue manipulating the fishingline
                //after they click the help button. If we removed it, the user would not be able to continue manipulating the fishingline after they click the help button. It served no purpose,
                //but we had to keep it there, so we set its opacity to 0 so that it is invisible.
                Button saveButton = new Button("Save");
                saveButton.setLayoutX(10);
                saveButton.setLayoutY(750);
                saveButton.setPrefSize(100, 20);
                saveButton.setStyle("-fx-background-color: PURPLE; -fx-text-fill: #ffffff;-fx-font-size: 20px;");
                saveButton.setOpacity(0);
                this.getChildren().add(saveButton);

                /** Event handler that handles the keyboard input (down and up arrows, in addition to c as the capture key) to manipulate the line and catch the fish using the catch method
                 * which requires the user to have the line overlap with a fish and then press the c key to catch the fish. 
                 */
                this.addEventHandler(KeyEvent.ANY, new EventHandler<Event>() {

                        @Override
                        public void handle(Event event) {
                                if (event instanceof KeyEvent) {
                                        KeyEvent keyEvent = (KeyEvent) event;
                                        if (keyEvent.getCode() == KeyCode.UP) {
                                                fishingLine.transitionLineUpward();
                                                // catchFish();
                                        } else if (keyEvent.getCode() == KeyCode.DOWN) {
                                                fishingLine.transitionLineDownward();
                                                // catchFish();
                                        }
                                        // The event below allows for a specific letter to catch a fish
                                        else if (keyEvent.getCode() == KeyCode.C) {
                                                catchFish();
                                        }

                                        // The below code allows for any keyboard key to catch a fish
                                        // else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                                        // catchFish();
                                        // }

                                }

                        }

                });

        }


        /** This method is a conditional for when the user's circle (AKA hook) intersects with a fish, which then allows the user to catch the fish and add 
         * the fish to the caught arraylist. The method also adds the points of the fish in the array to the total points of the user. This method is called in the event handler dealing with keys.
         */
        public void catchFish() {
                for (int i = 0; i < seaCreaturesArray.size(); i++) {
                        SeaCreatures seaCreatureObject = (SeaCreatures) seaCreaturesArray.get(i);
                        // use getBoundsinParents method to see if the line intersects with the image of
                        // the sea creature
                        if (catchCircle.getBoundsInParent()
                                        .intersects(seaCreatureObject.getImage().getBoundsInParent())) {
                                // remove the image from the pane
                                this.getChildren().remove(seaCreatureObject.getImage());
                                // remove the sea creature from the array list
                                seaCreaturesArray.remove(seaCreatureObject);
                                // add the sea creature to the caught arraylist
                                caught.add(seaCreatureObject);
                                // add all creature points together from the caught arraylist
                                totalPoints = 0;
                                for (int number = 0; number < caught.size(); number++) {
                                        SeaCreatures seaCreatureObject1 = (SeaCreatures) caught.get(number);
                                        totalPoints += seaCreatureObject1.getPoints();
                                        // make the score label display the total points
                                        scoreLabel.setText("Score: " + totalPoints);
                                        /**This was code for the modes if we were ever to implement it. A lot of troubleshooting needed to be done with the modes that time did not
                                         * allow for (since it involved possible restructuring the code), thus we left this code here just for future reference if we ever came back to this project.
                                         */
                                        //if seconds passed is greater than game time, then game is over
                                        // for (int j = 0; j < seaCreaturesArray.size(); j++) {
                                        //         SeaCreatures seaCreatureObject2 = (SeaCreatures) seaCreaturesArray
                                        //                         .get(i);
                                        //         if (totalPoints > 0 && totalPoints < 10) {
                                        //                 seaCreatureObject2.setMode("easy");
                                        //                 // change mode label to medium
                                        //                 modeLabel.setText("Mode: Easy");
                                        //                 //seaCreatureObject2.transition1(seaCreatureObject2.getImage());
                                        //         } else if (totalPoints > 10 && totalPoints < 40) {
                                        //                 seaCreatureObject2.setMode("medium");
                                        //                 // change mode label to medium
                                        //                 modeLabel.setText("Mode: Medium");
                                        //                 //seaCreatureObject2.transition1(seaCreatureObject2.getImage());
                                        //         } else if (totalPoints > 40) {
                                        //                 seaCreatureObject2.setMode("hard");
                                        //                 // change mode label to medium
                                        //                 modeLabel.setText("Mode: Hard");
                                        //                 //seaCreatureObject2.transition1(seaCreatureObject2.getImage());
                                        //         }
                                        // }
                                }
                        }

                }
        }
};

/** This class is used to display what scores each seacreature represents.
 * @param smallFishLabel is the label that displays the points for the small fish
 * @param largeFishLabel is the label that displays the points for the large fish
 * @param woodenStickLabel is the label that displays the points for the wooden stick
 * @param octopusLabel is the label that displays the points for the octopus
 */

class KeyPane extends Pane {

        SeaCreatures seaCreature = new SeaCreatures();

        private Label smallFishLabel;
        private Label largeFishLabel;
        private Label woodenStickLabel;
        private Label octopusLabel;

        /** KeyPane is the constructor that is used to display what socres each sea creature represents and trigger the help button so it displays the interface elements and
         * the save button that allows the user to save their score to a file named with whatever username they input.
         */
        public KeyPane() {
                // label to get points for the smallfish from the seacreature class
                SeaCreatures smallFish = new SeaCreatures("Small Fish", "easy");
                smallFishLabel = new Label("Small Fish: " + smallFish.getPoints() + " points");
                // label to get points for the largefish from the seacreature class
                SeaCreatures largeFish = new SeaCreatures("Large Fish", "easy");
                largeFishLabel = new Label("Large Fish: " + largeFish.getPoints() + " points");
                // label to get points for the woodenstick from the seacreature class
                SeaCreatures woodenStick = new SeaCreatures("Wooden Stick", "easy");
                woodenStickLabel = new Label("Wooden Stick: " + woodenStick.getPoints() + " points");
                // label to get points for the octopus from the seacreature class
                SeaCreatures octopus = new SeaCreatures("Octopus", "easy");
                octopusLabel = new Label("Octopus: " + octopus.getPoints() + " points");

                // set the layout of the labels
                smallFishLabel.setLayoutX(10);
                smallFishLabel.setLayoutY(10);
                largeFishLabel.setLayoutX(10);
                largeFishLabel.setLayoutY(30);
                woodenStickLabel.setLayoutX(10);
                woodenStickLabel.setLayoutY(50);
                octopusLabel.setLayoutX(10);
                octopusLabel.setLayoutY(70);
                this.getChildren().addAll(smallFishLabel, largeFishLabel, woodenStickLabel, octopusLabel);

                Button helpButton = new Button("Help");
                helpButton.setLayoutX(780);
                helpButton.setLayoutY(10);
                helpButton.setPrefSize(100, 20);
                // set color to purple and text to white
                helpButton.setStyle("-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                this.getChildren().add(helpButton);

                /** When the help button is clicked, the help panel will pop up with features such as reccomendations and an image of the last fish caught as well
                 * as helpful instructions on how to play the game.
                 */
                helpButton.setOnAction(e -> {
                        Stage helpStage = new Stage();
                        Pane helpPane = new Pane();
                        Scene helpScene = new Scene(helpPane, 500, 500);
                        helpStage.setScene(helpScene);
                        helpStage.setTitle("Help");
                        helpStage.show();

                        // add instructions at the top of the pane
                        Label instructions = new Label("How to Play: ");
                        instructions.setAlignment(Pos.CENTER);
                        instructions.setPrefSize(500, 50);
                        // Make the label purple and text white
                        instructions.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                        helpPane.getChildren().add(instructions);
                        Label step1 = new Label(
                                        "Step 1: Move the fishing line up and down using the arrow keys.");
                        step1.setWrapText(true);
                        step1.setTextAlignment(TextAlignment.JUSTIFY);
                        step1.setPrefSize(500, 30);
                        step1.setLayoutY(50);
                        helpPane.getChildren().add(step1);
                        // Make the label purple and text white
                        step1.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
                        // Give the label a thin black border
                        step1.setBorder(new Border(
                                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                                        BorderWidths.DEFAULT)));
                        Label step2 = new Label("Step 2: Press the 'C' key to catch a fish.");
                        step2.setWrapText(true);
                        step2.setTextAlignment(TextAlignment.JUSTIFY);
                        step2.setPrefSize(500, 30);
                        step2.setLayoutY(80);
                        helpPane.getChildren().add(step2);
                        // Make the label purple and text white
                        step2.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
                        // Give the label a thin black border
                        step2.setBorder(new Border(
                                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                                        BorderWidths.DEFAULT)));
                        Label step3 = new Label(
                                        "Step 3: Have fun! You have 300 seconds to catch as many fish as you can.");
                        step3.setWrapText(true);
                        step3.setTextAlignment(TextAlignment.JUSTIFY);
                        step3.setPrefSize(500, 30);
                        step3.setLayoutY(110);
                        helpPane.getChildren().add(step3);
                        // Make the label purple and text white
                        step3.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
                        // Give the label a thin black border
                        step3.setBorder(new Border(
                                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                                        BorderWidths.DEFAULT)));

                        HBox recentCatch = new HBox();
                        recentCatch.setLayoutY(140);
                        Label recentCatchLabel = new Label("Your Latest Catch: ");
                        recentCatchLabel.setPrefSize(250, 50);
                        recentCatchLabel.setAlignment(Pos.CENTER);
                        recentCatchLabel.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                        recentCatch.getChildren().add(recentCatchLabel);
                        // Give the label a thin black border
                        recentCatch.setBorder(new Border(
                                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                                        BorderWidths.DEFAULT)));
                        helpPane.getChildren().add(recentCatch);

                        if (FishingPane.caught.size() > 0) {
                                SeaCreatures lastCaught = (SeaCreatures) FishingPane.caught
                                                .get(FishingPane.caught.size() - 1);
                                ImageView image = lastCaught.pullImage();
                                image.setFitHeight(50);
                                image.setPreserveRatio(true);
                                recentCatch.getChildren().add(image);
                        } else {
                                Label noImage = new Label("No images to display yet.");
                                noImage.setPrefSize(250, 50);
                                noImage.setAlignment(Pos.CENTER);
                                noImage.setStyle(
                                                "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                                recentCatch.getChildren().add(noImage);

                        }

                        displayAnalysis(helpPane);

                        // add a button to save the game in the bottom left corner
                        Button saveButton = new Button("Save Game");
                        saveButton.setLayoutX(10);
                        saveButton.setLayoutY(450);
                        saveButton.setPrefSize(100, 20);
                        // set color to purple and text to white
                        saveButton.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
                        helpPane.getChildren().add(saveButton);

                        // Add a field next to the save button to prompt the user for a username
                        // this username will be used to save the game under a file
                        TextField usernameField = new TextField();
                        usernameField.setLayoutX(120);
                        usernameField.setLayoutY(450);
                        usernameField.setPrefSize(100, 20);
                        helpPane.getChildren().add(usernameField);

                        /**Once the user enters a username and clicks the save button, the game will save under that username and write the information to a file  */
                        saveButton.setOnAction(e2 -> {
                                // grab the username from the text field
                                String username = usernameField.getText();
                                // create a new file with the username
                                File file = new File(username + ".txt");
                                // if the file already exists, display a message saying that the file
                                // already exists
                                if (file.exists()) {
                                        Label fileExists = new Label("This file already exists!");
                                        fileExists.setLayoutX(120);
                                        fileExists.setLayoutY(480);
                                        helpPane.getChildren().add(fileExists);
                                        // if the file exists, overwrite the file with the new information
                                        try {
                                                // create a new file writer
                                                FileWriter writer = new FileWriter(file);
                                                // write the username to the file
                                                writer.write("Username: " + username + "\n");
                                                // write the score to the file
                                                writer.write("Score: " + FishingPane.totalPoints + "\n");
                                                // write the number of small fish caught to the file
                                                // close the writer
                                                writer.close();
                                                fileExists.setText("File overwritten!");
                                        } catch (IOException e1) {
                                                e1.printStackTrace();
                                        }
                                }
                                // if the file does not exist, create a new file and write the information
                                // to the file
                                else {
                                        try {
                                                // create a new file
                                                file.createNewFile();
                                                // create a new file writer
                                                FileWriter writer = new FileWriter(file);
                                                // write the username to the file
                                                writer.write("Username: " + username + "\n");
                                                // write the score to the file
                                                writer.write("Score: " + FishingPane.totalPoints + "\n");
                                                // write the number of small fish caught to the file
                                                // close the writer
                                                writer.close();
                                        } catch (IOException e1) {
                                                e1.printStackTrace();
                                        }
                                }
                        });

                });

        }

        /** This is an analysis method that analyzes the user's last three catches and displays advice for the user in the helpPane. E.g., user catches three wooden sticks in a row, 
         * so program suggests that user catch something that won't give them negative points and will rather increase their points.
         */
        public void displayAnalysis(Pane helpPane) {

                // ** THIS NEEDS TO BE DECLARED FIRST */
                ArrayList<SeaCreatures> caught = FishingPane.caught;
                // Once this is done, add a switch statement to decide what the pane should show

                // grab the length of the list and check the last 3 objects in the list
                // provide suggestions based on this information

                // if the list is empty, display a message saying that the user has not caught
                // anything yet
                if (caught.size() == 0) {
                        Label noCatch = new Label("You have not caught anything yet!");
                        noCatch.setAlignment(Pos.CENTER);
                        noCatch.setLayoutY(200);
                        noCatch.setPrefWidth(500);
                        // Set the label to purple and the text to white
                        noCatch.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                        helpPane.getChildren().add(noCatch);
                }
                // if the user has caught 1 object, display what type of object it is and
                // how many points it is worth
                else if (caught.size() >= 1) {
                        Label oneCatch = new Label(
                                        "You have caught a " + caught.get(caught.size() - 1).getName() + " worth "
                                                        + caught.get(caught.size() - 1).getPoints() + " points!");
                        oneCatch.setLayoutY(200);
                        oneCatch.setPrefWidth(500);
                        oneCatch.setAlignment(Pos.CENTER);
                        // italicize the text
                        oneCatch.setStyle("-fx-font-style: italic");
                        // Set the label to purple and the text to white
                        oneCatch.setStyle(
                                        "-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                        helpPane.getChildren().add(oneCatch);
                        // if the last three objects in the caught array are woodensticks, tell the user
                        // to try a different bait
                        // if the last three objects in the caught array are woodensticks, tell the user
                        // to try a different bait
                        if (caught.get(caught.size() - 1).getName().equals("Wooden Stick")
                                        && caught.get(caught.size() - 2).getName().equals("Wooden Stick")
                                        && caught.get(caught.size() - 3).getName().equals("Wooden Stick")) {
                                Label woodenStickAnalysis = new Label(
                                                "You have caught 3 wooden sticks in a row! Try a different bait!");
                                woodenStickAnalysis.setLayoutX(10);
                                woodenStickAnalysis.setLayoutY(250);
                                // bold the label
                                woodenStickAnalysis.setStyle("-fx-font-weight: bold");
                                woodenStickAnalysis.setAlignment(Pos.CENTER);
                                helpPane.getChildren().add(woodenStickAnalysis);
                        } else if (caught.get(caught.size() - 1).getName().equals("Octopus")
                                        && caught.get(caught.size() - 2).getName().equals("Octopus")
                                        && caught.get(caught.size() - 3).getName().equals("Octopus")) {
                                Label octopusAnalysis = new Label(
                                                "You have caught 3 octopuses in a row! You are doing a great job!");
                                octopusAnalysis.setLayoutX(10);
                                octopusAnalysis.setLayoutY(250);
                                // bold the label
                                octopusAnalysis.setStyle("-fx-font-weight: bold");
                                octopusAnalysis.setAlignment(Pos.CENTER);
                                helpPane.getChildren().add(octopusAnalysis);
                        } else if (caught.get(caught.size() - 1).getName().equals("Small Fish")
                                        && caught.get(caught.size() - 2).getName().equals("Small Fish")
                                        && caught.get(caught.size() - 3).getName().equals("Small Fish")) {
                                Label smallFishAnalysis = new Label(
                                                "You have caught 3 small fish in a row! Try upgrading to larger fish!");
                                smallFishAnalysis.setLayoutX(10);
                                smallFishAnalysis.setLayoutY(250);
                                // bold the label
                                smallFishAnalysis.setStyle("-fx-font-weight: bold");
                                smallFishAnalysis.setAlignment(Pos.CENTER);
                                helpPane.getChildren().add(smallFishAnalysis);
                        } else if (caught.get(caught.size() - 1).getName().equals("Large Fish")
                                        && caught.get(caught.size() - 2).getName().equals("Large Fish")
                                        && caught.get(caught.size() - 3).getName().equals("Large Fish")) {
                                Label largeFishAnalysis = new Label(
                                                "You have caught 3 large fish in a row! Try to catch an octopus (they're 30 points)!");
                                largeFishAnalysis.setLayoutX(10);
                                largeFishAnalysis.setLayoutY(250);
                                // bold the label
                                largeFishAnalysis.setStyle("-fx-font-weight: bold");
                                largeFishAnalysis.setAlignment(Pos.CENTER);
                                helpPane.getChildren().add(largeFishAnalysis);
                        }
                }

        }

}


