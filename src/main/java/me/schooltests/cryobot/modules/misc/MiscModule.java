package me.schooltests.cryobot.modules.misc;

import me.schooltests.cryobot.modules.misc.commands.SayCommand;
import me.schooltests.cryobot.internal.BaseModule;
import me.schooltests.cryobot.services.RegistryService;

public class MiscModule extends BaseModule {

    public MiscModule(RegistryService registryService) {
        super(registryService);
    }

    @Override
    public void onInit() {
        getRegistryService().registerCommand(new SayCommand(this));
    }

}
