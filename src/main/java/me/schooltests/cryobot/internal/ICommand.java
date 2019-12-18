package me.schooltests.cryobot.internal;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public interface ICommand {

    String getIdentifier();

    List<CommandArgument> getArguments();

    List<Permission> getPermissions();

    String getDescription();

    List<String> getAliases();

    void execute(GuildMessageReceivedEvent event, String[] args);

}
