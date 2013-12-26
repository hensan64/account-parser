package accountparser;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

import accountparser.File;

public class FileTest {

    @Test
    public void testWriteRead() throws IOException {
        List<String> lines = new ArrayList<String>();
        lines.add("Henrik Sandstr�m");
        lines.add("Ulrika Sandstr�m");
        
        String filePath = "test/files/FileWriteRead.txt";
        File.write(filePath, lines);
        
        List<String> lines1 = File.read(filePath);

        assertEquals("Henrik Sandstr�m", lines1.get(0));
        assertEquals("Ulrika Sandstr�m", lines1.get(1));
    }

}
