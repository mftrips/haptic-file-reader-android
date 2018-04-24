package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.FunscriptCommand;
import org.metafetish.haptic_file_reader.Handlers.FunscriptHandler;
import org.metafetish.haptic_file_reader.Properties.FunscriptProperties;

public class FunscriptTest {
    private static String sample = "{" +
            "\"version\": \"1.0\", " +
            "\"range\": 90, " +
            "\"inverted\": false, " +
            "\"actions\": [" +
                "{\"pos\": 75, \"at\": 1000}, " +
                "{\"pos\": 100, \"at\": 2500}, " +
                "{\"pos\": 0, \"at\": 3100}" +
            "]}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(FunscriptTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(FunscriptHandler.class, handler.getClass());
        Assert.assertNotNull(handler.properties);
        Assert.assertEquals(FunscriptProperties.class, handler.properties.getClass());
        FunscriptProperties properties = (FunscriptProperties) handler.properties;
        Assert.assertEquals("1.0", properties.getVersion());
        Assert.assertEquals(90, properties.getRange());
        Assert.assertEquals(false, properties.isInverted());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(FunscriptCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(75, ((FunscriptCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(100, ((FunscriptCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((FunscriptCommand) handler.commands.get(2)).getPosition());
    }
}