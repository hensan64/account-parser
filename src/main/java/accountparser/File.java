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

  @Nullable
  static List<String> read(final String pathString) throws IOException {
    final List<String> result;
    if (File.ENCODING == null) {
      result = null;
    } else {
      result = Files.readAllLines(Paths.get(pathString), File.ENCODING);
    }
    return result;
  }

  static void write(final String pathString, final List<String> lines) throws IOException {
    if (File.ENCODING != null) {
      Files.write(Paths.get(pathString), lines, File.ENCODING);
    }
  }
}
