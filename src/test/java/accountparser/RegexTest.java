package accountparser;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class RegexTest {

  @SuppressWarnings("static-method")
  @Test
  public final void testMatch() {
    final Matcher matcher = Regex.match("Henrik Sandström", "^Henrik\\sSandström$");
    Assert.assertTrue(matcher.matches());
  }
}
