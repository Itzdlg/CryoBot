package me.schooltests.cryobot.modules.core.util;

import me.schooltests.cryobot.internal.BaseCommand;
import me.schooltests.cryobot.internal.CommandArgument;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CommandUtil {
    public String getCommandPrefix() {
        return "-";
    }

    public String[] removeElementFromArray(String[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        String[] anotherArray = new String[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }


            anotherArray[k++] = arr[i];
        }

        return anotherArray;
    }

    public boolean validArgs(BaseCommand command, String[] args) {
        if(command.getArguments() == null || command.getArguments().isEmpty()) return true;

        List<CommandArgument> requiredArgs = command.getArguments().stream().filter(arg -> !arg.isOptional()).collect(Collectors.toList());

        if(args.length < requiredArgs.size()) return false;

        for(int i = 0; i < requiredArgs.size(); i++) {
            if(!command.getArguments().get(i).validArgument(args[i])) return false;
        }

        return true;
    }

    public void handleCommandEvent(GuildMessageReceivedEvent event, BaseCommand command, String[] args) {
        boolean hasPermission = false;
        for(Permission perm : command.getPermissions()) {
            if (event.getMember().hasPermission(perm)) hasPermission = true;
        }

        if(hasPermission && validArgs(command, args)) {
            command.execute(event, args);
        } else {
            StringBuilder builder = new StringBuilder();
            for(CommandArgument argument : command.getArguments()) {
                builder.append(" ");
                if(argument.isOptional()) { builder.append("["); } else { builder.append("<"); }
                builder.append(argument.getName().toLowerCase());
                if(argument.isOptional()) { builder.append("]"); } else { builder.append(">"); }
            }
            event.getChannel().sendMessage("Please ensure all of the command arguments are correct and that you have permission to use this command! (" + command.getIdentifier() + builder.toString() + ")").queue();
        }
    }


}
