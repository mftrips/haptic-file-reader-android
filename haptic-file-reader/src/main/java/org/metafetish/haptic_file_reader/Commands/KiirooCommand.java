package org.metafetish.haptic_file_reader.Commands;

import org.metafetish.haptic_file_reader.HapticDevice;

public class KiirooCommand extends HapticCommand {
    private int position;

    public KiirooCommand(int time, int position) {
        super(time);
        this.position = position;
    }

    public KiirooCommand(int time, int position, HapticDevice device) {
        super(time, device);
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
