package org.metafetish.haptic_file_reader.Handlers;

import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.HapticDevice;
import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.util.ArrayList;
import java.util.List;

public class KiirooHandler extends HapticFileHandler {
    public static List<HapticCommand> parseCommands(String commands) {
        return KiirooHandler.parseCommands(commands, HapticDevice.ANY, ";", ",");
    }

    public static List<HapticCommand> parseCommands(String commands, HapticDevice device) {
        return KiirooHandler.parseCommands(commands, device, ";", ",");
    }

    public static List<HapticCommand> parseCommands(String commands, String separator, String keyValueSeparator) {
        return KiirooHandler.parseCommands(commands, HapticDevice.ANY, separator, keyValueSeparator);
    }

    public static List<HapticCommand> parseCommands(String commands, HapticDevice device, String separator, String keyValueSeparator) {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Invalid Kiiroo file");
        }
        commands = commands.trim();

        // First off, let's make sure this is stringified javascript object
        if (commands.indexOf("{") != 0 || commands.indexOf("}") != commands.length() - 1) {
            throw new IllegalArgumentException("Invalid Kiiroo file");
        }
        // Strip off { and }
        commands = commands.substring(1, commands.length() - 1);

        List<HapticCommand> retList = new ArrayList<>();
        for (String command : commands.split(separator)) {
            if (command.isEmpty()) {
                continue;
            }
            String[] time_pos = command.split(keyValueSeparator);
            int time = (int) Math.floor(Double.parseDouble(time_pos[0]) * 1000);
            int pos = Integer.parseInt(time_pos[1], 10);
            retList.add(new KiirooCommand(time, pos, device));
        }
        return retList;
    }

    @Override
    public void loadString(String body) {
        if (body.indexOf("var kiiroo_subtitles") != 0) {
            throw new IllegalArgumentException("Wrong format");
        }
        String commands = body.substring(body.indexOf("{"), body.indexOf("}") + 1);
        this.commands = KiirooHandler.parseCommands(commands);
    }
}
