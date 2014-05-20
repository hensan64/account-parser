package account_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Converter {

    private Converter() {
        Lib.instantiationNotAllowed();
    }

    static void execute(final Bank bank, final String inPath, final String outPath, final String prefix) throws IOException {
        final List<String> lines = File.read(inPath);
        @SuppressWarnings("unchecked") final List<String> lines1 = (List<String>) Lib.handleNull(lines, "lines");
        List<Data> dataList;
        if (bank.equals(Bank.CITIBANK)) {
            dataList = CitibankChrome.parse(lines1, prefix);
        } else if (bank.equals(Bank.SKANDIABANKEN)) {
            dataList = SkandiabankenChrome.parse(lines1, prefix);
        } else {
            throw (new Error("Unknown bank: " + bank.toString()));
        }
        // Lib.print(dataList);
        Converter.write(dataList, outPath);
    }

    private static void write(final List<Data> dataList, final String filePath) throws IOException {
        final List<String> lines = new ArrayList<>();
        String valueString;
        lines.add("Date,Payee,Category,Memo,Outflow,Inflow");
        for (final Data data : dataList) {
            final String type = data.getType();
            if (type == "inflow") {
                valueString = "," + data.getValue();
            } else if (type == "outflow") {
                valueString = data.getValue() + ",";
            } else {
                throw (new Error("Wrong value type: Neither 'inflow' | 'outflow': " + type.toString()));
            }
            // "Payee" field in YNAB not used
            lines.add(data.getYear() + "-" + data.getMonth() + "-" + data.getDay() + "," + "," + data.getPrefix() + ","
                      + data.getMemo() + "," + valueString);
        }
        File.write(filePath, lines);
    }
}
