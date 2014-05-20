package account_parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.eclipse.jdt.annotation.Nullable;

final class Lib {

    private Lib() {
        instantiationNotAllowed();
    }

    static String formatMemo(final String memo) {
        // Replace commas with whitespace, to avoid separator (comma) conflicts
        final String memo1 = memo.replaceAll(",", " ");
        // Replace multiple whitespace with single whitespace, and trim the ends
        final String memo2 = memo1.replaceAll("\\s{2,}", " ").trim();
        return (String) Lib.handleNull(memo2, "memo2");
    }

    static String formatValue(final String value) {
        // Remove dots and whitespace from the value
        final String value1 = value.replaceAll("[.\\s]", "");
        // Replace decimal comma with decimal dot
        final String value2 = value1.replaceAll(",", ".").trim();
        return (String) Lib.handleNull(value2, "value2");
    }

    static String getMatcherGroup(final Matcher matcher, final String group) {
        final String string = matcher.group(group);
        return (String) Lib.handleNull(string, "string");
    }

    static Object handleNull(@Nullable final Object object, final String objectString) {
        if (object == null) {
            throw new RuntimeException("'" + objectString + "' is null");
        }
        return object;
    }

    static void instantiationNotAllowed() {
        throw new RuntimeException("Instantiating not allowed");
    }

    static boolean isBlank(final String line) {
        boolean result;
        if (line.trim().length() == 0) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    static List<String> print(final List<Data> dataList) {
        final List<String> dataStringList = new ArrayList<>();
        for (final Data data : dataList) {
            final String dataString = data.print();
            dataStringList.add(dataString);
        }
        // Return a list of strings for unit test purpose
        return dataStringList;
    }

    static String print(final String string) {
        System.out.println(string);
        // Return an object string for unit test purpose
        return string;
    }
}
