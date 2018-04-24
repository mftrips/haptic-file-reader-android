package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.Handlers.FeelMeHandler;

public class FeelMeTest {
    private static String sample = "{" +
            "\"name\": \"Video Name\", " +
            "\"session_id\": \"123456789012345\", " +
            "\"type\": \"penetration\", " +
            "\"id\": 1234, " +
            "\"text\": \"{1,3;2.5,4;3.1,0}\", " +
            "\"created\": \"1970-01-01T00:00:00.000000\", " +
            "\"description\": \"\", " +
            "\"video_external_id\": \"123456789\", " +
            "\"video\": {" +
                "\"created\": \"1970-01-01T00:00:00.000000\", " +
                "\"external_id\": \"123456789\", " +
                "\"name\": \"123456789\", " +
                "\"description\": null, " +
                "\"subtitles_count\": 1" +
            "}}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(FeelMeTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(FeelMeHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(KiirooCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(3, ((KiirooCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(4, ((KiirooCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((KiirooCommand) handler.commands.get(2)).getPosition());
    }
}