package accountparser;

import java.io.IOException;

public final class Main {

  public static final String IN_CITIBANK          = "files/InCitibank.txt";
  public static final String IN_SKANDIABANKEN     = "files/InSkandiabanken.txt";
  public static final String OUT_CITIBANK         = "files/OutCitibank.csv";
  public static final String OUT_SKANDIABANKEN    = "files/OutSkandiabanken.csv";
  public static final String PREFIX_CITIBANK      = "CB";
  public static final String PREFIX_SKANDIABANKEN = "SB";

  private Main() {
    Lib.instantiationNotAllowed();
  }

  public static void main(final String[] args) throws IOException {
    if (args[0].equals(Bank.CITIBANK.name())) {
      Converter.execute(Bank.CITIBANK, Main.IN_CITIBANK, Main.OUT_CITIBANK, Main.PREFIX_CITIBANK);
    } else if (args[0].equals(Bank.SKANDIABANKEN.name())) {
      Converter.execute(Bank.SKANDIABANKEN, Main.IN_SKANDIABANKEN, Main.OUT_SKANDIABANKEN, Main.PREFIX_SKANDIABANKEN);
    } else {
      throw new AccountParserException("Unknown bank argument: " + args[0]);
    }
  }
}
