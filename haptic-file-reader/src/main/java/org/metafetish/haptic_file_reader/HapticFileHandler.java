package org.metafetish.haptic_file_reader;

import org.apache.commons.io.FileUtils;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Handlers.FeelMeHandler;
import org.metafetish.haptic_file_reader.Handlers.FeelVRHandler;
import org.metafetish.haptic_file_reader.Handlers.FunscriptHandler;
import org.metafetish.haptic_file_reader.Handlers.KiirooHandler;
import org.metafetish.haptic_file_reader.Handlers.VirtualRealPornHandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class HapticFileHandler {
    private static List<HapticFileHandler> handlers = new ArrayList<HapticFileHandler>(){{
        add(new FeelMeHandler());
        add(new FeelVRHandler());
        add(new FunscriptHandler());
        add(new KiirooHandler());
        add(new VirtualRealPornHandler());
    }};

    @SuppressWarnings("unused")
    public static HapticFileHandler handleFile(File file) {
        try {
            return HapticFileHandler.handleString(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            //TODO: Handle exceptions
            e.printStackTrace();
            return null;
        }
    }

    public static HapticFileHandler handleString(String string) {
        List<HapticFileHandler> handlers = new ArrayList<>();
        for (HapticFileHandler handler : HapticFileHandler.handlers) {
            try {
                // make fresh objects to avoid false positives from previous runs
                handler = handler.getClass().newInstance();
                try {
                    handler.loadString(string);
                    if (!handler.commands.isEmpty()) {
                        handlers.add(handler);
                    }
                } catch (IllegalArgumentException e) {
                    // just ignore if there's an error.
                    // e.printStackTrace();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (handlers.size() == 1) {
            return handlers.get(0);
        } else {
            //TODO: Return a better error when multiple parsers available somehow.
            return null;
        }
    }

    protected List<HapticCommand> commands = new ArrayList<>();
    private int lastIndexRetrieved = 0;
    private int lastTimeRetrieved = 0;

    @SuppressWarnings("unused")
    public int getCommandLength() {
        return this.commands.size();
    }

    @SuppressWarnings("unused")
    public List<HapticCommand> getCommands() {
        return this.commands;
    }

    @SuppressWarnings("unused")
    public HapticCommand getValueNearestTime(int time) {
        // We figure we'll normally be handing out indexes sequentially, while a
        // movie is playing. So always start from our last record returned.
        int startIndex = this.lastIndexRetrieved;
        if (time < this.lastTimeRetrieved) {
            startIndex = 0;
        }
        int i = startIndex;
        for (; i < this.commands.size(); ++i) {
            if (this.commands.get(i).getTime() > time) {
                break;
            }
        }
        // Lots of videos have long leadins with no haptics. Return null if we
        // don't have anything to send yet.
        if (i == 0) {
            return null;
        }
        this.lastIndexRetrieved = i - 1;
        this.lastTimeRetrieved = this.commands.get(i - 1).getTime();
        return this.commands.get(i - 1);
    }

    public abstract void loadString(String body);
}
