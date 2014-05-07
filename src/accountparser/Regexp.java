package accountparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regexp {
    static Matcher match(String input, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
