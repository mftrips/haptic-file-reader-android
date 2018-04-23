package org.metafetish.haptic_file_reader.Handlers;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.metafetish.haptic_file_reader.HapticDevice;
import org.metafetish.haptic_file_reader.HapticFileHandler;
import org.metafetish.haptic_file_reader.Properties.VirtualRealPornProperties;

import java.io.IOException;
import java.io.StringReader;

public class VirtualRealPornHandler extends HapticFileHandler {
    @Override
    public void loadString(String body) {
        INIConfiguration ini = new INIConfiguration();
        try {
            ini.read(new StringReader(body));
            VirtualRealPornProperties.Player player = null;
            SubnodeConfiguration section = ini.getSection("Player");
            if (section != null && section.containsKey("zoom") && section.containsKey("vert_rot") && section.containsKey("h_offset")) {
                player = new VirtualRealPornProperties.Player(section.getInt("zoom"),
                        section.getFloat("vert_rot"), section.getInt("h_offset"));
            }
            VirtualRealPornProperties.VideoInfo videoInfo = null;
            section = ini.getSection("VideoInfo");
            if (section != null && section.containsKey("name") && section.containsKey("version")) {
                videoInfo = new VirtualRealPornProperties.VideoInfo(section.getString("name"),
                        section.getInt("version"));
            }
            this.properties = new VirtualRealPornProperties(player, videoInfo);

            HapticDevice device = HapticDevice.ANY;
            section = ini.getSection("Kiiroo");
            if (section != null && section.containsKey("onyx")) {
                if (ini.containsKey("Lovense.hombre")) {
                    device = HapticDevice.LINEAR;
                }
                String commands = section.getString("onyx");
                this.commands.addAll(KiirooHandler.parseCommands(String.format("{%s}", commands), device));
            }
            section = ini.getSection("Lovense");
            if (section != null && section.containsKey("hombre")) {
                if (ini.containsKey("Kiiroo.onyx")) {
                    device = HapticDevice.VIBRATE;
                }
                String commands = section.getString("hombre");
                this.commands.addAll(KiirooHandler.parseCommands(String.format("{%s}", commands), device, "-", "/"));
            }
        } catch (ConfigurationException | IOException e) {
            //TODO: Handle exceptions
            e.printStackTrace();
        }
    }
}
