package org.metafetish.haptic_file_reader.Handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.metafetish.haptic_file_reader.HapticFileHandler;

import java.io.IOException;
import java.util.LinkedHashMap;

public class FeelVRHandler extends HapticFileHandler {
    @Override
    public void loadString(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LinkedHashMap map = (LinkedHashMap) mapper.readValue(body, Object.class);
            if (map.get("subs") != null && ((LinkedHashMap) map.get("subs")).get("text") != null) {
                this.commands = KiirooHandler.parseCommands((String) ((LinkedHashMap) map.get("subs")).get("text"));
            }
        } catch (JsonParseException | JsonMappingException e) {
            throw new IllegalArgumentException("Wrong format");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
