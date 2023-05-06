package edu.guilford;

public class SeaCreatures {
    
    private int points; //points for catching the creature
    private String name; //name of the creature

    public SeaCreatures(int points, String name) {
        this.points = points;
        this.name = name;
    }

    //constructor, where sea creature is either Big Fish, Small Fish, Wooden Stick, or Octopus 
    //if sea creature is Big Fish, then points = 20 and name = "Big Fish". If sea creature is Small Fish, then points = 10 and name = "Small Fish."
    //If sea creature is Wooden Stick, then points = -5 and name = "Wooden Stick." If sea creature is Octopus, then points = -20 and name = "Octopus."
    //constructor that takes in the points and name of the sea creature
    public SeaCreatures() {
        //array of sea creatures 
        String[] seaCreatures = {"Big Fish", "Small Fish", "Wooden Stick", "Octopus"};
        //math.random to randomly select a sea creature from the array
        int random = (int) (Math.random() * seaCreatures.length);
        //switch statement to set the points and name of the sea creature
        switch (seaCreatures[random]) {
            case "Big Fish":
                points = 20;
                name = "Big Fish";
                break;
            case "Small Fish":
                points = 10;
                name = "Small Fish";
                break;
            case "Wooden Stick":
                points = -5;
                name = "Wooden Stick";
                break;
            case "Octopus":
                points = -20;
                name = "Octopus";
                break;
        }
    }


    //method that calculate the frequency of the sea creature



}
