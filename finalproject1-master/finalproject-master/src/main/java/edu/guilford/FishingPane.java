package edu.guilford;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FishingPane extends Pane {

        // "caught" static ArrayList that will be called in the HelpAnalysis Pane class

        static ArrayList<SeaCreatures> caught = new ArrayList<SeaCreatures>();

        // create a getter for the caught array list
        public ArrayList<SeaCreatures> getCaught() {
                return caught;
        }

        final int GAME_OVER_TIME = 120;

        // SeaCreature Array for the FishingPane class that generates all the objects on
        // the screen
        ArrayList<SeaCreatures> seaCreaturesArray = new ArrayList<SeaCreatures>();

        // add a getter for the seaCreaturesArray
        public ArrayList<SeaCreatures> getSeaCreaturesArray() {
                return seaCreaturesArray;
        }

        // instantiate a score label
        Label scoreLabel = new Label("Score: 0");

        // instantiate seaCreatures
        SeaCreatures seaCreature = new SeaCreatures();

        // instantiate a catchCircle
        Circle catchCircle;

        // add a getter for the catchCircle
        public Circle getCatchCircle() {
                return catchCircle;
        }

        // instantiate a line shape to be used as the fishing line
        private Line line;

        boolean gameComplete = false;

        Random random = new Random();
        Timer timer;

        FishingLine fishingLine = new FishingLine();

        // create a getter method for fishingLine

        // Constructor
        public FishingPane(SeaCreatures seaCreature) {
                this.seaCreature = seaCreature;

                int numOfSeaCreatures = 50;

                // divide the ArrayList into smaller sections based on time
                // between 0 and 30 seconds, display sea creatures of indeces 0-24
                // between 30 and 60 seconds, display sea creatures of indeces 25-49
                // between 60 and 90 seconds, display sea creatures of indeces 50-74
                // between 90 and 120 seconds, display sea creatures of indeces 75-99
                // between 120 and 150 seconds, display sea creatures of indeces 100-124
                // between 150 and 180 seconds, display sea creatures of indeces 125-149
                // between 180 and 210 seconds, display sea creatures of indeces 150-174
                // between 210 and 240 seconds, display sea creatures of indeces 175-199
                // between 240 and 270 seconds, display sea creatures of indeces 200-224
                // between 270 and 300 seconds, display sea creatures of indeces 225-249

                System.out.println(seaCreature.getName());

                // create a new small fish
                SeaCreatures fish = new SeaCreatures();
                fish.transition(fish.getImage());
                System.out.println(fish.getTransition().getCycleCount());

                Timer countingTimer = new Timer();

                TimerTask task = new TimerTask() {

                        private int secondsPassed = 0;
                        Label timerLabel = new Label("Time: " + 0);

                        @Override
                        public void run() {
                                if (secondsPassed >= 0) {
                                        System.out.println(secondsPassed + " seconds");
                                        secondsPassed = secondsPassed + 1;
                                }
                                if (secondsPassed > GAME_OVER_TIME) {
                                        System.out.println("Game Over");
                                        countingTimer.cancel();
                                        gameComplete = true;
                                        fishingLine.transitionLineUpward();
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

                // instantiating the fisherman
                Fisherman fisherman = new Fisherman();
                fisherman.setPosition(fisherman.getXPosition(), fisherman.getYPosition());

                // create an array list of 9 random sea creatures
                for (int number = 0; number < numOfSeaCreatures; number++) {
                        SeaCreatures seaCreatureObject = new SeaCreatures();
                        seaCreaturesArray.add(seaCreatureObject);
                        this.getChildren().add(seaCreatureObject.getImage());
                }

                // iterate through the array list and use transition method to animate each sea
                // creature
                for (int number = 0; number < seaCreaturesArray.size(); number++) {
                        SeaCreatures seaCreatureObject = (SeaCreatures) seaCreaturesArray.get(number);
                        seaCreatureObject.transition(seaCreatureObject.getImage());
                        // set the transition to repeat forever
                        // seaCreatureObject.getTransition().setOnFinished(e -> {
                        // //have the transition repeat forever
                        // //remove the image from the pane and array list
                        // this.getChildren().remove(seaCreatureObject.getImage());
                        // //remove from the array list
                        // //seaCreaturesArray.remove(seaCreatureObject);
                        // //add a new random one to the pane and array list
                        // SeaCreatures newSeaCreatureObject = new SeaCreatures();
                        // //set all properties of the new sea creature to the old one
                        // seaCreatureObject.setName(newSeaCreatureObject.getName());
                        // seaCreatureObject.setPoints(newSeaCreatureObject.getPoints());
                        // seaCreatureObject.setImage(newSeaCreatureObject.getImage());
                        // seaCreatureObject.setTransition(newSeaCreatureObject.getTransition());
                        // seaCreatureObject.transition(seaCreatureObject.getImage());
                        // this.getChildren().add(seaCreatureObject.getImage());
                        // //newSeaCreatureObject.transition(newSeaCreatureObject.getImage());
                        // });

                }

                // for loop for seaCreatures that once the transition is finished, it removes
                // the image from the pane and the array list and adds a new random one
                // to the pane and array list. It keeps doing this every time the transition is
                // finished for each object, so it is constantly adding and removing
                // objects from the pane and array list

                // get each element of the arraylist
                SeaCreatures seaCreature1 = (SeaCreatures) seaCreaturesArray.get(0);
                SeaCreatures seaCreature2 = (SeaCreatures) seaCreaturesArray.get(1);
                SeaCreatures seaCreature3 = (SeaCreatures) seaCreaturesArray.get(2);
                SeaCreatures seaCreature4 = (SeaCreatures) seaCreaturesArray.get(3);
                SeaCreatures seaCreature5 = (SeaCreatures) seaCreaturesArray.get(4);
                SeaCreatures seaCreature6 = (SeaCreatures) seaCreaturesArray.get(5);
                SeaCreatures seaCreature7 = (SeaCreatures) seaCreaturesArray.get(6);
                SeaCreatures seaCreature8 = (SeaCreatures) seaCreaturesArray.get(7);
                SeaCreatures seaCreature9 = (SeaCreatures) seaCreaturesArray.get(8);

                // event listener for each cycle of the transition

                // for each new cycle of the transition, have a new random sea creature appear
                // and replace the attributes of the old one

                // //while loop for each sea creature that once the transition is finished, it
                // removes the image from the pane and the array list and adds a new random one
                // //to the pane and array list. It keeps doing this every time the transition
                // is finished for each object, so it is constantly adding and removing

                // //make this transition repeat forever
                // seaCreature1.getTransition().setOnFinished(e -> {
                // //have the transition repeat forever
                // //remove the image from the pane and array list
                // //this.getChildren().remove(seaCreature1.getImage());
                // //remove from the array list
                // //seaCreaturesArray.remove(seaCreatureObject);
                // //add a new random one to the pane and array list
                // SeaCreatures newSeaCreatureObject = new SeaCreatures();
                // //set all properties of the new sea creature to the old one
                // seaCreature1.setName(newSeaCreatureObject.getName());
                // seaCreature1.setPoints(newSeaCreatureObject.getPoints());
                // seaCreature1.setImage(newSeaCreatureObject.getImage());
                // seaCreature1.setTransition(newSeaCreatureObject.getTransition());
                // seaCreature1.transition(seaCreature1.getImage());
                // this.getChildren().add(seaCreature1.getImage());
                // //newSeaCreatureObject.transition(newSeaCreatureObject.getImage());
                // });

                // will do rest if the code above works

                // add the fisherman and fishingline to the pane
                this.getChildren().add(fisherman.getImage());
                this.getChildren().add(fishingLine.getLine());
                // request focus on the fishing line
                fishingLine.getLine().requestFocus();
                // allow the fishingpane to have focus after other windows are open
                this.setFocusTraversable(true);

                // Setting circle properties so it follows the fishingline
                // add a circle to the line that follows the line
                Line line = fishingLine.getLine();
                catchCircle = new Circle(line.getEndX(), line.getEndY(), 5);
                this.getChildren().add(catchCircle);
                // have circle folllow the end of the line
                catchCircle.centerXProperty().bind(line.endXProperty());
                catchCircle.centerYProperty().bind(line.endYProperty());

                System.out.println(caught.size());

                // For the score label
                // add the score label to the pane and set its properties]
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

                // parameters represent x1, y1, x2, y2 which are the starting and ending points
                // of the line
                // -467/14
                // have the line
                line = new Line(339, 183, 339, 650);
                line.setStrokeWidth(2);
                line.setStartX(339);
                line.setStartY(183);
                line.setEndX(317);
                line.setEndY(827);

                // use translate transition to move line downward
                PathTransition lineTransition = new PathTransition();
                lineTransition.setNode(line);
                lineTransition.setDuration(javafx.util.Duration.seconds(10));
                lineTransition.setPath(line);
                lineTransition.play();

                // add buttons
                // add help button to the top right of the pane
                Button helpButton = new Button("Help");
                helpButton.setLayoutX(780);
                helpButton.setLayoutY(10);
                helpButton.setPrefSize(100, 20);
                // set color to purple and text to white
                helpButton.setStyle("-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                this.getChildren().add(helpButton);

                // Event handler that handles the keyboard input to manipulate the line and then
                // catch the fish
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
                                // System.out.println(caught.size());
                                // add all creature points together from the caught arraylist
                                int totalPoints = 0;
                                for (int number = 0; number < caught.size(); number++) {
                                        SeaCreatures seaCreatureObject1 = (SeaCreatures) caught.get(number);
                                        totalPoints += seaCreatureObject1.getPoints();
                                        // make the score label display the total points
                                        scoreLabel.setText("Score: " + totalPoints);
                                }
                        }

                }
        }
};

class KeyPane extends Pane {

        SeaCreatures seaCreature = new SeaCreatures();

        private Label smallFishLabel;
        private Label largeFishLabel;
        private Label woodenStickLabel;
        private Label octopusLabel;

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

                // bold all the labels
                smallFishLabel.setStyle("-fx-font-weight: bold");
                largeFishLabel.setStyle("-fx-font-weight: bold");
                woodenStickLabel.setStyle("-fx-font-weight: bold");
                octopusLabel.setStyle("-fx-font-weight: bold");
                this.getChildren().addAll(smallFishLabel, largeFishLabel, woodenStickLabel, octopusLabel);

                Button helpButton = new Button("Help");
                helpButton.setLayoutX(780);
                helpButton.setLayoutY(10);
                helpButton.setPrefSize(100, 20);
                // set color to purple and text to white
                helpButton.setStyle("-fx-background-color: PURPLE; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
                this.getChildren().add(helpButton);

                // when the help button is clicked, the help stage will show
                helpButton.setOnAction(e -> {
                        Stage helpStage = new Stage();
                        Pane helpPane = new Pane();
                        Scene helpScene = new Scene(helpPane, 500, 500);
                        helpStage.setScene(helpScene);
                        helpStage.setTitle("Help");
                        helpStage.show();

                        // add instructions at the top of the pane
                        Label instructions = new Label("How to Play: ");
                        instructions.setLayoutX(10);
                        instructions.setLayoutY(10);
                        helpPane.getChildren().add(instructions);
                        Label instructions2 = new Label(
                                        "Use the up and down arrow keys to move the fishing line up and down. \n"
                                                        + "Press the 'C' key to catch a fish. \n"
                                                        + "There is a time limit of 300 seconds. \n"
                                                        + "When the time is up, the game will end. \n");
                        instructions2.setLayoutX(10);
                        instructions2.setLayoutY(30);
                        helpPane.getChildren().add(instructions2);

                        displayAnalysis(helpPane);
                });

        }

        // this method will display the analysis of the objects caught by the user
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
                        noCatch.setLayoutX(10);
                        noCatch.setLayoutY(200);
                        helpPane.getChildren().add(noCatch);
                }
                // if the user has caught 1 object, display what type of object it is and
                // how many points it is worth
                else if (caught.size() >= 1) {
                        Label oneCatch = new Label(
                                        "You have caught a " + caught.get(caught.size() - 1).getName() + " worth "
                                                        + caught.get(caught.size() - 1).getPoints() + " points!");
                        oneCatch.setLayoutX(10);
                        oneCatch.setLayoutY(200);
                        // italicize the text
                        oneCatch.setStyle("-fx-font-style: italic");
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

// do a setonFinished for each transition to make it repeat with each object in
// the arraylist

// // when the fish has completed the animation, remove it from the pane
// smallFishTransition.setOnFinished(e -> {
// this.getChildren().remove(smallFish);
// smallFish = new ImageView(
// new File(FishingPane.class.getResource("smallfish.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(smallFish);
// smallFishTransition.setNode(smallFish);
// // set duration between 10 and 15 seconds randomly
// smallFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// smallFishTransition.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// smallFish.setX(900);
// // set Y to a random number between 400 and 500
// smallFish.setY(random.nextInt(100) + 400);
// // animate the new fish
// smallFishTransition.play();

// });

// // when smallfish2 has completed the animation, remove it from the pane
// smallFishTransition2.setOnFinished(e -> {
// this.getChildren().remove(smallFish2);
// smallFish2 = new ImageView(
// new File(FishingPane.class.getResource("smallfish_2.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(smallFish2);
// smallFishTransition2.setNode(smallFish2);
// smallFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// smallFishTransition2.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// smallFish2.setX(900);
// // set Y to a random number between 400 and 500
// smallFish2.setY(random.nextInt(100) + 400);
// // animate the new fish
// smallFishTransition2.play();
// });

// // add the same thing for smallfish3
// smallFishTransition3.setOnFinished(e -> {
// this.getChildren().remove(smallFish3);
// smallFish3 = new ImageView(
// new File(FishingPane.class.getResource("smallfish_3.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(smallFish3);
// smallFishTransition3.setNode(smallFish3);
// smallFishTransition3.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// smallFishTransition3.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// smallFish3.setX(900);
// // set Y to a random number between 400 and 500
// smallFish3.setY(random.nextInt(100) + 400);
// // animate the new fish
// smallFishTransition3.play();
// });

// // add the same thing for largefish
// largeFishTransition.setOnFinished(e -> {
// this.getChildren().remove(largeFish);
// largeFish = new ImageView(
// new File(FishingPane.class.getResource("largefish.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(largeFish);
// largeFishTransition.setNode(largeFish);
// largeFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// largeFishTransition.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// largeFish.setX(900);
// // set Y to a random number between 400 and 500
// largeFish.setY(random.nextInt(100) + 600);
// // animate the new fish
// largeFishTransition.play();
// });

// // add the same thing for largefish2
// // largeFishTransition2.setOnFinished(e -> {
// // this.getChildren().remove(largeFish2);
// // largeFish2 = new ImageView(
// // new
// File(FishingPane.class.getResource("largefish_2.png").getPath()).toURI()
// // .toString());
// // // add the new fish to the pane
// // this.getChildren().add(largeFish2);
// // largeFishTransition2.setNode(largeFish2);
// //
// largeFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// // largeFishTransition2.setByX(-1000);
// // // set the new fish to the far right of the pane in the middle
// // largeFish2.setX(900);
// // // set Y to a random number between 400 and 500
// // largeFish2.setY(random.nextInt(100) + 600);
// // // animate the new fish
// // largeFishTransition2.play();
// // });

// // add the same thing for woodenstick
// woodenStickTransition.setOnFinished(e -> {
// this.getChildren().remove(woodenStick);
// woodenStick = new ImageView(
// new File(FishingPane.class.getResource("woodenstick.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(woodenStick);
// woodenStickTransition.setNode(woodenStick);
// woodenStickTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5)
// + 10));
// woodenStickTransition.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// woodenStick.setX(900);
// // set Y to a random number between 400 and 500
// woodenStick.setY(random.nextInt(100) + 600);
// // rotate wooden stick
// RotateTransition rotateagain = new RotateTransition();
// rotateagain.setNode(woodenStick);
// rotateagain.setDuration(javafx.util.Duration.seconds(15));
// rotateagain.setByAngle(360);
// rotateagain.setCycleCount(Animation.INDEFINITE);
// // animate and rotate the woodenstick
// woodenStickTransition.play();
// rotateagain.play();
// });

// //add the same thing for octopus
// octopusTransition.setOnFinished(e -> {
// this.getChildren().remove(octopus);
// octopus = new ImageView(
// new File(FishingPane.class.getResource("octopus.png").getPath()).toURI()
// .toString());
// // add the new fish to the pane
// this.getChildren().add(octopus);
// octopusTransition.setNode(octopus);
// //set duration between 15 and 30 seconds
// octopusTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(30)
// + 30));
// octopusTransition.setByX(-1000);
// // set the new fish to the far right of the pane in the middle
// octopus.setX(900);
// // set Y to a random number between 700 and 800
// octopus.setY(random.nextInt(100) + 700);
// // animate the new fish
// octopusTransition.play();
// });

// have line extend down to the bottom of the pane and then back up with every
// spacebar press
// TranslateTransition lineTransition = new TranslateTransition();
// lineTransition.setOnFinished(e -> {
// lineTransition.setNode(line);
// lineTransition.setDuration(javafx.util.Duration.seconds(8));
// lineTransition.setByY(183);
// lineTransition.setByX(339);
// lineTransition.play();
// lineTransition.setOnFinished(ev -> {
// lineTransition.setNode(line);
// lineTransition.setDuration(javafx.util.Duration.seconds(8));
// lineTransition.setByY(829);
// lineTransition.setByX(317);
// lineTransition.play();
// });

// event listener for space bar
// this.setOnKeyPressed(e -> {
// if (e.getCode() == KeyCode.SPACE) {
// // if the line is not already extended, extend it
// line = new Line(339, 183, 339, 650 + 100);
// line.setEndY(650 + 100);
// line.setEndX(339);

// }
// });