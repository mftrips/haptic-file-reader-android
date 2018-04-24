package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.Handlers.FeelVRHandler;

public class WankZTest {
    private static String sample = "{" +
                "\"subs\":{" +
                    "\"session_id\":\"123456789012345\"," +
                    "\"id\":12345," +
                    "\"video_external_id\":\"12345\"," +
                    "\"text\":\"{\\\"1\\\":3,\\\"2.51\\\":4,\\\"3.12\\\":0}\"," +
                    "\"description\":\"upload\"," +
                    "\"created\":\"1970-01-01T00:00:00.000000\"," +
                    "\"type\":\"penetration\"," +
                    "\"name\":\"1234567_full.subtitles.js\"," +
                    "\"video\":{" +
                        "\"description\":null," +
                        "\"name\":\"1234\"," +
                        "\"external_id\":\"1234\"," +
                        "\"created\":\"1970-01-01T00:00:00.000000\"," +
                        "\"subtitles_count\":1" +
                    "}" +
                "}," +
                "\"format\":\"STEREO_180_LR\"," +
                "\"file\":\"filename.mp4\"," +
                "\"source\":\"https://domain.tld/filename.mp4?query=string\"," +
                "\"downloadComplete\":true" +
            "}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(WankZTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(FeelVRHandler.class, handler.getClass());
        Assert.assertNotNull(handler.commands);
        Assert.assertEquals(3, handler.commands.size());
        for (HapticCommand command : handler.commands) {
            Assert.assertEquals(KiirooCommand.class, command.getClass());
        }
        Assert.assertEquals(1000, handler.commands.get(0).getTime());
        Assert.assertEquals(3, ((KiirooCommand) handler.commands.get(0)).getPosition());
        Assert.assertEquals(2510, handler.commands.get(1).getTime());
        Assert.assertEquals(4, ((KiirooCommand) handler.commands.get(1)).getPosition());
        Assert.assertEquals(3120, handler.commands.get(2).getTime());
        Assert.assertEquals(0, ((KiirooCommand) handler.commands.get(2)).getPosition());
    }
}