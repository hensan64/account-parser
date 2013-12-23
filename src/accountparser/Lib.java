package accountparser;

import java.util.ArrayList;
import java.util.List;

class Lib {

    static boolean isBlank(String line) {
        Boolean reply;
        if (line.trim().length() == 0)
            reply = true;
        else
            reply = false;

        return reply;
    }

    static String formatMemo(String memo) {
        // Replace commas with whitespace, to avoid separator (comma) conflicts
        String memo1 = memo.replaceAll(",", " ");
        // Replace multiple whitespace with single whitespace, and trim the ends
        String memo2 = memo1.replaceAll("\\s{2,}", " ").trim();

        return memo2;
    }

    static String formatValue(String value) {
        // Remove dots and whitespace from the value
        String value1 = value.replaceAll("[.\\s]", "");
        // Replace decimal comma with decimal dot

        String value2 = value1.replaceAll(",", ".").trim();

        return value2;
    }

    static List<String> print(List<Data> dataList) {
        List<String> dataStringList = new ArrayList<String>();

        for (Data data : dataList) {
            String dataString = data.print();
            dataStringList.add(dataString);
        }

        // Return a list of strings for unit test purpose
        return dataStringList;
    }

    static String print(Object object) {
        String objectString = object.toString();
        System.out.println(objectString);

        // Return an object string for unit test purpose
        return objectString;
    }

}