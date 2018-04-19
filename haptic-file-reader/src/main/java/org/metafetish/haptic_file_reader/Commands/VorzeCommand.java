package org.metafetish.haptic_file_reader.Commands;

public class VorzeCommand extends HapticCommand {
    private int direction;
    private int speed;

    public VorzeCommand(int time, int direction, int speed) {
        super(time);
        this.direction = direction;
        this.speed = speed;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }
}
