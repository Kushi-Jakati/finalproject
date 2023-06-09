package edu.guilford;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

/**
 * SeaCreatures class is a subclass of GraphicalObjects and is used to create
 * the sea creatures that are caught in the fishing game
 * @param points is the points for catching the creature
 * @param cycles is the number of TranslateTransition cycles for the creature
 * @param name is the name of the creature
 * @param image is the image of the creature
 * @param mode is the game mode (easy, medium, hard) -- however, this is currently not used but we would look forward to using this in future development 
 * of this program. Thus, the setup is there but it is not used in the current program. 
 * @param transition is the TranslateTransition for the creature
 * @param random1 is a random number generator that is not a part of the object constructor but is used in the FishingPane class to generate a random number.
 */

public class SeaCreatures extends GraphicalObjects {

    private int points; // points for catching the creature
    private int cycles; // number of cycles for the creature
    private String name; // name of the creature
    private ImageView image; // image of the creature
    private String mode = "easy"; // game mode (easy, medium, hard)
    private TranslateTransition transition; // transition for the creature
    Random random1 = new Random(); // array list of caught sea creatures


    /**
     * Constructor for the SeaCreatures class
     * @param name is the name of the creature
     * @param mode is the game mode (easy, medium, hard)
     */
    public SeaCreatures(String name, String mode) {
        // this constructor is for the fish that are caught and are for displaying the
        // score on the screen
        this.name = name;
        // set the points for the sea creature
        // so all this sets the points if mode is easy, medium, or hard
        if (name == "Large Fish") {
            if (mode == "easy") {
                points = 20;
            } else if (mode == "medium") {
                points = 30;
            } else if (mode == "hard") {
                points = 40;
            }
        } else if (name == "Large Fish 2") {
            if (mode == "easy") {
                points = 20;
            } else if (mode == "medium") {
                points = 30;
            } else if (mode == "hard") {
                points = 40;
            }

        } else if (name == "Small Fish") {
            if (mode == "easy") {
                points = 10;
            } else if (mode == "medium") {
                points = 20;
            } else if (mode == "hard") {
                points = 30;
            }
        } else if (name == "Small Fish 2") {
            if (mode == "easy") {
                points = 10;
            } else if (mode == "medium") {
                points = 20;
            } else if (mode == "hard") {
                points = 30;
            }
        } else if (name == "Small Fish 3") {
            if (mode == "easy") {
                points = 10;
            } else if (mode == "medium") {
                points = 20;
            } else if (mode == "hard") {
                points = 30;
            }
        } else if (name == "Wooden Stick") {
            if (mode == "easy") {
                points = -10;
            } else if (mode == "medium") {
                points = -20;
            } else if (mode == "hard") {
                points = -30;
            }
        } else if (name == "Octopus") {
            if (mode == "easy") {
                points = 30;
            } else if (mode == "medium") {
                points = 40;
            } else if (mode == "hard") {
                points = 50;
            }
        }
    }


