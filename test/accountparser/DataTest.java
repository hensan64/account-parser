package accountparser;

import static org.junit.Assert.*;

import org.junit.Test;

import accountparser.Data;

public class DataTest {

    @Test
    public void testPrint() {
        Data data = new Data();
        
        data.year   = "2013";
        data.month  = "01";
        data.day    = "12";
        data.prefix = "SB";
        data.memo   = "Computer Store";
        data.type   = "debit";
        data.value  = "1234567.98";

        assertEquals("{year: 2013, month: 01, day: 12, prefix: SB, memo: Computer Store, type: debit, value: 1234567.98}", data.print());
    }

}
