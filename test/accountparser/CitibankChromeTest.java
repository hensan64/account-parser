package accountparser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import accountparser.CitibankChrome;
import accountparser.Data;

public class CitibankChromeTest {

    @Test
    public void testParse() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("1\t27/02/2013\t27/02/2013\t1305851001333\t,  Payment   Thank  You, \t00000\t\t-1.234.567,98\tUSD\t\t- 2.345.678,90");
        stringList.add("2\t24/02/2013\t25/02/2013\t6011162125079\tLingmerths, RAMKVILLA ,SE\t7474\t\t9.876.543,21\tUSD\t\t8.765.432,10");

        String prefix = "CB";

        List<Data> dataList = CitibankChrome.parse(stringList, prefix);
        Data data1 = dataList.get(0);
        Data data2 = dataList.get(1);
        
        assertEquals("2013", data1.year);
        assertEquals("02", data1.month);
        assertEquals("27", data1.day);
        assertEquals(prefix, data1.prefix);
        assertEquals("Payment Thank You", data1.memo);
        assertEquals("inflow", data1.type);
        assertEquals("2345678.90", data1.value);

        assertEquals("2013", data2.year);
        assertEquals("02", data2.month);
        assertEquals("24", data2.day);
        assertEquals(prefix, data2.prefix);
        assertEquals("Lingmerths RAMKVILLA SE", data2.memo);
        assertEquals("outflow", data2.type);
        assertEquals("8765432.10", data2.value);
    }

}
