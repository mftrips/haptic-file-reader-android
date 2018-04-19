package org.metafetish.haptic_file_reader.Handlers;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.io.IOException;
import java.io.StringReader;

public class VirtualRealPornHandler extends HapticFileHandler {
    @Override
    public void loadString(String body) {
        INIConfiguration ini = new INIConfiguration();
        try {
            ini.read(new StringReader(body));
            SubnodeConfiguration section = ini.getSection("Kiiroo");
            if (section != null) {
                if (section.containsKey("onyx")) {
                    String commands = section.getString("onyx");
                    this.commands = KiirooHandler.parseCommands(String.format("{%s}", commands));
                    return;
                }
            }
            section = ini.getSection("Lovense");
            if (section != null) {
                if (section.containsKey("hombre")) {
                    String commands = section.getString("hombre");
                    this.commands = KiirooHandler.parseCommands(String.format("{%s}", commands), "-", "/");
                }
            }
        } catch (ConfigurationException | IOException e) {
            //TODO: Handle exceptions
            e.printStackTrace();
        }
    }
}
