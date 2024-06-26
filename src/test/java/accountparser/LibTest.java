package accountparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class LibTest {

  private static final String MEMO  = "Abc def ghi";
  private static final String VALUE = "1234567.89";

  @SuppressWarnings("static-method")
  @Test
  public final void testFormatMemo() {
    Assert.assertEquals(MEMO, Lib.formatMemo("Abc def ghi"));
    Assert.assertEquals(MEMO, Lib.formatMemo("Abc  def   ghi"));
    Assert.assertEquals(MEMO, Lib.formatMemo(",Abc,def,ghi,"));
    Assert.assertEquals(MEMO, Lib.formatMemo(", Abc,  def,   ghi    ,"));
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testFormatValue() {
    Assert.assertEquals(VALUE, Lib.formatValue("1234567,89"));
    Assert.assertEquals(VALUE, Lib.formatValue("1 234 567,89"));
    Assert.assertEquals(VALUE, Lib.formatValue("1.234.567,89"));
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testGetMatcherGroup() {
    final String group = "group";
    final String line = "string";
    final String regexp = "^(?<group>.+)$";
    final Matcher matcher = Regex.match(line, regexp);
    matcher.matches();
    Assert.assertEquals(line, Lib.getMatcherGroup(matcher, group));
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testIsBlank() {
    Assert.assertTrue(Lib.isBlank(" \n"));
    Assert.assertFalse(Lib.isBlank("abc\n"));
  }

  @SuppressWarnings("static-method")
  @Test
  public final void testPrintListData() {
    final List<String> stringList = new ArrayList<>();
    stringList.add("{year: 2013, month: 01, day: 12, prefix: SB, memo: Computer Store 1, type: debit, value: 1234567.98}");
    stringList.add("{year: 2014, month: 02, day: 13, prefix: CB, memo: Computer Store 2, type: credit, value: 9876543.21}");
    final TransactionData data1 = new TransactionData.Builder().setYear("2013")
                                                               .setMonth("01")
                                                               .setDay("12")
                                                               .setPrefix("SB")
                                                               .setMemo("Computer Store 1")
                                                               .setType("debit")
                                                               .setValue("1234567.98")
                                                               .build();
    final TransactionData data2 = new TransactionData.Builder().setYear("2014")
                                                               .setMonth("02")
                                                               .setDay("13")
                                                               .setPrefix("CB")
                                                               .setMemo("Computer Store 2")
                                                               .setType("credit")
                                                               .setValue("9876543.21")
                                                               .build();
    final List<TransactionData> dataList = new ArrayList<>();
    dataList.add(data1);
    dataList.add(data2);
    Assert.assertEquals(stringList, Lib.print(dataList));
  }
}
