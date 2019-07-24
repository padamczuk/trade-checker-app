package lab.orion.clients.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

public class CurrencyRates {
    private boolean success;
    private Long timestamp;
    private Currency base;
    private boolean historical;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    @JsonSerialize(keyUsing = MapSerializer.class)
    private Map<Currency, BigDecimal> rates;

    public boolean isHistorical() {
        return historical;
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Currency getBase() {
        return base;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<Currency, BigDecimal> getRates() {
        return rates;
    }

    public static CurrencyRatesBuilder builder() {
        return new CurrencyRatesBuilder();
    }
    public static final class CurrencyRatesBuilder {
        private boolean success;
        private Long timestamp;
        private Currency base;
        private boolean historical;
        private LocalDate date;
        private Map<Currency, BigDecimal> rates;

        private CurrencyRatesBuilder() {
        }


        public CurrencyRatesBuilder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public CurrencyRatesBuilder withTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CurrencyRatesBuilder withBase(Currency base) {
            this.base = base;
            return this;
        }

        public CurrencyRatesBuilder withHistorical(boolean historical) {
            this.historical = historical;
            return this;
        }

        public CurrencyRatesBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public CurrencyRatesBuilder withRates(Map<Currency, BigDecimal> rates) {
            this.rates = rates;
            return this;
        }

        public CurrencyRates build() {
            CurrencyRates currencyRates = new CurrencyRates();
            currencyRates.historical = this.historical;
            currencyRates.timestamp = this.timestamp;
            currencyRates.rates = this.rates;
            currencyRates.date = this.date;
            currencyRates.success = this.success;
            currencyRates.base = this.base;
            return currencyRates;
        }
    }
}
