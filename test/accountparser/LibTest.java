package accountparser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import accountparser.Data;
import accountparser.Lib;

public class LibTest {

    @Test
    public void testIsBlank() {
       assertTrue(Lib.isBlank(" \n"));
       assertFalse(Lib.isBlank("abc\n"));
    }

    @Test
    public void testFormatMemo() {
        assertEquals("Abc def ghi", Lib.formatMemo("Abc def ghi"));
        assertEquals("Abc def ghi", Lib.formatMemo("Abc  def   ghi"));
        assertEquals("Abc def ghi", Lib.formatMemo(",Abc,def,ghi,"));
        assertEquals("Abc def ghi", Lib.formatMemo(", Abc,  def,   ghi    ,"));
   }

    @Test
    public void testFormatValue() {
        assertEquals("1234567.89", Lib.formatValue("1234567,89"));
        assertEquals("1234567.89", Lib.formatValue("1 234 567,89"));
        assertEquals("1234567.89", Lib.formatValue("1.234.567,89"));
    }

    @Test
    public void testPrintListData() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("{year: 2013, month: 01, day: 12, prefix: SB, memo: Computer Store 1, type: debit, value: 1234567.98}");
        stringList.add("{year: 2014, month: 02, day: 13, prefix: CB, memo: Computer Store 2, type: credit, value: 9876543.21}");

        Data data1 = new Data();        
        data1.year   = "2013";
        data1.month  = "01";
        data1.day    = "12";
        data1.prefix = "SB";
        data1.memo   = "Computer Store 1";
        data1.type   = "debit";
        data1.value  = "1234567.98";

        Data data2 = new Data();        
        data2.year   = "2014";
        data2.month  = "02";
        data2.day    = "13";
        data2.prefix = "CB";
        data2.memo   = "Computer Store 2";
        data2.type   = "credit";
        data2.value  = "9876543.21";
        
        List<Data> dataList = new ArrayList<Data>();
        dataList.add(data1);
        dataList.add(data2);
        
        assertEquals(stringList, Lib.print(dataList));
    }

    @Test
    public void testPrintObject() {
        assertEquals("Object 1", Lib.print("Object 1"));
        assertEquals("123", Lib.print(123));
        assertEquals("12.3", Lib.print(12.3));
    }

}
