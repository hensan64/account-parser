package accountparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

class CitibankChrome {

    static List<Data> parse(List<String> lines, String prefix) {
        List<Data> dataList = new ArrayList<Data>();

        for (String line : lines) {
            if (Lib.isBlank(line))
                continue;

            String regexp = "^.+?\\t(?<day>\\d{2})\\/(?<month>\\d{2})\\/(?<year>\\d{4})(?:\\t.*?){2}\\t(?<memo>.+?)(?:\\t.*?){5}\\t(?<sign>-?)(?<value>.+?)$";
            Matcher matcher = Regexp.match(line, regexp);

            if (matcher.matches() != true)
                throw (new Error("Line did not match regexp: " + line));

            Data data = new Data();

            data.year = matcher.group("year");
            data.month = matcher.group("month");
            data.day = matcher.group("day");
            data.prefix = prefix;
            data.memo = Lib.formatMemo(matcher.group("memo"));
            data.value = Lib.formatValue(matcher.group("value"));

            String sign = matcher.group("sign");

            if (sign.equals("-"))
                data.type = "inflow";
            else if (sign.equals(""))
                data.type = "outflow";
            else
                throw (new Error("Unknown sign: " + sign));

            dataList.add(data);
        }

        return dataList;
    }

}