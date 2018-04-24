package org.metafetish.haptic_file_reader.Handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.HapticDevice;
import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KiirooHandler extends HapticFileHandler {
    public static List<HapticCommand> parseCommands(String commands) {
        return KiirooHandler.parseCommands(commands, HapticDevice.ANY, ";", ",");
    }

    public static List<HapticCommand> parseCommands(String commands, HapticDevice device) {
        return KiirooHandler.parseCommands(commands, device, ";", ",");
    }

    @SuppressWarnings("unused")
    public static List<HapticCommand> parseCommands(String commands, String separator, String keyValueSeparator) {
        return KiirooHandler.parseCommands(commands, HapticDevice.ANY, separator, keyValueSeparator);
    }

    public static List<HapticCommand> parseCommands(String commands, HapticDevice device, String separator, String keyValueSeparator) {
        if (commands == null || commands.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Kiiroo file");
        }
        commands = commands.trim();

        List<HapticCommand> retList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            LinkedHashMap map = (LinkedHashMap) mapper.readValue(commands, Object.class);
            for (Object timePos : map.entrySet()) {
                String timeString = (String) ((Map.Entry) timePos).getKey();
                int time = (int) Math.floor(Double.parseDouble(timeString) * 1000);
                int pos = (int) ((Map.Entry) timePos).getValue();
                retList.add(new KiirooCommand(time, pos, device));
            }
        } catch (IOException e) {
            // First off, let's make sure this is stringified javascript object
            if (commands.indexOf("{") != 0 || commands.indexOf("}") != commands.length() - 1) {
                throw new IllegalArgumentException("Invalid Kiiroo file");
            }
            // Strip off { and }
            commands = commands.substring(1, commands.length() - 1);

            for (String command : commands.split(separator)) {
                if (command.isEmpty()) {
                    continue;
                }
                String[] timePos = command.split(keyValueSeparator);
                int time = (int) Math.floor(Double.parseDouble(timePos[0]) * 1000);
                int pos = Integer.parseInt(timePos[1], 10);
                retList.add(new KiirooCommand(time, pos, device));
            }
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
