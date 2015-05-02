package accountparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Converter {

  private Converter() {
    Lib.instantiationNotAllowed();
  }

  static void execute(final Bank bank, final String inPath, final String outPath, final String prefix) throws IOException {
    final List<String> lines = File.read(inPath);
    final List<String> lines1 = Lib.handleNull(lines);
    List<TransactionData> dataList;
    final ParserData parserData;
    if (bank.equals(Bank.CITIBANK)) {
      parserData = new ParserData.Builder().setHasInvertedSign(true).setRegex(ParserData.CITIBANK_CHROME_REGEX).build();
    } else if (bank.equals(Bank.SKANDIABANKEN)) {
      parserData = new ParserData.Builder().setHasInvertedSign(false).setRegex(ParserData.SKANDIABANKEN_CHROME_REGEX).build();
    } else {
      throw new AccountParserException("Unknown bank: " + bank.toString());
    }
    dataList = Parser.parse(lines1, prefix, parserData);
    Converter.write(dataList, outPath);
  }

  private static void write(final List<TransactionData> dataList, final String filePath) throws IOException {
    final List<String> lines = new ArrayList<>();
    String valueString;
    lines.add("Date,Payee,Category,Memo,Outflow,Inflow");
    for (final TransactionData data : dataList) {
      final String type = data.getType();
      if ("inflow".equals(type)) {
        valueString = "," + data.getValue();
      } else if ("outflow".equals(type)) {
        valueString = data.getValue() + ",";
      } else {
        throw new AccountParserException("Wrong value type: Neither 'inflow' | 'outflow': " + type.toString());
      }
      // "Payee" field in YNAB not used
      lines.add(data.getYear() + "-" + data.getMonth() + "-" + data.getDay() + "," + "," + data.getPrefix() + "," + data.getMemo() + "," + valueString);
    }
    File.write(filePath, lines);
  }
}
