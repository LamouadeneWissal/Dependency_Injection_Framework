package org.example.core;


import org.example.annotations.Component;
import org.example.annotations.Inject;
import java.lang.reflect.*;
import java.util.*;

public class ApplicationContext {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?>... classes) throws Exception {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Object instance = createInstance(clazz);
                instances.put(clazz, instance);
            }
        }
    }

    private Object createInstance(Class<?> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                Object[] params = Arrays.stream(constructor.getParameterTypes())
                        .map(instances::get)
                        .toArray();
                return constructor.newInstance(params);
            }
        }
        Object instance = clazz.getDeclaredConstructor().newInstance();
        injectFields(instance);
        injectSetters(instance);
        return instance;
    }

    private void injectFields(Object instance) throws Exception {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(instance, instances.get(field.getType()));
            }
        }
    }

    private void injectSetters(Object instance) throws Exception {
        for (Method method : instance.getClass().getMethods()) {
            if (method.isAnnotationPresent(Inject.class) && method.getParameterCount() == 1) {
                method.invoke(instance, instances.get(method.getParameterTypes()[0]));
            }
        }
    }

    public <T> T getInstance(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }
}
