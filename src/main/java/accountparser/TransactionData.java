package accountparser;

class TransactionData {

  // "12"
  private final String day;
  // "Descriptive Text"
  private final String memo;
  // "06"
  private final String month;
  // "SB"
  private final String prefix;
  // "inflow" | "outflow"
  private final String type;
  // "1234.65"
  private final String value;
  // "2011"
  private final String year;

  TransactionData(final Builder builder) {
    day = builder.getDay();
    memo = builder.getMemo();
    month = builder.getMonth();
    prefix = builder.getPrefix();
    type = builder.getType();
    value = builder.getValue();
    year = builder.getYear();
  }

  // For unit test purpose
  String getDataString() {
    return "{" + "year: " + year + ", " + "month: " + month + ", " + "day: " + day + ", "
           + "prefix: " + prefix + ", " + "memo: " + memo + ", " + "type: " + type + ", "
           + "value: " + value + "}";
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

  static class Builder {

    // "12"
    private String day    = "";
    // "Descriptive Text"
    private String memo   = "";
    // "06"
    private String month  = "";
    // "SB"
    private String prefix = "";
    // "inflow" | "outflow"
    private String type   = "";
    // "1234.65"
    private String value  = "";
    // "2011"
    private String year   = "";

    public TransactionData build() {
      return new TransactionData(this);
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
