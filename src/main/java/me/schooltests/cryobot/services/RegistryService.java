package me.schooltests.cryobot.services;

import me.schooltests.cryobot.internal.BaseCommand;
import me.schooltests.cryobot.internal.BaseModule;
import me.schooltests.cryobot.modules.core.CoreModule;
import me.schooltests.cryobot.modules.misc.MiscModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RegistryService extends ListenerAdapter {
    private JDA JDA;
    private List<BaseModule> modules = new ArrayList<>();
    private List<BaseCommand> commands = new ArrayList<>();

    public RegistryService(JDA JDA) {
        this.JDA = JDA;

        // Register Modules
        registerModule(new CoreModule(this)); // MUST BE FIRST

        registerModule(new MiscModule(this));
    }

    public JDA getJDA() {
        return JDA;
    }

    public void registerModule(BaseModule module) {
        modules.add(module);
        module.onInit();
    }

    public void registerCommand(BaseCommand command) {
        commands.add(command);
    }

    public void registerEventHandler(Object handler) {
        getJDA().addEventListener(handler);
    }

    public List<BaseCommand> getCommands() {
        return commands;
    }
}
