package ru.klimakov.nurse;


import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

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

    public Nurse registerInfirmary(Infirmary infirmary) {
        List<Inmate> inmates = infirmary.getInmates();
        for (Inmate inmate: inmates) {
            try {
                Class type = Class.forName(inmate.getName());
                Object instance = type.newInstance();
                register.add(inmate.getName(), instance);
                for (Injection injection: inmate.getInjections()) {
                    Field field = type.getDeclaredField(injection.getName());
                    register.addInjectionPlace(field, instance);
                }
            } catch (ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

        }
        return this;
    }
}
