package me.schooltests.cryobot.internal;

import me.schooltests.cryobot.internal.annotations.ModuleRegistry;
import me.schooltests.cryobot.services.RegistryService;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ReflectionRegistry {
    private RegistryService registryService;
    public ReflectionRegistry(RegistryService registryService) {
        this.registryService = registryService;
    }

    public void beginQuery() {
        try {

            Reflections reflections = new Reflections("me.schooltests.cryobot.modules");
            Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(ModuleRegistry.class);
            for (Class<?> clazz : annotatedClasses) {
                Constructor constructor = clazz.getConstructor(RegistryService.class);
                registryService.registerModule((BaseModule) clazz.cast(constructor.newInstance(registryService)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
