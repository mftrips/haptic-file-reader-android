package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.VorzeCommand;
import org.metafetish.haptic_file_reader.Handlers.VorzeHandler;

public class VorzeTest {
    private static String sample = "" +
            "10,1,75,\n" +
            "25,0,100,\n" +
            "31,1,0,\n";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(VorzeTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(VorzeHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(VorzeCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(1, ((VorzeCommand) handler.commands.get(0)).getDirection());
        Assert.assertEquals(75, ((VorzeCommand) handler.commands.get(0)).getSpeed());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(0, ((VorzeCommand) handler.commands.get(1)).getDirection());
        Assert.assertEquals(100, ((VorzeCommand) handler.commands.get(1)).getSpeed());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(1, ((VorzeCommand) handler.commands.get(2)).getDirection());
        Assert.assertEquals(0, ((VorzeCommand) handler.commands.get(2)).getSpeed());
    }
}