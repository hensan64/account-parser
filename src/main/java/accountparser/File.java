package accountparser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;

final class File {

  @Nullable static final Charset ENCODING = StandardCharsets.UTF_8;

  private File() {
    Lib.instantiationNotAllowed();
  }

  @SuppressWarnings("null")
  static List<String> read(final String pathString) throws IOException {
    return Files.readAllLines(Paths.get(pathString), File.ENCODING);
  }

  static void write(final String pathString, final List<String> lines) throws IOException {
    if (File.ENCODING != null) {
      Files.write(Paths.get(pathString), lines, File.ENCODING);
    }
  }
}
