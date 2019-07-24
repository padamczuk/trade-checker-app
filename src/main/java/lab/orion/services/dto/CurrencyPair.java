package lab.orion.services.dto;

import java.util.Currency;

public class CurrencyPair {
    private Currency sourceCurrency;
    private Currency targetCurrency;

    private CurrencyPair(Currency sourceCurrency, Currency targetCurrency) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public static CurrencyPair of(Currency sourceCurrency, Currency targetCurrency) {
        return new CurrencyPair(sourceCurrency, targetCurrency);
    }
}
