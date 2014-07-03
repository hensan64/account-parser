package accountparser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public final void testParseCitibankChrome() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("1\t27/02/2013\t27/02/2013\t1305851001333\t,  Payment   Thank  You, \t00000\t\t-1.234.567,98\tUSD\t\t- 2.345.678,90");
        stringList.add("2\t24/02/2013\t25/02/2013\t6011162125079\tLingmerths, RAMKVILLA ,SE\t7474\t\t9.876.543,21\tUSD\t\t8.765.432,10");
        final String prefix = "CB";
        final ParserData parserData = new ParserData.Builder().setHasInvertedSign(true)
                                                              .setRegex(ParserData.CITIBANK_CHROME_REGEX)
                                                              .build();
        final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
        final TransactionData data1 = dataList.get(0);
        final TransactionData data2 = dataList.get(1);
        Assert.assertEquals("2013", data1.getYear());
        Assert.assertEquals("02", data1.getMonth());
        Assert.assertEquals("27", data1.getDay());
        Assert.assertEquals(prefix, data1.getPrefix());
        Assert.assertEquals("Payment Thank You", data1.getMemo());
        Assert.assertEquals("inflow", data1.getType());
        Assert.assertEquals("2345678.90", data1.getValue());
        Assert.assertEquals("2013", data2.getYear());
        Assert.assertEquals("02", data2.getMonth());
        Assert.assertEquals("24", data2.getDay());
        Assert.assertEquals(prefix, data2.getPrefix());
        Assert.assertEquals("Lingmerths RAMKVILLA SE", data2.getMemo());
        Assert.assertEquals("outflow", data2.getType());
        Assert.assertEquals("8765432.10", data2.getValue());
    }

    @Test
    public final void testParseSkandiabankenChrome() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("2012-09-01\tKortköp CURA APOTEK SOLNA SOLNA\t2 345 678,90\t-5 957,86");
        stringList.add("2012-08-31\tAutomatuttag 325675\t-8 765 432,10\t-5 562,86");
        final String prefix = "SB";
        final ParserData parserData = new ParserData.Builder().setHasInvertedSign(false)
                                                              .setRegex(ParserData.SKANDIABANKEN_CHROME_REGEX)
                                                              .build();
        final List<TransactionData> dataList = Parser.parse(stringList, prefix, parserData);
        final TransactionData data1 = dataList.get(0);
        final TransactionData data2 = dataList.get(1);
        Assert.assertEquals("2012", data1.getYear());
        Assert.assertEquals("09", data1.getMonth());
        Assert.assertEquals("01", data1.getDay());
        Assert.assertEquals(prefix, data1.getPrefix());
        Assert.assertEquals("Kortköp CURA APOTEK SOLNA SOLNA", data1.getMemo());
        Assert.assertEquals("inflow", data1.getType());
        Assert.assertEquals("2345678.90", data1.getValue());
        Assert.assertEquals("2012", data2.getYear());
        Assert.assertEquals("08", data2.getMonth());
        Assert.assertEquals("31", data2.getDay());
        Assert.assertEquals(prefix, data2.getPrefix());
        Assert.assertEquals("Automatuttag 325675", data2.getMemo());
        Assert.assertEquals("outflow", data2.getType());
        Assert.assertEquals("8765432.10", data2.getValue());
    }

}
