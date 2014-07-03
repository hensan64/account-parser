package accountparser;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

import accountparser.Regex;

public class RegexTest {

    @Test
    public final void testMatch() {
        final Matcher matcher = Regex.match("Henrik Sandström", "^Henrik\\sSandström$");
        Assert.assertTrue(matcher.matches());
    }
}
