package org.metafetish.haptic_file_reader.Commands;

public class FunscriptCommand extends HapticCommand {
    private int position;

    public FunscriptCommand(int time, int position) {
        super(time);
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
