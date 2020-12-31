package com.github.dynosaur.variation;

import java.io.Serializable;
import java.util.ArrayList;

public class Variation implements Serializable {
    public double startingFunds = 1500d;
    private double transportOwnedMultiplier = 2d;
    private double oneUtilMultiplier = 4d;
    private double twoUtilMultiplier = 10d;
    private double mortgageRate = 0.5d;
    private double firstSideBuildingCost = 50;
    private double secondSideBuildingCost = 100;
    private double thirdSideBuildingCost = 150;
    private double fourthSideBuildingCost = 200;
    private ArrayList<Property> properties = new ArrayList<Property>(27);

    public Variation() { }

    public Variation(double startingFunds) {
        this.startingFunds = startingFunds;
        properties.add(new StandardProperty("purple-1"));
        properties.add(new StandardProperty("purple-2"));
        properties.add(new Property("transport-1"));
        properties.add(new StandardProperty("light-blue-1"));
        properties.add(new StandardProperty("light-blue-2"));
        properties.add(new StandardProperty("light-blue-3"));
        properties.add(new StandardProperty("magneta-1"));
        properties.add(new Property("utility-1"));
        properties.add(new StandardProperty("magneta-2"));
        properties.add(new StandardProperty("magneta-3"));
        properties.add(new Property("transport-2"));
        properties.add(new StandardProperty("orange-1"));
        properties.add(new StandardProperty("orange-2"));
        properties.add(new StandardProperty("orange-3"));
        properties.add(new StandardProperty("red-1"));
        properties.add(new StandardProperty("red-2"));
        properties.add(new StandardProperty("red-3"));
        properties.add(new Property("transport-3"));
        properties.add(new StandardProperty("yellow-1"));
        properties.add(new StandardProperty("yellow-2"));
        properties.add(new Property("utility-2"));
        properties.add(new StandardProperty("yellow-3"));
        properties.add(new StandardProperty("green-1"));
        properties.add(new StandardProperty("green-2"));
        properties.add(new StandardProperty("green-3"));
        properties.add(new Property("transport-4"));
        properties.add(new StandardProperty("dark-blue-1"));
        properties.add(new StandardProperty("dark-blue-2"));
    }


}
