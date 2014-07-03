package accountparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Regex {

    private Regex() {
        Lib.instantiationNotAllowed();
    }

    static Matcher match(final String input, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);
        return Lib.handleNull(matcher, "matcher");
    }
}
