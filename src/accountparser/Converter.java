package accountparser;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

class Converter {

    static void execute(Bank bank, String inPath, String outPath, String prefix)
            throws IOException {
        List<String> lines = File.read(inPath);

        List<Data> dataList;

        if (bank.equals(Bank.CITIBANK))
            dataList = CitibankChrome.parse(lines, prefix);
        else if (bank.equals(Bank.SKANDIABANKEN))
            dataList = SkandiabankenChrome.parse(lines, prefix);
        else
            throw (new Error("Unknown bank: " + bank.toString()));

        // Lib.print(dataList);
        write(dataList, outPath);
    }

    private static void write(List<Data> dataList, String filePath)
            throws IOException {
        List<String> lines = new ArrayList<String>();
        String valueString;

        lines.add("Date,Payee,Category,Memo,Outflow,Inflow");

        for (Data data : dataList) {
            if (data.type == "inflow")
                valueString = "," + data.value;
            else if (data.type == "outflow")
                valueString = data.value + ",";
            else
                throw (new Error(
                        "Wrong value type: Neither 'inflow' | 'outflow': "
                                + data.type.toString()));

            // Payee (not used)
            lines.add(data.year + "-" + data.month + "-" + data.day + "," + ","
                    + data.prefix + "," + data.memo + "," + valueString);
        }

        File.write(filePath, lines);
    }

}
