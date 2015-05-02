package accountparser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

  private static int FIRST  = 0;
  private static int SECOND = 1;

  private final static void checkData(final TransactionData referenceData, final TransactionData data) {
    Assert.assertEquals(referenceData.getYear(), data.getYear());
    Assert.assertEquals(referenceData.getMonth(), data.getMonth());
    Assert.assertEquals(referenceData.getDay(), data.getDay());
    Assert.assertEquals(referenceData.getPrefix(), data.getPrefix());
    Assert.assertEquals(referenceData.getMemo(), data.getMemo());
    Assert.assertEquals(referenceData.getType(), data.getType());
    Assert.assertEquals(referenceData.getValue(), data.getValue());
  }

  private final static void checkDataList(final TransactionData referenceData1, final TransactionData referenceData2, final List<TransactionData> dataList) {
    final TransactionData data1 = dataList.get(FIRST);
    final TransactionData data2 = dataList.get(SECOND);
    checkData(referenceData1, Lib.handleNull(data1));
    checkData(referenceData2, Lib.handleNull(data2));
  }

  private final static ParserData getParserData(final boolean hasInvertedSign, final String regex) {
    return new ParserData.Builder().setHasInvertedSign(hasInvertedSign).setRegex(regex).build();
  }

  private final static TransactionData getTransactionData(final String year, final String month, final String day, final String prefix, final String memo,
                                                          final String type, final String value) {
    return new TransactionData.Builder().setYear(year).setMonth(month).setDay(day).setPrefix(prefix).setMemo(memo).setValue(value).setType(type).build();
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testParseCitibankChrome() {
    final List<String> stringList = new ArrayList<>();
    stringList.add("1\t27/02/2013\t27/02/2013\t1305851001333\t,  Payment   Thank  You, \t00000\t\t-1.234.567,98\tUSD\t\t- 2.345.678,90");
    stringList.add("2\t24/02/2013\t25/02/2013\t6011162125079\tLingmerths, RAMKVILLA ,SE\t7474\t\t9.876.543,21\tUSD\t\t8.765.432,10");
    final ParserData parserData = getParserData(true, ParserData.CITIBANK_CHROME_REGEX);
    final String prefix = "CB";
    final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
    final TransactionData referenceData1 = getTransactionData("2013", "02", "27", prefix, "Payment Thank You", "inflow", "2345678.90");
    final TransactionData referenceData2 = getTransactionData("2013", "02", "24", prefix, "Lingmerths RAMKVILLA SE", "outflow", "8765432.10");
    checkDataList(referenceData1, referenceData2, dataList);
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testParseSkandiabankenChrome() {
    final List<String> stringList = new ArrayList<>();
    stringList.add("2012-09-01\tKortköp CURA APOTEK SOLNA SOLNA\t2 345 678,90\t-5 957,86");
    stringList.add("2012-08-31\tAutomatuttag 325675\t-8 765 432,10\t-5 562,86");
    final ParserData parserData = getParserData(false, ParserData.SKANDIABANKEN_CHROME_REGEX);
    final String prefix = "SB";
    final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
    final TransactionData referenceData1 = getTransactionData("2012", "09", "01", prefix, "Kortköp CURA APOTEK SOLNA SOLNA", "inflow", "2345678.90");
    final TransactionData referenceData2 = getTransactionData("2012", "08", "31", prefix, "Automatuttag 325675", "outflow", "8765432.10");
    checkDataList(referenceData1, referenceData2, dataList);
  }
}
