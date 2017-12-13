package ru.klimakov.nurse;

public class Nurse {


    private Register register = new Register();

    public Nurse register(Object something) {
        register.add(something);
        return this;
    }

    public Register build() {
        register.inject();
        return register;
    }
}
