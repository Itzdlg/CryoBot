package me.schooltests.cryobot.internal;

import me.schooltests.cryobot.services.RegistryService;
import net.dv8tion.jda.api.JDA;

public class BaseModule {

    private RegistryService registryService;
    public BaseModule(RegistryService registryService) {
        this.registryService = registryService;
    }

    public JDA getJDA() {
        return registryService.getJDA();
    }

    public RegistryService getRegistryService() { return registryService; }

    public void onInit() { }

}
