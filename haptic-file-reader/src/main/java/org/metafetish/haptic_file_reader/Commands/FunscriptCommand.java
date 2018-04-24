package org.metafetish.haptic_file_reader.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FunscriptCommand extends HapticCommand {
    private int position;

    public FunscriptCommand(
            @JsonProperty("at") int time,
            @JsonProperty("pos") int position) {
        super(time);
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
