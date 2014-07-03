package accountparser;

import org.junit.Assert;
import org.junit.Test;

public class ParserDataTest {

    @Test
    public final void testPrint() {
        final ParserData data = new ParserData.Builder().setHasInvertedSign(false).setRegex(".*").build();
        Assert.assertEquals("{hasInvertedSign: false, regex: .*}", data.getDataString());
    }
}
