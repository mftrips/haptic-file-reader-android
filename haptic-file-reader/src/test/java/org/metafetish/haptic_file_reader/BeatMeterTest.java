package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.BeatMeterCommand;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Handlers.BeatMeterHandler;

public class BeatMeterTest {
    private static String sample = "" +
            "1\n" +
            "2.5\n" +
            "3.1";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(BeatMeterTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(BeatMeterHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(BeatMeterCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
    }
}