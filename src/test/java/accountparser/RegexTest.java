package accountparser;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

    @SuppressWarnings("static-method")
    @Test
    public final void testMatch() {
        final Matcher matcher = Regex.match("Henrik Sandstr�m", "^Henrik\\sSandstr�m$");
        Assert.assertTrue(matcher.matches());
    }
}
