package accountparser;

import java.io.IOException;

public class _Main {
    public static final String IN_CITIBANK = "files/InCitibank.txt";
    public static final String IN_SKANDIABANKEN = "files/InSkandiabanken.txt";
    public static final String OUT_CITIBANK = "files/OutCitibank.csv";
    public static final String OUT_SKANDIABANKEN = "files/OutSkandiabanken.csv";
    public static final String PREFIX_CITIBANK = "CB";
    public static final String PREFIX_SKANDIABANKEN = "SB";

    public static void main(String[] args) throws IOException {
        if (args[0].equals(Bank.CITIBANK.name()))
            Converter.execute(Bank.CITIBANK, IN_CITIBANK, OUT_CITIBANK, PREFIX_CITIBANK);
        else if (args[0].equals(Bank.SKANDIABANKEN.name()))
            Converter.execute(Bank.SKANDIABANKEN, IN_SKANDIABANKEN, OUT_SKANDIABANKEN, PREFIX_SKANDIABANKEN);
        else
            throw (new Error("Unknown bank argument: " + args[0]));
    }
}