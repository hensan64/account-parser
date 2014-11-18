package accountparser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    private static int FIRST = 0;
    private static int SECOND = 1;

    @Test
    public final void testParseCitibankChrome() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("1\t27/02/2013\t27/02/2013\t1305851001333\t,  Payment   Thank  You, \t00000\t\t-1.234.567,98\tUSD\t\t- 2.345.678,90");
        stringList.add("2\t24/02/2013\t25/02/2013\t6011162125079\tLingmerths, RAMKVILLA ,SE\t7474\t\t9.876.543,21\tUSD\t\t8.765.432,10");
        final ParserData parserData = getParserData(true, ParserData.CITIBANK_CHROME_REGEX);
        final String prefix = "CB";
        final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
        final TransactionData referenceData1 = getTransactionData("2013", "02", "27", prefix, "Payment Thank You",
                                                                  "inflow", "2345678.90");
        final TransactionData referenceData2 = getTransactionData("2013", "02", "24", prefix,
                                                                  "Lingmerths RAMKVILLA SE", "outflow", "8765432.10");
        checkDataList(referenceData1, referenceData2, dataList);
    }

    @Test
    public final void testParseSkandiabankenChrome() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("2012-09-01\tKortköp CURA APOTEK SOLNA SOLNA\t2 345 678,90\t-5 957,86");
        stringList.add("2012-08-31\tAutomatuttag 325675\t-8 765 432,10\t-5 562,86");
        final ParserData parserData = getParserData(false, ParserData.SKANDIABANKEN_CHROME_REGEX);
        final String prefix = "SB";
        final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
        final TransactionData referenceData1 = getTransactionData("2012", "09", "01", prefix,
                                                                  "Kortköp CURA APOTEK SOLNA SOLNA", "inflow",
                                                                  "2345678.90");
        final TransactionData referenceData2 = getTransactionData("2012", "08", "31", prefix, "Automatuttag 325675",
                                                                  "outflow", "8765432.10");
        checkDataList(referenceData1, referenceData2, dataList);
    }

    private final void checkDataList(final TransactionData referenceData1, final TransactionData referenceData2,
                                     final List<TransactionData> dataList) {
        final TransactionData data1 = dataList.get(FIRST);
        final TransactionData data2 = dataList.get(SECOND);

        Assert.assertEquals(referenceData1.getYear(), data1.getYear());
        Assert.assertEquals(referenceData1.getMonth(), data1.getMonth());
        Assert.assertEquals(referenceData1.getDay(), data1.getDay());
        Assert.assertEquals(referenceData1.getPrefix(), data1.getPrefix());
        Assert.assertEquals(referenceData1.getMemo(), data1.getMemo());
        Assert.assertEquals(referenceData1.getType(), data1.getType());
        Assert.assertEquals(referenceData1.getValue(), data1.getValue());

        Assert.assertEquals(referenceData2.getYear(), data2.getYear());
        Assert.assertEquals(referenceData2.getMonth(), data2.getMonth());
        Assert.assertEquals(referenceData2.getDay(), data2.getDay());
        Assert.assertEquals(referenceData2.getPrefix(), data2.getPrefix());
        Assert.assertEquals(referenceData2.getMemo(), data2.getMemo());
        Assert.assertEquals(referenceData2.getType(), data2.getType());
        Assert.assertEquals(referenceData2.getValue(), data2.getValue());
    }

    private final ParserData getParserData(final boolean hasInvertedSign, final String regex) {
        return new ParserData.Builder().setHasInvertedSign(hasInvertedSign).setRegex(regex).build();
    }

    private final TransactionData getTransactionData(final String year, final String month, final String day,
                                                     final String prefix, final String memo, final String type,
                                                     final String value) {
        return new TransactionData.Builder().setYear(year)
                                            .setMonth(month)
                                            .setDay(day)
                                            .setPrefix(prefix)
                                            .setMemo(memo)
                                            .setValue(value)
                                            .setType(type)
                                            .build();
    }
}
