package accountparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

final class Parser {

    private Parser() {
        Lib.instantiationNotAllowed();
    }

    static List<TransactionData> parse(final List<String> lines, final String prefix, final ParserData parserData) {
        final List<TransactionData> dataList = new ArrayList<>();
        for (final String line : lines) {
            final String line1 = Lib.handleNull(line);
            if (Lib.isBlank(line1)) {
                continue;
            }
            final String regexp = parserData.getRegex();
            final Matcher matcher = Regex.match(line1, regexp);
            // Executes matching
            if (!matcher.matches()) {
                throw new AccountParserException("Line did not match regexp: " + line1);
            }
            final String sign = matcher.group("sign");
            final String type;
            if ("-".equals(sign)) {
                if (parserData.getHasInvertedSign()) {
                    type = "inflow";
                } else {
                    type = "outflow";
                }
            } else if ("".equals(sign)) {
                if (parserData.getHasInvertedSign()) {
                    type = "outflow";
                } else {
                    type = "inflow";
                }
            } else {
                throw new AccountParserException("Unknown sign: " + sign);
            }
            final TransactionData data = new TransactionData.Builder().setYear(Lib.getMatcherGroup(matcher, "year"))
                            .setMonth(Lib.getMatcherGroup(matcher, "month"))
                            .setDay(Lib.getMatcherGroup(matcher, "day"))
                            .setPrefix(prefix)
                            .setMemo(Lib.formatMemo(Lib.getMatcherGroup(matcher,
                                                                        "memo")))
                                                                        .setValue(Lib.formatValue(Lib.getMatcherGroup(matcher,
                                                                                        "value")))
                                                                                        .setType(type)
                                                                                        .build();
            dataList.add(data);
        }
        return dataList;
    }
}
