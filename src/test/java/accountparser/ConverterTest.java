package accountparser;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    private static final int FIRST  = 0;
    private static final int SECOND = 1;
    private static final int THIRD  = 2;

    @Test
    public final void testExecuteCitibank() throws IOException {
        Converter.execute(Bank.CITIBANK, "src/test/resources/InCitibank.txt", "src/test/resources/OutCitibank.csv",
                          "CB");
        final List<String> lines = File.read("src/test/resources/OutCitibank.csv");
        final List<String> lines1 = Lib.handleNull(lines);
        Assert.assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines1.get(FIRST));
        Assert.assertEquals("2013-02-27,,CB,Payment Thank You,,2345678.90", lines1.get(SECOND));
        Assert.assertEquals("2013-02-24,,CB,Lingmerths RAMKVILLA SE,8765432.10,", lines1.get(THIRD));
    }

    @Test
    public final void testExecuteSkandiabanken() throws IOException {
        Converter.execute(Bank.SKANDIABANKEN, "src/test/resources/InSkandiabanken.txt",
                          "src/test/resources/OutSkandiabanken.csv", "SB");
        final List<String> lines = File.read("src/test/resources/OutSkandiabanken.csv");
        final List<String> lines1 = Lib.handleNull(lines);
        Assert.assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines1.get(FIRST));
        Assert.assertEquals("2012-09-01,,SB,Kortköp CURA APOTEK SOLNA SOLNA,,2345678.90", lines1.get(SECOND));
        Assert.assertEquals("2012-08-31,,SB,Automatuttag 325675,8765432.10,", lines1.get(THIRD));
    }
}
