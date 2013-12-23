package accountparser;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Test;

import accountparser.Bank;
import accountparser.Converter;
import accountparser.File;

public class ConverterTest {

    @Test
    public void testExecuteSkandiabanken() throws IOException {
        Converter.execute(Bank.SKANDIABANKEN, "test/files/InSkandiabanken.txt", "test/files/OutSkandiabanken.csv", "SB");
        
        List<String> lines = File.read("test/files/OutSkandiabanken.csv");
        
        assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines.get(0));
        assertEquals("2012-09-01,,SB,Kortköp CURA APOTEK SOLNA SOLNA,,2345678.90", lines.get(1));
        assertEquals("2012-08-31,,SB,Automatuttag 325675,8765432.10,", lines.get(2));
    }

    @Test
    public void testExecuteCitibank() throws IOException {
        Converter.execute(Bank.CITIBANK, "test/files/InCitibank.txt", "test/files/OutCitibank.csv", "CB");
        
        List<String> lines = File.read("test/files/OutCitibank.csv");
        
        assertEquals("Date,Payee,Category,Memo,Outflow,Inflow", lines.get(0));
        assertEquals("2013-02-27,,CB,Payment Thank You,,2345678.90", lines.get(1));
        assertEquals("2013-02-24,,CB,Lingmerths RAMKVILLA SE,8765432.10,", lines.get(2));
    }

}
