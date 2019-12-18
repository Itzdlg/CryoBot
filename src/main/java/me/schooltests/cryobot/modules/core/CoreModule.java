package me.schooltests.cryobot.modules.core;

import me.schooltests.cryobot.modules.core.listeners.CommandListener;
import me.schooltests.cryobot.internal.BaseModule;
import me.schooltests.cryobot.modules.core.util.CommandUtil;
import me.schooltests.cryobot.services.RegistryService;

public class CoreModule extends BaseModule {

    private CommandUtil commandUtil = new CommandUtil();

    public CoreModule(RegistryService registryService) {
        super(registryService);
    }

    public CommandUtil getCommandUtil() {
        return commandUtil;
    }

    @Override
    public void onInit() {
        getRegistryService().registerEventHandler(new CommandListener(this));
    }
}
