package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.Handlers.KiirooHandler;

public class KiirooTest {
    private static String sample = "var kiiroo_subtitles = {1,4;2.5,5;3.1,0}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(KiirooTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(KiirooHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(KiirooCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(4, ((KiirooCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(5, ((KiirooCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((KiirooCommand) handler.commands.get(2)).getPosition());
    }
}