package me.schooltests.cryobot.internal;

import me.schooltests.cryobot.services.RegistryService;

public class BaseModule {

    private RegistryService registryService;
    public BaseModule(RegistryService registryService) {
        this.registryService = registryService;
    }

    public RegistryService getRegistryService() { return registryService; }

    public void onInit() { }

}
