package me.schooltests.cryobot.modules.core.listeners;

import me.schooltests.cryobot.internal.BaseCommand;
import me.schooltests.cryobot.modules.core.CoreModule;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandListener extends ListenerAdapter {
    private CoreModule module;
    public CommandListener(CoreModule module) {
        this.module = module;
    }

    public CoreModule getModule() {
        return module;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(getModule().getRegistryService().getCommandPrefix())) {
                String[] args = event.getMessage().getContentRaw().split(" ");
                String commandPart = args[0].replaceFirst(getModule().getRegistryService().getCommandPrefix(), "");
                for (BaseCommand command : getModule().getRegistryService().getCommands()) {
                    if (command.getIdentifier().equalsIgnoreCase(commandPart) || command.getAliases().contains(commandPart.toLowerCase())) {
                        getModule().getCommandUtil().handleCommandEvent(event, command, getModule().getCommandUtil().removeElementFromArray(args, 0));
                    }
                }
            }
        }
    }
}
