package account_parser;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

    @Test
    public final void testMatch() {
        final Matcher matcher = Regex.match("Henrik Sandström", "^Henrik\\sSandström$");
        Assert.assertTrue(matcher.matches());
    }
}
