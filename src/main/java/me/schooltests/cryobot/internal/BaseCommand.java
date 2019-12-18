package me.schooltests.cryobot.internal;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public abstract class BaseCommand {
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


    public abstract String getIdentifier();

    public abstract List<CommandArgument> getArguments();

    public abstract List<Permission> getPermissions();

    public abstract String getDescription();

    public abstract List<String> getAliases();

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}
