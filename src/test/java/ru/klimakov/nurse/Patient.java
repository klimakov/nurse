package ru.klimakov.nurse;

public class Patient {

    @Inject
    private Glucose glucose;

    public Glucose getGlucose() {
        return glucose;
    }

}
