package me.schooltests.cryobot.modules.core.listeners;

import me.schooltests.cryobot.internal.ICommand;
import me.schooltests.cryobot.modules.core.CoreModule;
import me.schooltests.cryobot.modules.core.util.CommandUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandListener extends ListenerAdapter {

    private CoreModule module;
    private CommandUtil commandUtil;

    public CommandListener(CoreModule module) {
        this.module = module;
        this.commandUtil = module.getCommandUtil();
    }

    public CoreModule getModule() {
        return module;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String rawContent = event.getMessage().getContentRaw();
        if (rawContent.startsWith(commandUtil.getCommandPrefix())) {
            String[] args = rawContent.split(" ");
            String commandPart = args[0].replaceFirst(commandUtil.getCommandPrefix(), "");
            for (ICommand command : getModule().getRegistryService().getCommands()) {
                if (command.getIdentifier().equalsIgnoreCase(commandPart) || command.getAliases().contains(commandPart.toLowerCase())) {
                    commandUtil.handleCommandEvent(event, command, commandUtil.removeElementFromArray(args, 0));
                }
            }
        }
    }

}
