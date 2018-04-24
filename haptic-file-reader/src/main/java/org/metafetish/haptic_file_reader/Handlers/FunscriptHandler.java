package org.metafetish.haptic_file_reader.Handlers;

import org.metafetish.haptic_file_reader.Commands.FunscriptCommand;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.HapticFileHandler;
import org.metafetish.haptic_file_reader.Properties.FunscriptProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunscriptHandler extends HapticFileHandler {
    @SuppressWarnings("WeakerAccess")
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class FunscriptMap {
        public String version = "1.0";
        public int range = 90;
        public boolean inverted = false;
        public ArrayList<FunscriptCommand> actions;
    }

    @Override
    public void loadString(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            FunscriptMap map = mapper.readValue(body, FunscriptMap.class);
            this.properties = new FunscriptProperties(map.version, map.range, map.inverted);
            if (map.actions != null) {
                this.commands =  Collections.unmodifiableList((List<? extends HapticCommand>) map.actions);
            }
        } catch (JsonParseException | JsonMappingException e) {
            throw new IllegalArgumentException("Wrong format");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