    /**
     *Random constructor for the SeaCreatures class
     */
    public SeaCreatures() {
        super();
        // array of sea creatures
        String[] seaCreatures = { "Large Fish", "Large Fish 2", "Small Fish", "Small Fish 2", "Small Fish 3",
                "Wooden Stick", "Octopus" };
        // math.random to randomly select a sea creature from the array
        int random = (int) (Math.random() * seaCreatures.length);
        // so if we ever get to modes, then we'd have to set all creatures in the list,
        // once the user hits a certain amount of points, to
        // be the same as the mode they're in;
        // switch statement to set the points and name of the sea creature
        // this is all for generating the sea creatures in the array and setting their
        // points accordingly to the mode; (fish go faster each mode)
        switch (seaCreatures[random]) {
            case "Large Fish":
                if (mode == "easy") {
                    points = 20;
                } else if (mode == "medium") {
                    points = 30;
                } else if (mode == "hard") {
                    points = 40;
                }
                name = "Large Fish";
                // set the y position to be between 500 and 600 using random generator
                xPosition = 900;
                yPosition = random1.nextInt(100) + 600;
                image = new ImageView(
                        new File(this.getClass().getResource("largefish.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Large Fish 2":
                if (mode == "easy") {
                    points = 20;
                } else if (mode == "medium") {
                    points = 30;
                } else if (mode == "hard") {
                    points = 40;
                }
                name = "Large Fish 2";
                xPosition = 900;
                yPosition = random1.nextInt(100) + 600;
                image = new ImageView(
                        new File(this.getClass().getResource("largefish_2.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Small Fish":
                if (mode == "easy") {
                    points = 10;
                } else if (mode == "medium") {
                    points = 20;
                } else if (mode == "hard") {
                    points = 30;
                }
                name = "Small Fish";
                xPosition = 900;
                yPosition = random1.nextInt(100) + 400;
                image = new ImageView(
                        new File(this.getClass().getResource("smallfish.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Small Fish 2":
                if (mode == "easy") {
                    points = 10;
                } else if (mode == "medium") {
                    points = 20;
                } else if (mode == "hard") {
                    points = 30;
                }
                name = "Small Fish 2";
                xPosition = 900;
                yPosition = random1.nextInt(100) + 400;
                image = new ImageView(
                        new File(this.getClass().getResource("smallfish_2.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Small Fish 3":
                if (mode == "easy") {
                    points = 10;
                } else if (mode == "medium") {
                    points = 20;
                } else if (mode == "hard") {
                    points = 30;
                }
                name = "Small Fish 3";
                xPosition = 900;
                yPosition = random1.nextInt(100) + 400;
                image = new ImageView(
                        new File(this.getClass().getResource("smallfish_3.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Wooden Stick":
                if (mode == "easy") {
                    points = -10;
                } else if (mode == "medium") {
                    points = -20;
                } else if (mode == "hard") {
                    points = -30;
                }
                name = "Wooden Stick";
                xPosition = 900;
                yPosition = random1.nextInt(300) + 400;
                image = new ImageView(
                        new File(this.getClass().getResource("woodenstick.png").getPath()).toURI().toString());
                image.setVisible(true);
                break;
            case "Octopus":
                Glow glow = new Glow();
                glow.setLevel(10);
                if (mode == "easy") {
                    points = 30;
                } else if (mode == "medium") {
                    points = 40;
                } else if (mode == "hard") {
                    points = 50;
                }
                name = "Octopus";
                xPosition = 900;
                yPosition = random1.nextInt(100) + 700;
                image = new ImageView(
                        new File(this.getClass().getResource("octopus.png").getPath()).toURI().toString());
                image.setEffect(glow);
                image.setVisible(true);
                break;
        }

    }

    //* Getters and Setters for the program */
    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TranslateTransition getTransition() {
        return transition;
    }

    public void setTransition(TranslateTransition transition) {
        this.transition = transition;
    }

    // getters and setters for the cycle
    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    // getters and setters for mode
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    //* Basic toString method */
    @Override
    public String toString() {
        return "SeaCreatures [image=" + image + ", name=" + name + ", points=" + points + ", xPosition=" + xPosition
                + ", yPosition=" + yPosition + "]";
    }

    /** Mode was supposed to be used if we were to have modes in the game, setting distinct speeds of each creature whenever player levels up in the mode. */
    public void mode(TranslateTransition transition) {
        // if mode is easy, duration is 10 seconds
        if (mode.equals("easy")) {
            transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 10));
            // if it is an octopus, make it 15 seconds
            if (name.equals("Octopus")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 15));
                // else if wooden stick, make it 12 seconds
            } else if (name.equals("Wooden Stick")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 12));
            }
        }
        // if mode is medium, duration is 5 seconds
        else if (mode.equals("medium")) {
            // make it between 7 and 10 seconds
            transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 7));
            // if it is an octopus, make it between 13 and 15 seconds
            if (name.equals("Octopus")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(3) + 13));
            } else if (name.equals("Wooden Stick")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 12));
            }
        }
        // if mode is hard, duration is 2 seconds
        else if (mode.equals("hard")) {
            // make it between 5 and 7 seconds
            transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(3) + 5));
            // if it is an octopus, make it between 10 and 13 seconds
            if (name.equals("Octopus")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(3) + 10));
            } else if (name.equals("Wooden Stick")) {
                transition.setDuration(javafx.util.Duration.seconds(random1.nextInt(5) + 12));
            }
        }
    }

    /** The transition method (inherited by the GraphicalObjects Class) was used to transition all the creatures across the screen in randomized yet specified regions. */
    @Override
    public void transition(ImageView image) {
        cycles = 1500;
        // delay should be between 1 and 120 seconds
        int delay = random1.nextInt(120) + 1;
        // so it would be object.transition(object.transition(getImage))
        // if the sea creature is a large fish
        if (name.equals("Large Fish")) {
            TranslateTransition largeFishTransition = new TranslateTransition();
            largeFishTransition.setNode(image);
            largeFishTransition.setByX(-1000);
            mode(largeFishTransition);
            // setDelay, randomize delay between 1 and 10 seconds
            largeFishTransition.setDelay(javafx.util.Duration.seconds(delay));
            largeFishTransition.setCycleCount(cycles);
            largeFishTransition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            // set visible to true
            image.setVisible(true);
            // set transition variable to largeFishTransition
            transition = largeFishTransition;

            // if the sea creature is a large fish 2
        } else if (name.equals("Large Fish 2")) {
            TranslateTransition largeFish2Transition = new TranslateTransition();
            largeFish2Transition.setNode(image);
            mode(largeFish2Transition);
            largeFish2Transition.setByX(-1000);
            // set delay
            largeFish2Transition.setDelay(javafx.util.Duration.seconds(delay));
            largeFish2Transition.setCycleCount(cycles);
            largeFish2Transition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = largeFish2Transition;
        }
        // if the sea creature is a small fish
        else if (name.equals("Small Fish")) {
            TranslateTransition smallFishTransition = new TranslateTransition();
            smallFishTransition.setNode(image);
            mode(smallFishTransition);
            smallFishTransition.setByX(-1000);
            // set delay
            smallFishTransition.setDelay(javafx.util.Duration.seconds(delay));
            smallFishTransition.setCycleCount(cycles);
            smallFishTransition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = smallFishTransition;
        }
        // if the sea creature is a small fish 2
        else if (name.equals("Small Fish 2")) {
            TranslateTransition smallFish2Transition = new TranslateTransition();
            smallFish2Transition.setNode(image);
            mode(smallFish2Transition);
            smallFish2Transition.setByX(-1000);
            // set delay
            smallFish2Transition.setDelay(javafx.util.Duration.seconds(delay));
            smallFish2Transition.setCycleCount(cycles);
            smallFish2Transition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = smallFish2Transition;
        }
        // if the sea creature is a small fish 3
        else if (name.equals("Small Fish 3")) {
            TranslateTransition smallFish3Transition = new TranslateTransition();
            smallFish3Transition.setNode(image);
            mode(smallFish3Transition);
            smallFish3Transition.setByX(-1000);
            // set delay
            smallFish3Transition.setDelay(javafx.util.Duration.seconds(delay));
            smallFish3Transition.setCycleCount(cycles);
            smallFish3Transition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = smallFish3Transition;
        }
        // if the sea creature is a wooden stick
        else if (name.equals("Wooden Stick")) {
            TranslateTransition woodenStickTransition = new TranslateTransition();
            woodenStickTransition.setNode(image);
            mode(woodenStickTransition);
            woodenStickTransition.setByX(-1000);
            // set delay
            woodenStickTransition.setDelay(javafx.util.Duration.seconds(delay));
            woodenStickTransition.setCycleCount(cycles);
            woodenStickTransition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = woodenStickTransition;
            RotateTransition rotate = new RotateTransition();
            rotate.setNode(image);
            rotate.setDuration(javafx.util.Duration.seconds(15));
            rotate.setByAngle(360);
            rotate.setCycleCount(Animation.INDEFINITE);
            rotate.play();
        }
        // if the sea creature is an octopus
        else if (name.equals("Octopus")) {
            TranslateTransition octopusTransition = new TranslateTransition();
            octopusTransition.setNode(image);
            mode(octopusTransition);
            octopusTransition.setByX(-1200);
            // set delay
            octopusTransition.setDelay(javafx.util.Duration.seconds(delay));
            octopusTransition.setCycleCount(cycles);
            octopusTransition.play();
            image.setY(yPosition);
            image.setX(xPosition);
            image.setVisible(true);
            transition = octopusTransition;
        }

    }

    /** This method pullImage is used to pull the image of the creature last caught and display it in the HelpPanel */

    public ImageView pullImage() {
        if (name.equals("Large Fish")) {
            image = new ImageView(
                    new File(this.getClass().getResource("largefish.png").getPath()).toURI().toString());
        } else if (name.equals("Large Fish 2")) {
            image = new ImageView(
                    new File(this.getClass().getResource("largefish_2.png").getPath()).toURI().toString());
        } else if (name.equals("Small Fish")) {
            image = new ImageView(
                    new File(this.getClass().getResource("smallfish.png").getPath()).toURI().toString());
        } else if (name.equals("Small Fish 2")) {
            image = new ImageView(
                    new File(this.getClass().getResource("smallfish_2.png").getPath()).toURI().toString());
        } else if (name.equals("Small Fish 3")) {
            image = new ImageView(
                    new File(this.getClass().getResource("smallfish_3.png").getPath()).toURI().toString());
        } else if (name.equals("Wooden Stick")) {
            image = new ImageView(
                    new File(this.getClass().getResource("woodenstick.png").getPath()).toURI().toString());
        } else if (name.equals("Octopus")) {
            image = new ImageView(
                    new File(this.getClass().getResource("octopus.png").getPath()).toURI().toString());
        }
        return image;
    }
}
