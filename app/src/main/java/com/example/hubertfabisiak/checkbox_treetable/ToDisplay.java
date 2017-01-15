package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Michał on 2017-01-15.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ToDisplay {
}

//    Field[] fields = Car.class.getDeclaredFields();
//
//for (Field field : fields) {
//        if (field.isAnnotationPresent(ToDisplay.class)) {
//        field.setAccessible(true);
//        try {
//        names.add(field.getName());
//        } catch (IllegalArgumentException e) {
//        e.printStackTrace();
//        }
//        }
//        }
//        for(String str : names) {
//        Log.i(TAG, str);
//        }


