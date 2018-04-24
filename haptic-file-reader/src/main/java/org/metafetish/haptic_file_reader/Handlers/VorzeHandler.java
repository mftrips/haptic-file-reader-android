package org.metafetish.haptic_file_reader.Handlers;

import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.VorzeCommand;
import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.util.ArrayList;
import java.util.List;

public class VorzeHandler extends HapticFileHandler {
    @Override
    public void loadString(String body) {
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Vorze file");
        }
        List<HapticCommand> retList = new ArrayList<>();
        for (String line : body.trim().split("\r?\n")) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.trim().split(",");
            int time = Integer.parseInt(parts[0], 10) * 100;
            int direction = Integer.parseInt(parts[1], 10);
            int speed = Integer.parseInt(parts[2], 10);
            retList.add(new VorzeCommand(time, direction, speed));
        }
        this.commands = retList;
    }
}
