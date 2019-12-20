package me.schooltests.cryobot.services;

import me.schooltests.cryobot.internal.ICommand;
import me.schooltests.cryobot.internal.BaseModule;
import me.schooltests.cryobot.internal.ReflectionRegistry;
import me.schooltests.cryobot.modules.core.CoreModule;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

public class RegistryService {

    private JDA JDA;
    private List<BaseModule> modules = new ArrayList<>();
    private List<ICommand> commands = new ArrayList<>();

    public RegistryService(JDA JDA) {
        this.JDA = JDA;

        // Force register Core
        registerModule(new CoreModule(this));

        // Register other modules
        ReflectionRegistry reflectionRegistry = new ReflectionRegistry(this);
        reflectionRegistry.beginQuery();
    }

    public JDA getJDA() {
        return JDA;
    }

    public void registerModule(BaseModule module) {
        modules.add(module);
        module.onInit();
    }

    public <T extends BaseModule> T getModuleByClass(Class<T> clazz) {
        for(BaseModule module : modules) {
            if (clazz.isInstance(module)) {
                return clazz.cast(module);
            }
        }

        return null;
    }

    public void registerCommand(ICommand command) {
        commands.add(command);
    }

    public void registerEventHandler(Object handler) {
        getJDA().addEventListener(handler);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

}
