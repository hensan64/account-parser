package account_parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public final class SkandiabankenChrome {

    private SkandiabankenChrome() {
        Lib.instantiationNotAllowed();
    }

    public static List<Data> parse(final List<String> lines, final String prefix) {
        final List<Data> dataList = new ArrayList<>();
        for (final String line : lines) {
            final String line1 = Lib.handleNull(line);
            if (Lib.isBlank(line1)) {
                continue;
            }
            final String regex = "^(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})\\t(?<memo>.+?)\\t(?<sign>-?)(?<value>.+?)\\t.*$";
            final Matcher matcher = Regex.match(line1, regex);
            if (!matcher.matches()) { // Executes matching
                throw (new Error("Line did not match regexp: " + line1));
            }
            final String sign = matcher.group("sign");
            final String type;
            if (sign.equals("-")) {
                type = "outflow";
            } else if (sign.equals("")) {
                type = "inflow";
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
