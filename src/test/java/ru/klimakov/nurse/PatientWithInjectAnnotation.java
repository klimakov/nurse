package ru.klimakov.nurse;

public class PatientWithInjectAnnotation {

    @Inject
    private Glucose glucose;

    public Glucose getGlucose() {
        return glucose;
    }

}
