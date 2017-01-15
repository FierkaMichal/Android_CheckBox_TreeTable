package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */
import java.util.Date;

public class Car {

    private String name;
    @ToDisplay
    private Date productionDate;
    private boolean electric;
    @ToDisplay
    private double maxSpeed;

    public Car(String name, Date productionDate, boolean electric, double maxSpeed) {
        this.name = name;
        this.productionDate = productionDate;
        this.electric = electric;
        this.maxSpeed = maxSpeed;
    }

    public String toString() {
        String str = "name = " + name + "data = " + productionDate + "electric = " + electric + "maxSpeed" + maxSpeed;
        return str;
    }
}
