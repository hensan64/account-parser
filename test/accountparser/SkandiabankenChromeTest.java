package accountparser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import accountparser.Data;
import accountparser.SkandiabankenChrome;

public class SkandiabankenChromeTest {

    @Test
    public void testParse() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("2012-09-01\tKortköp CURA APOTEK SOLNA SOLNA\t2 345 678,90\t-5 957,86");
        stringList.add("2012-08-31\tAutomatuttag 325675\t-8 765 432,10\t-5 562,86");

        String prefix = "SB";

        List<Data> dataList = SkandiabankenChrome.parse(stringList, prefix);
        Data data1 = dataList.get(0);
        Data data2 = dataList.get(1);
        
        assertEquals("2012", data1.year);
        assertEquals("09", data1.month);
        assertEquals("01", data1.day);
        assertEquals(prefix, data1.prefix);
        assertEquals("Kortköp CURA APOTEK SOLNA SOLNA", data1.memo);
        assertEquals("inflow", data1.type);
        assertEquals("2345678.90", data1.value);

        assertEquals("2012", data2.year);
        assertEquals("08", data2.month);
        assertEquals("31", data2.day);
        assertEquals(prefix, data2.prefix);
        assertEquals("Automatuttag 325675", data2.memo);
        assertEquals("outflow", data2.type);
        assertEquals("8765432.10", data2.value);
    }

}
