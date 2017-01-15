package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */
public class Car {

    //@ToDisplay
    private int size;
    //@ToDisplay
    private String mark;
    // @ToDisplay
    private double maxSpeed;

    public Car(int size, String mark, double maxSpeed) {
        super();
        this.size = size;
        this.mark = mark;
        this.maxSpeed = maxSpeed;
    }

//    public String toString() {
//        String str = " name = " + name + "  electric = " + electric + "  maxSpeed " + maxSpeed;
//        return str;
//    }
}
