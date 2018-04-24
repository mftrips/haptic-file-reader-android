package org.metafetish.haptic_file_reader.Handlers;

import org.metafetish.haptic_file_reader.Commands.BeatMeterCommand;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.util.ArrayList;
import java.util.List;

public class BeatMeterHandler extends HapticFileHandler {
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
            int time = (int) Math.floor(Double.parseDouble(line) * 1000);
            retList.add(new BeatMeterCommand(time));
        }
        this.commands = retList;
   }
}
