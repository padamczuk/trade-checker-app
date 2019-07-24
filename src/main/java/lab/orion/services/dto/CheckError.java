package lab.orion.services.dto;

public class CheckError {
    public static final String VALUE_DATE_CANNOT_BE_BEFORE_TRADE_DATE = "value date cannot be before trade date";
    public static final String VALUE_DATE_CANNOT_BE_EMPTY = "value date cannot be empty";
    public static final String TRADE_DATE_CANNOT_BE_EMPTY = "trade date cannot be empty";
    public static final String EXPIRY_DATE_CANNOT_BE_EMPTY = "expiry date cannot be empty";
    public static final String DELIVERY_DATE_CANNOT_BE_EMPTY = "delivery date cannot be empty";
    public static final String PREMIUM_DATE_CANNOT_BE_EMPTY = "premium date cannot be empty";
    public static final String THE_EXCERCISESTARTDATE_CANNOT_BE_EMPTY = "the excercise start date cannot be empty";
    public static final String INVALID_SPOT_VALUE_DATE = "invaild spot value date, cannot be after trade date plus two working days";
    public static final String INVALID_FORWARD_VALUE_DATE = "invalid forward value date, cannot be after 3 friday of the quarter";
    public static final String UNSUPPORTED_COUNTERPARTY = "Unsupported counterparty";
    public static final String UNSUPPORTED_LEGAL_ENTITY = "Unsupported legal entity";
    public static final String VALUE_DATE_CANNOT_FALL_ON_WEEKEND_OR_NON_WORKING_DAY = "value date cannot fall on weekend or non-working day";
    public static final String THE_STYLE_CAN_BE_EITHER_AMERICAN_OR_EUROPEAN = "The style can be either American or European";
    public static final String THE_EXCERCISESTARTDATE_HAS_TO_BE_AFTER_THE_TRADE_DATE_AND_BEFORE_THE_EXPIRY_DATE = "the excerciseStartDate, which has to be after the trade date\n" +
            "but before the expiry date";
    public static final String EXPIRY_DATE_SHALL_BE_BEFORE_DELIVERY_DATE = "expiry date shall be before delivery date";
    public static final String PREMIUM_DATE_SHALL_BE_BEFORE_DELIVERY_DATE = "premium date shall be before delivery date";
    public static final String CURRENCY_PAIR_CANNOT_BE_EMPTY = "currency pair cannot be empty";
    public static final String INCORRECT_CURRENCY_RATE = "incorrect currency rate";
    private String message;

    public String getMessage() {
        return message;
    }

    public static CheckErrorBuilder builder(){
        return new CheckErrorBuilder();
    }

    public static final class CheckErrorBuilder {
        private String message;

        private CheckErrorBuilder() {
        }

        public CheckErrorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public CheckError build() {
            CheckError checkError = new CheckError();
            checkError.message = this.message;
            return checkError;
        }
    }

}
