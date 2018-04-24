package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.FunscriptCommand;
import org.metafetish.haptic_file_reader.Handlers.FunscriptHandler;

public class FunscriptTest {
    private static String sample = "{" +
            "\"version\": \"1.0\", " +
            "\"range\": 90, " +
            "\"inverted\": false, " +
            "\"actions\": [" +
                "{\"pos\": 80, \"at\": 1000}, " +
                "{\"pos\": 100, \"at\": 2500}, " +
                "{\"pos\": 0, \"at\": 3100}" +
            "]}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(FunscriptTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(FunscriptHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(FunscriptCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(80, ((FunscriptCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(100, ((FunscriptCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((FunscriptCommand) handler.commands.get(2)).getPosition());
    }
}