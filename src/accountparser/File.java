package accountparser;

import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;

import java.util.List;

class File {

    final static Charset ENCODING = StandardCharsets.ISO_8859_1;

    static List<String> read(String pathString) throws IOException {
        return Files.readAllLines(Paths.get(pathString), ENCODING);
    }

    static Path write(String pathString, List<String> lines) throws IOException {
        return Files.write(Paths.get(pathString), lines, ENCODING);
    }

}