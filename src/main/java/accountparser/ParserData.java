package accountparser;

class ParserData {

    // ".+?" means any one or more character in a non-greedy (?) way
    // The regex consequently don't swallow the first 'tab'
    public static final String CITIBANK_CHROME_REGEX      = "^.+?\\t(?<day>\\d{2})\\/(?<month>\\d{2})\\/(?<year>\\d{4})(?:\\t.*?){2}\\t(?<memo>.+?)(?:\\t.*?){5}\\t(?<sign>-?)(?<value>.+?)$";
    public static final String SKANDIABANKEN_CHROME_REGEX = "^(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})\\t(?<memo>.+?)\\t(?<sign>-?)(?<value>.+?)\\t.*$";

    private final boolean      hasInvertedSign;
    private final String       regex;

    ParserData(final Builder builder) {
        hasInvertedSign = builder.getHasInvertedSign();
        regex = builder.getRegex();
    }

    // For unit test purpose
    String getDataString() {
        return "{" + "hasInvertedSign: " + hasInvertedSign + ", " + "regex: " + regex + "}";
    }

    boolean getHasInvertedSign() {
        return hasInvertedSign;
    }

    String getRegex() {
        return regex;
    }

    static class Builder {

        private boolean hasInvertedSign = false;
        private String  regex           = "";

        public ParserData build() {
            return new ParserData(this);
        }

        public boolean getHasInvertedSign() {
            return hasInvertedSign;
        }

        public String getRegex() {
            return regex;
        }

        public Builder setHasInvertedSign(final boolean hasInvertedSign) {
            this.hasInvertedSign = hasInvertedSign;
            return this;
        }

        public Builder setRegex(final String regex) {
            this.regex = regex;
            return this;
        }
    }
}
