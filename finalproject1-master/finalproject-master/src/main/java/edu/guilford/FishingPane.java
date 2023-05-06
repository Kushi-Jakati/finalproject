package edu.guilford;

import java.io.File;
import java.util.Random;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class FishingPane extends Pane {

    SeaCreatures seaCreature = new SeaCreatures();
    // File smallFish = new
    // File(FishingPane.class.getResource("/smallfish.png").toURI());
    private ImageView smallFish;
    private ImageView smallFish2;
    private ImageView smallFish3;
    private ImageView largeFish;
    private ImageView largeFish2;
    private ImageView woodenStick;
    private ImageView octopus;
    private ImageView fisherman;

    //instantiate a line shape to be used as the fishing line
    private Line line; 

    Random random = new Random();
    Timer timer;

    // declare a helpButton
    Button helpButton = new Button("Help");

    // declare a stopButton
    Button stopButton = new Button("Stop");

    // Constructor
    public FishingPane(SeaCreatures seaCreature) {
        this.seaCreature = seaCreature;

        smallFish = new ImageView(
                new File(FishingPane.class.getResource("smallfish.png").getPath()).toURI().toString());
        smallFish2 = new ImageView(
                new File(FishingPane.class.getResource("smallfish_2.png").getPath()).toURI().toString());
        smallFish3 = new ImageView(
                new File(FishingPane.class.getResource("smallfish_3.png").getPath()).toURI().toString());

        largeFish = new ImageView(
                new File(FishingPane.class.getResource("largefish.png").getPath()).toURI().toString());

        largeFish2 = new ImageView(
                new File(FishingPane.class.getResource("largefish_2.png").getPath()).toURI().toString());
        woodenStick = new ImageView(
                new File(FishingPane.class.getResource("woodenstick.png").getPath()).toURI().toString());
        octopus = new ImageView(new File(FishingPane.class.getResource("octopus.png").getPath()).toURI().toString());
        fisherman = new ImageView(
                new File(FishingPane.class.getResource("fisherman.png").getPath()).toURI().toString());

        // Animate fishes and seacreatures
        TranslateTransition smallFishTransition = new TranslateTransition();
        smallFishTransition.setNode(smallFish);
        smallFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
        smallFishTransition.setByX(-1000);
        smallFishTransition.setCycleCount(0);
        smallFishTransition.play();

        TranslateTransition smallFishTransition2 = new TranslateTransition();
        smallFishTransition2.setNode(smallFish2);
        smallFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
        smallFishTransition2.setByX(-1000);
        smallFishTransition2.setCycleCount(0);
        smallFishTransition2.play();

        TranslateTransition smallFishTransition3 = new TranslateTransition();
        smallFishTransition3.setNode(smallFish3);
        smallFishTransition3.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
        smallFishTransition3.setByX(-1000);
        smallFishTransition3.setCycleCount(0);
        smallFishTransition3.play();

        TranslateTransition largeFishTransition = new TranslateTransition();
        largeFishTransition.setNode(largeFish);
        largeFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
        largeFishTransition.setByX(-1000);
        largeFishTransition.setCycleCount(0);
        largeFishTransition.play();

        TranslateTransition largeFishTransition2 = new TranslateTransition();
        largeFishTransition2.setNode(largeFish2);
        largeFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
        largeFishTransition2.setByX(-1000);
        largeFishTransition2.setCycleCount(0);
        largeFishTransition2.play();

        TranslateTransition woodenStickTransition = new TranslateTransition();
        woodenStickTransition.setNode(woodenStick);
        woodenStickTransition.setDuration(javafx.util.Duration.seconds((random.nextInt(5) + 10)));
        woodenStickTransition.setByX(-1000);
        woodenStickTransition.play();


        // TranslateTransition octopusTransition = new TranslateTransition();
        // octopusTransition.setNode(octopus);
        // octopusTransition.setDuration(javafx.util.Duration.seconds(15));
        // octopusTransition.setByX(-1000);
        // octopusTransition.play();

        // to the far right of the pane in the middle
        smallFish.setX(900);
        smallFish2.setX(900);
        smallFish3.setX(900);
        largeFish.setX(900);
        largeFish2.setX(900);
        woodenStick.setX(900);

        //set line right below the fisherman
        //parameters represent x1, y1, x2, y2 which are the starting and ending points of the line
        line = new Line(325, 190, 325, 650);
        line.setStrokeWidth(2);
        //make line height of 5 pixels
        line.setEndY(300);
        // set Y to a random number between 400 and 500 for small fish
        smallFish.setY(random.nextInt(100) + 400);
        smallFish2.setY(random.nextInt(100) + 400);
        smallFish3.setY(random.nextInt(100) + 400);

        // set Y to a random number between 500 and 600 for wooden stick
        woodenStick.setY(random.nextInt(100) + 500);

        // set large fish Y to a random number between 600 and 700
        largeFish.setY(random.nextInt(100) + 600);
        largeFish2.setY(random.nextInt(100) + 600);

        // set fisherman to the middle of the pane
        fisherman.setX(325);
        fisherman.setY(165);

        // rotate wooden stick
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(woodenStick);
        rotate.setDuration(javafx.util.Duration.seconds(15));
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();

        // add the fish to the pane
        this.getChildren().add(smallFish);
        this.getChildren().add(smallFish2);
        this.getChildren().add(smallFish3);
        this.getChildren().add(largeFish);
        this.getChildren().add(largeFish2);
        this.getChildren().add(woodenStick);
        this.getChildren().add(line);

        // add the fisherman to the middle of the pane
        this.getChildren().add(fisherman);

        // when the fish has completed the animation, remove it from the pane
        smallFishTransition.setOnFinished(e -> {
            this.getChildren().remove(smallFish);
            smallFish = new ImageView(
                    new File(FishingPane.class.getResource("smallfish.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(smallFish);
            smallFishTransition.setNode(smallFish);
            // set duration between 10 and 15 seconds randomly
            smallFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            smallFishTransition.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            smallFish.setX(900);
            // set Y to a random number between 400 and 500
            smallFish.setY(random.nextInt(100) + 400);
            // animate the new fish
            smallFishTransition.play();
            // use timeline keyframe to set a delay of 5 seconds before it creates a new
            // fish
            // Timeline time = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            // // create a new fish
            // smallFish = new ImageView(
            // new
            // File(FishingPane.class.getResource("smallfish.png").getPath()).toURI().toString());
            // // add the new fish to the pane
            // this.getChildren().add(smallFish);
            // smallFishTransition.setNode(smallFish);
            // smallFishTransition.setDuration(javafx.util.Duration.seconds(5));
            // smallFishTransition.setByX(-1000);
            // // set the new fish to the far right of the pane in the middle
            // smallFish.setX(900);
            // //set Y to a random number between 400 and 500
            // smallFish.setY(random.nextInt(100) + 400);
            // // animate the new fish
            // smallFishTransition.play();

            // }));
            // time.setCycleCount(Timeline.INDEFINITE);
            // time.play();

        });

        // when smallfish2 has completed the animation, remove it from the pane
        smallFishTransition2.setOnFinished(e -> {
            this.getChildren().remove(smallFish2);
            smallFish2 = new ImageView(
                    new File(FishingPane.class.getResource("smallfish_2.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(smallFish2);
            smallFishTransition2.setNode(smallFish2);
            smallFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            smallFishTransition2.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            smallFish2.setX(900);
            // set Y to a random number between 400 and 500
            smallFish2.setY(random.nextInt(100) + 400);
            // animate the new fish
            smallFishTransition2.play();
        });

        // add the same thing for smallfish3
        smallFishTransition3.setOnFinished(e -> {
            this.getChildren().remove(smallFish3);
            smallFish3 = new ImageView(
                    new File(FishingPane.class.getResource("smallfish_3.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(smallFish3);
            smallFishTransition3.setNode(smallFish3);
            smallFishTransition3.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            smallFishTransition3.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            smallFish3.setX(900);
            // set Y to a random number between 400 and 500
            smallFish3.setY(random.nextInt(100) + 400);
            // animate the new fish
            smallFishTransition3.play();
        });

        // add the same thing for largefish
        largeFishTransition.setOnFinished(e -> {
            this.getChildren().remove(largeFish);
            largeFish = new ImageView(
                    new File(FishingPane.class.getResource("largefish.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(largeFish);
            largeFishTransition.setNode(largeFish);
            largeFishTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            largeFishTransition.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            largeFish.setX(900);
            // set Y to a random number between 400 and 500
            largeFish.setY(random.nextInt(100) + 600);
            // animate the new fish
            largeFishTransition.play();
        });

        // add the same thing for largefish2
        largeFishTransition2.setOnFinished(e -> {
            this.getChildren().remove(largeFish2);
            largeFish2 = new ImageView(
                    new File(FishingPane.class.getResource("largefish_2.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(largeFish2);
            largeFishTransition2.setNode(largeFish2);
            largeFishTransition2.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            largeFishTransition2.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            largeFish2.setX(900);
            // set Y to a random number between 400 and 500
            largeFish2.setY(random.nextInt(100) + 600);
            // animate the new fish
            largeFishTransition2.play();
        });

        // add the same thing for woodenstick
        woodenStickTransition.setOnFinished(e -> {
            this.getChildren().remove(woodenStick);
            woodenStick = new ImageView(
                    new File(FishingPane.class.getResource("woodenstick.png").getPath()).toURI().toString());
            // add the new fish to the pane
            this.getChildren().add(woodenStick);
            woodenStickTransition.setNode(woodenStick);
            woodenStickTransition.setDuration(javafx.util.Duration.seconds(random.nextInt(5) + 10));
            woodenStickTransition.setByX(-1000);
            // set the new fish to the far right of the pane in the middle
            woodenStick.setX(900);
            // set Y to a random number between 400 and 500
            woodenStick.setY(random.nextInt(100) + 600);
            // rotate wooden stick
            RotateTransition rotateagain = new RotateTransition();
            rotateagain.setNode(woodenStick);
            rotateagain.setDuration(javafx.util.Duration.seconds(15));
            rotateagain.setByAngle(360);
            rotateagain.setCycleCount(Animation.INDEFINITE);
            // animate and rotate the woodenstick
            woodenStickTransition.play();
            rotateagain.play();
        });

        //when the space bar is pressed, extend line down
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                //for every space, increase the line length by 10
                line.setEndY(line.getEndY() + 100);
            }
        });

    }

}