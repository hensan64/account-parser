package accountparser;

import static org.junit.Assert.*;

import java.util.regex.*;

import org.junit.Test;

import accountparser.Regexp;


public class RegexpTest {

    @Test
    public void testMatch() {
        Matcher matcher = Regexp.match("Henrik Sandstr�m", "^Henrik\\sSandstr�m$");
        assertTrue(matcher.matches());
    }

}
