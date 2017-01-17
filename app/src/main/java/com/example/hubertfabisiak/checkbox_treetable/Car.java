package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */
public class Car {

    @ToDisplay(index = 2)
    private int size;
    @ToDisplay(index = 1)
    private String mark;
    @ToDisplay(index = 0)
    private double maxSpeed;

    public Car(int size, String mark, double maxSpeed) {
        super();
        this.size = size;
        this.mark = mark;
        this.maxSpeed = maxSpeed;
    }

}
