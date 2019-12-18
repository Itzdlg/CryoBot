package me.schooltests.cryobot.modules.misc.commands;

import me.schooltests.cryobot.internal.BaseCommand;
import me.schooltests.cryobot.internal.CommandArgument;
import me.schooltests.cryobot.modules.misc.MiscModule;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class SayCommand extends BaseCommand {

    private MiscModule module;
    private final String identifier = "say";
    private final String description = "Make the bot send a message";
    private final List<Permission> permissions = Arrays.asList(Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER);
    private final List<CommandArgument> arguments = Arrays.asList(new CommandArgument("Message", "The message the bot should send", 1, 1500));
    private final List<String> aliases = Arrays.asList("speak");

    public SayCommand(MiscModule module) {
        super(module);
        this.module = module;
    }

    @Override
    public MiscModule getModule() {
        return module;
    }

    public String joinStrings(String[] strings, String joinCharacter) {
        StringBuilder builder = new StringBuilder();
        for(String string : strings) {
            builder.append(joinCharacter + string);
        }

        return builder.toString();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Permission> getPermissions() { return permissions; }

    @Override
    public List<CommandArgument> getArguments() { return arguments; }

    @Override
    public List<String> getAliases() { return aliases; }

    // say <message>
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String message = joinStrings(args, " ");
        event.getChannel().sendMessage(message).queue();
    }
}
