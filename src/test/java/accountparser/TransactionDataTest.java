package accountparser;

import org.junit.Assert;
import org.junit.Test;

public class TransactionDataTest {

  @SuppressWarnings("static-method")
  @Test
  public final void testPrint() {
    final TransactionData data = new TransactionData.Builder().setYear("2013")
                                                              .setMonth("01")
                                                              .setDay("12")
                                                              .setPrefix("SB")
                                                              .setMemo("Computer Store")
                                                              .setType("debit")
                                                              .setValue("1234567.98")
                                                              .build();
    Assert.assertEquals("{year: 2013, month: 01, day: 12, prefix: SB, memo: Computer Store, type: debit, value: 1234567.98}",
                        data.getDataString());
  }
}
