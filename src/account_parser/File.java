package account_parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;

final class File {

    @Nullable static final Charset ENCODING = StandardCharsets.ISO_8859_1;

    private File() {
        Lib.instantiationNotAllowed();
    }

    static List<String> read(final String pathString) throws IOException {
        List<String> returnValue = Files.readAllLines(Paths.get(pathString), File.ENCODING);
        if (returnValue == null) {
            returnValue = new ArrayList<>();
        }
        return returnValue;

    }

    static void write(final String pathString, final List<String> lines) throws IOException {
        Files.write(Paths.get(pathString), lines, File.ENCODING);
    }
}
