package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Damian on 15.01.2017.
 */

import java.util.ArrayList;

public class Settings {
    public static ArrayList<String> variablesToDisplay; // zawiera nazwy argumentow do wyswietlenia w tabeli
    public static ArrayList<String> invalidVariablesToDisplay; // wprowadzone przez uzytkowanika

    public Settings() {
        variablesToDisplay = new ArrayList<>();
        invalidVariablesToDisplay = new ArrayList<>();
        variablesToDisplay.add("mark");
        variablesToDisplay.add("size");
    }

    public static void addVariableToDisplay(String variable) {
        variablesToDisplay.add(variable);
    }

    public static void addInvalidVariablesToDisplay(String variable) {
        invalidVariablesToDisplay.add(variable);
    }

    public static void clearVariablesToDisplay() {
        for (String invalidVariable : invalidVariablesToDisplay) {
            variablesToDisplay.remove(invalidVariable);
        }
    }

    public static String getVariableToDisplay(int idx) {
        return variablesToDisplay.get(idx);
    }

    public static int getVariablesToDisplaySize() {
        return variablesToDisplay.size();
    }
}
