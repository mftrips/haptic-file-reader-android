package org.metafetish.haptic_file_reader.Commands;

import org.metafetish.haptic_file_reader.HapticDevice;

public abstract class HapticCommand {
    private int time;
    private HapticDevice device;

    public HapticCommand(int time) {
        this.time = time;
        this.device = HapticDevice.ANY;
    }

    public HapticCommand(int time, HapticDevice device) {
        this.time = time;
        this.device = device;
    }

    public int getTime() {
        return this.time;
    }

    public HapticDevice getDevice() {
        return this.device;
    }
}
