package account_parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SkandiabankenChromeTest {

    @Test
    public final void testParse() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("2012-09-01\tKortköp CURA APOTEK SOLNA SOLNA\t2 345 678,90\t-5 957,86");
        stringList.add("2012-08-31\tAutomatuttag 325675\t-8 765 432,10\t-5 562,86");
        final String prefix = "SB";
        final List<Data> dataList = SkandiabankenChrome.parse(stringList, prefix);
        final Data data1 = dataList.get(0);
        final Data data2 = dataList.get(1);
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
