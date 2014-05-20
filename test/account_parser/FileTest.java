package account_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import account_parser.File;

public class FileTest {

    @Test
    public final void testWriteRead() throws IOException {
        final List<String> lines = new ArrayList<>();
        lines.add("Henrik Sandstr�m");
        lines.add("Ulrika Sandstr�m");
        final String filePath = "test/files/FileWriteRead.txt";
        File.write(filePath, lines);
        final List<String> lines1 = File.read(filePath);
        if (lines1 == null) {
            throw new RuntimeException("'lines1' is null");
        }
        Assert.assertEquals("Henrik Sandstr�m", lines1.get(0));
        Assert.assertEquals("Ulrika Sandstr�m", lines1.get(1));
    }
}
