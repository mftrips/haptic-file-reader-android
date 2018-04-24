package org.metafetish.haptic_file_reader;

import org.junit.Assert;
import org.junit.Test;
import org.metafetish.haptic_file_reader.Commands.HapticCommand;
import org.metafetish.haptic_file_reader.Commands.KiirooCommand;
import org.metafetish.haptic_file_reader.Handlers.FeelVRHandler;

public class FeelVRTest {
    private static String sample = "{" +
                "\"subs\":{" +
                    "\"created\":\"1970-01-01T00:00:00.000000\"," +
                    "\"video_external_id\":\"1234\"," +
                    "\"description\":\"Imported subtitle\"," +
                    "\"video\":{" +
                        "\"created\":\"1970-01-01T00:00:00.000000\"," +
                        "\"name\":\"1234\"," +
                        "\"external_id\":\"1234\"," +
                        "\"description\":null," +
                        "\"subtitles_count\":1" +
                    "}," +
                    "\"text\":\"{1,4;2.5,5;3.1,0}\"," +
                    "\"session_id\":\"123456789012345\"," +
                    "\"id\":1234," +
                    "\"name\":\"Video Name\"," +
                    "\"type\":\"penetration\"" +
                "}," +
                "\"format\":\"STEREO_180_LR\"," +
                "\"file\":\"filename.mp4\"," +
                "\"source\":\"https://domain.tld/filename.mp4?query=string\"," +
                "\"downloadComplete\":true" +
            "}";

    @Test
    public void test() {
        HapticFileHandler handler = HapticFileHandler.handleString(FeelVRTest.sample);
        Assert.assertNotNull(handler);
        Assert.assertEquals(FeelVRHandler.class, handler.getClass());
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