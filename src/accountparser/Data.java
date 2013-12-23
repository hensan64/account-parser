package accountparser;

class Data {

    String year,    // "2011"
            month,  // "06"
            day,    // "12"
            prefix, // "SB"
            memo,   // "Descriptive Text"
            type,   // "inflow" | "outflow"
            value;  // "1234.65"

    // Return a data string for unit test purpose
    String print() {
        String dataString = "{" + "year: " + year + ", " + "month: " + month
                + ", " + "day: " + day + ", " + "prefix: " + prefix + ", "
                + "memo: " + memo + ", " + "type: " + type + ", " + "value: "
                + value + "}";
        Lib.print(dataString); // The real purpose

        return dataString;
    }

}