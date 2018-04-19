package org.metafetish.haptic_file_reader.Commands;

public class LovenseMaxCommand extends HapticCommand {
    private int inflation;

    public LovenseMaxCommand(int time, int inflation) {
        super(time);
        this.inflation = inflation;
    }

    public int getInflation() {
        return this.inflation;
    }
}
