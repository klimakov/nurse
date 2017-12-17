package ru.klimakov.nurse;


import com.google.common.reflect.ClassPath;

import java.io.IOException;

public class Nurse {


    private Register register = new Register();

    public Nurse register(Class typeOfSomething) {
        register.add(typeOfSomething);
        return this;
    }

    public Nurse register(Object something) {
        register.add(something);
        return this;
    }

    public Nurse scan(String packageName) {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            ClassPath.from(loader).getTopLevelClasses()
                    .stream()
                    .filter(info -> info.getName().startsWith(packageName))
                    .map(ClassPath.ClassInfo::load)
                    .filter(clazz -> clazz.isAnnotationPresent(Cure.class))
                    .forEach(info -> register.add(info));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public Register build() {
        register.inject();
        return register;
    }

}
