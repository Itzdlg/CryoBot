package me.schooltests.cryobot;

import me.schooltests.cryobot.services.DataService;
import me.schooltests.cryobot.services.RegistryService;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;


public class CryoBot {
    private RegistryService registryService;
    private JDA jda;

    public CryoBot() throws LoginException, InterruptedException {
        jda = new JDABuilder(AccountType.BOT).setToken(DataService.token).build().awaitReady();
        registryService = new RegistryService(jda);
    }
}
