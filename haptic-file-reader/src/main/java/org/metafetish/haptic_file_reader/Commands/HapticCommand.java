package org.metafetish.haptic_file_reader.Commands;

public abstract class HapticCommand {
    private int time;

    public HapticCommand(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }
}
