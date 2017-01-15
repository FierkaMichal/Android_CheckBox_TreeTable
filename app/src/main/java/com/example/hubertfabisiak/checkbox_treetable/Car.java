package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */
public class Car {

    //@ToDisplay
    private String name;
    private boolean electric;
    @ToDisplay
    private double maxSpeed;

    public Car(String name, boolean electric, double maxSpeed) {
        this.name = name;
        this.electric = electric;
        this.maxSpeed = maxSpeed;
    }

    public String toString() {
        String str = " name = " + name + "  electric = " + electric + "  maxSpeed " + maxSpeed;
        return str;
    }
}
