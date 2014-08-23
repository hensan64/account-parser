package accountparser;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

    @Test
    public final void testMatch() {
        final Matcher matcher = Regex.match("Henrik Sandstr�m", "^Henrik\\sSandstr�m$");
        Assert.assertTrue(matcher.matches());
    }
}
