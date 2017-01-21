package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */
public class Car {

    //@ToDisplay(index = 2)
    private int size;
    //@ToDisplay(index = 0)
    private String id;
    private String mark;
    //@ToDisplay(index = 1)
    private double maxSpeed;

    public Car(){

    }

    public Car(int size, String id, String mark, double maxSpeed) {
        super();
        this.size = size;
        this.mark = mark;
        this.id = id;
        this.maxSpeed = maxSpeed;
    }

    public void wypisz(){
        System.out.println("Size: " + this.size);
        System.out.println("Id: " + this.id);
        System.out.println("Mark: " + this.mark);
        System.out.println("MaxSpeed: " + this.maxSpeed);
    }

}
