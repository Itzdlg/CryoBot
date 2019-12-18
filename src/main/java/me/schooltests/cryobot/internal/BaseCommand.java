package me.schooltests.cryobot.internal;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class BaseCommand {
    private BaseModule module;

    /*
    private String description;
    private List<CommandArgument> arguments = new ArrayList<>();
    private List<Permission> permissions = new ArrayList<>();
    private List<String> aliases = new ArrayList<>();
     */

    public BaseCommand(BaseModule module) {
        this.module = module;
    }

    public BaseModule getModule() {
        return module;
    }


    public String getIdentifier() { return ""; }

    public List<CommandArgument> getArguments() { return new ArrayList<>(); }

    public List<Permission> getPermissions() { return new ArrayList<>(); }

    public String getDescription() { return ""; }

    public List<String> getAliases() { return new ArrayList<>(); }

    public void execute(GuildMessageReceivedEvent event, String[] args) { }
}
