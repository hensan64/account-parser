package account_parser;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public final void testExecuteCitibank() throws IOException {
        Converter.execute(Bank.CITIBANK, "test/files/InCitibank.txt", "test/files/OutCitibank.csv", "CB");
        final List<String> lines = File.read("test/files/OutCitibank.csv");
        final List<String> lines1 = Lib.handleNull(lines);
        Assert.assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines1.get(0));
        Assert.assertEquals("2013-02-27,,CB,Payment Thank You,,2345678.90", lines1.get(1));
        Assert.assertEquals("2013-02-24,,CB,Lingmerths RAMKVILLA SE,8765432.10,", lines1.get(2));
    }

    @Test
    public final void testExecuteSkandiabanken() throws IOException {
        Converter.execute(Bank.SKANDIABANKEN, "test/files/InSkandiabanken.txt", "test/files/OutSkandiabanken.csv", "SB");
        final List<String> lines = File.read("test/files/OutSkandiabanken.csv");
        final List<String> lines1 = Lib.handleNull(lines);
        Assert.assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines1.get(0));
        Assert.assertEquals("2012-09-01,,SB,Kortköp CURA APOTEK SOLNA SOLNA,,2345678.90", lines1.get(1));
        Assert.assertEquals("2012-08-31,,SB,Automatuttag 325675,8765432.10,", lines1.get(2));
    }
}
