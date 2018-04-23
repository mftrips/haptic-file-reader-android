package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.Handlers.VirtualRealPornHandler;
import org.metafetish.haptic_file_reader.Properties.VirtualRealPornProperties;

public class VirtualRealPornTest {
    private static String sample = "" +
            "[Player]\n" +
            "zoom=0\n" +
            "vert_rot=1.5\n" +
            "h_offset=0\n" +
            "\n" +
            "[VideoInfo]\n" +
            "name=Video Name\n" +
            "version=2\n" +
            "\n" +
            "[Kiiroo]\n" +
            "onyx=1,4;2.5,5;3.1,0\n" +
            "\n" +
            "[Lovense]\n" +
            "hombre=-1/05-2.6/00-3.2/05";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(VirtualRealPornTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(VirtualRealPornHandler.class, handler.getClass());
        Assert.assertNotNull(handler.properties);
        Assert.assertEquals(VirtualRealPornProperties.class, handler.properties.getClass());
        VirtualRealPornProperties properties = (VirtualRealPornProperties) handler.properties;
        VirtualRealPornProperties.Player player = properties.getPlayer();
        Assert.assertNotNull(player);
        Assert.assertEquals(0, player.getZoom());
        Assert.assertEquals(1.5, player.getVertRot(), 0);
        Assert.assertEquals(0, player.getHOffset());
        VirtualRealPornProperties.VideoInfo videoInfo = properties.getVideoInfo();
        Assert.assertNotNull(videoInfo);
        Assert.assertEquals("Video Name", videoInfo.getName());
        Assert.assertEquals(2, videoInfo.getVersion());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(6, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(KiirooCommand.class, command.getClass());
        }
        for (int i = 0; i < 3; ++i) {
            Assert.assertEquals(HapticDevice.LINEAR, handler.commands.get(i).getDevice());
        }
        for (int i = 3; i < 6; ++i) {
            Assert.assertEquals(HapticDevice.VIBRATE, handler.commands.get(i).getDevice());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(4, ((KiirooCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2500, handler.commands.get(1).getTime());
        Assert.assertEquals(5, ((KiirooCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3100, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((KiirooCommand) handler.commands.get(2)).getPosition());
        Assert.assertEquals(1000, handler.commands.get(3).getTime());
        Assert.assertEquals(5, ((KiirooCommand) handler.commands.get(3)).getPosition());
        Assert.assertEquals(2600, handler.commands.get(4).getTime());
        Assert.assertEquals(0, ((KiirooCommand) handler.commands.get(4)).getPosition());
        Assert.assertEquals(3200, handler.commands.get(5).getTime());
        Assert.assertEquals(5, ((KiirooCommand) handler.commands.get(5)).getPosition());
    }
}