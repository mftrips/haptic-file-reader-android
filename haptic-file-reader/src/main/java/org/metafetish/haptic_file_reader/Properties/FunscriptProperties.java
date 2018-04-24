package org.metafetish.haptic_file_reader.Properties;

public class FunscriptProperties extends HapticProperties {
    private String version;
    private int range;
    private boolean inverted;

    @SuppressWarnings("unused")
    public FunscriptProperties(int range) {
        this(null, range, false);
    }

    @SuppressWarnings("unused")
    public FunscriptProperties(boolean inverted) {
        this(null, 100, inverted);
    }

    @SuppressWarnings("unused")
    public FunscriptProperties(int range, boolean inverted) {
        this(null, range, inverted);
    }

    public FunscriptProperties(String version, int range, boolean inverted) {
        this.version = version;
        this.range = range;
        this.inverted = inverted;
    }

    public String getVersion() {
        return this.version;
    }

    public int getRange() {
        return this.range;
    }

    public boolean isInverted() {
        return this.inverted;
    }
}
