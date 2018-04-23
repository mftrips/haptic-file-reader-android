package org.metafetish.haptic_file_reader.Properties;

public class VirtualRealPornProperties extends HapticProperties {
    public static class Player {
        private int zoom;
        private float vertRot;
        private int hOffset;

        public Player(int zoom, float vertRot, int hOffset) {
            this.zoom = zoom;
            this.vertRot = vertRot;
            this.hOffset = hOffset;
        }

        public int getZoom() {
            return this.zoom;
        }

        public float getVertRot() {
            return this.vertRot;
        }

        public int getHOffset() {
            return this.hOffset;
        }
    }

    public static class VideoInfo {
        private String name;
        private int version;

        public VideoInfo(String name, int version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return this.name;
        }

        public int getVersion() {
            return this.version;
        }
    }

    private Player player;
    private VideoInfo videoInfo;

    public VirtualRealPornProperties(Player player, VideoInfo videoInfo) {
        this.player = player;
        this.videoInfo = videoInfo;
    }

    public Player getPlayer() {
        return this.player;
    }

    public VideoInfo getVideoInfo() {
        return this.videoInfo;
    }
}
