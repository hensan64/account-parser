package accountparser;

import org.junit.Assert;
import org.junit.Test;

public class DataTest {

    @Test
    public final void testPrint() {
        final Data data = new Data.Builder().setYear("2013")
                                            .setMonth("01")
                                            .setDay("12")
                                            .setPrefix("SB")
                                            .setMemo("Computer Store")
                                            .setType("debit")
                                            .setValue("1234567.98")
                                            .build();
        Assert.assertEquals("{year: 2013, month: 01, day: 12, prefix: SB, memo: Computer Store, type: debit, value: 1234567.98}",
                            data.print());
    }
}
