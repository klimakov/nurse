package ru.klimakov.nurse;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Register {

    private Map<String, Object> register = new HashMap<>();
    private Map<Field, Object> injectionsPlaces = new HashMap<>();

    public Optional<Object> get(String name) {
        Object something = register.get(name);
        return Optional.ofNullable(something);
    }

    void add(String name, Object something) {
        if (register.containsKey(name)) {
            throw new RuntimeException();
        }
        Field[] fields = something.getClass().getDeclaredFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                injectionsPlaces.put(field, something);
            }
        }
        register.put(name, something);
    }

    void add(Object component) {
        add(component.getClass().getName(), component);
    }

    public <T> Optional<T> get(Class<T> type) {
        return (Optional<T>) get(type.getName());
    }

    void inject() {
        for (Field field: injectionsPlaces.keySet()) {
            String injectionName = field.getType().getName();
            Object injection = get(injectionName).get();
            Object patient = injectionsPlaces.get(field);
            field.setAccessible(true);
            try {
                field.set(patient, injection);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
