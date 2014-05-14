package accountparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

final class CitibankChrome {

    private CitibankChrome() {
        Lib.instantiationNotAllowed();
    }

    static List<Data> parse(final List<String> lines, final String prefix) {
        final List<Data> dataList = new ArrayList<>();
        for (final String line : lines) {
            final String line1 = (String) Lib.handleNull(line, "line");
            if (Lib.isBlank(line1)) {
                continue;
            }
            final String regexp = "^.+?\\t(?<day>\\d{2})\\/(?<month>\\d{2})\\/(?<year>\\d{4})(?:\\t.*?){2}\\t(?<memo>.+?)(?:\\t.*?){5}\\t(?<sign>-?)(?<value>.+?)$";
            final Matcher matcher = Regex.match(line1, regexp);
            if (!matcher.matches()) { // Executes matching
                throw (new Error("Line did not match regexp: " + line1));
            }
            final String sign = matcher.group("sign");
            final String type;
            if (sign.equals("-")) {
                type = "inflow";
            } else if (sign.equals("")) {
                type = "outflow";
            } else {
                throw (new Error("Unknown sign: " + sign));
            }
            final Data data = new Data.Builder().setYear(Lib.getMatcherGroup(matcher, "year"))
                                                .setMonth(Lib.getMatcherGroup(matcher, "month"))
                                                .setDay(Lib.getMatcherGroup(matcher, "day"))
                                                .setPrefix(prefix)
                                                .setMemo(Lib.formatMemo(Lib.getMatcherGroup(matcher, "memo")))
                                                .setValue(Lib.formatValue(Lib.getMatcherGroup(matcher, "value")))
                                                .setType(type)
                                                .build();
            dataList.add(data);
        }
        return dataList;
    }
}
