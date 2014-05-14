package accountparser;

class Data {

    private final String day;   // "12"
    private final String memo;  // "Descriptive Text"
    private final String month; // "06"
    private final String prefix; // "SB"
    private final String type;  // "inflow" | "outflow"
    private final String value; // "1234.65"
    private final String year;  // "2011"

    Data(final Builder builder) {
        day = builder.getDay();
        memo = builder.getMemo();
        month = builder.getMonth();
        prefix = builder.getPrefix();
        type = builder.getType();
        value = builder.getValue();
        year = builder.getYear();
    }

    String getDay() {
        return day;
    }

    String getMemo() {
        return memo;
    }

    String getMonth() {
        return month;
    }

    String getPrefix() {
        return prefix;
    }

    String getType() {
        return type;
    }

    String getValue() {
        return value;
    }

    final String getYear() {
        return year;
    }

    String print() {
        final String dataString = "{" + "year: " + year + ", " + "month: " + month + ", " + "day: " + day + ", "
                                  + "prefix: " + prefix + ", " + "memo: " + memo + ", " + "type: " + type + ", "
                                  + "value: " + value + "}";
        Lib.print(dataString); // The real purpose
        return dataString; // Return a data string for unit test purpose
    }

    static class Builder {

        private String day    = ""; // "12"
        private String memo   = ""; // "Descriptive Text"
        private String month  = ""; // "06"
        private String prefix = ""; // "SB"
        private String type   = ""; // "inflow" | "outflow"
        private String value  = ""; // "1234.65"
        private String year   = ""; // "2011"

        public Data build() {
            return new Data(this);
        }

        public String getDay() {
            return day;
        }

        public String getMemo() {
            return memo;
        }

        public String getMonth() {
            return month;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        public String getYear() {
            return year;
        }

        public Builder setDay(final String day) {
            this.day = day;
            return this;
        }

        public Builder setMemo(final String memo) {
            this.memo = memo;
            return this;
        }

        public Builder setMonth(final String month) {
            this.month = month;
            return this;
        }

        public Builder setPrefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder setType(final String type) {
            this.type = type;
            return this;
        }

        public Builder setValue(final String value) {
            this.value = value;
            return this;
        }

        public Builder setYear(final String year) {
            this.year = year;
            return this;
        }
    }
}
