package lab.orion.services.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeCheckerItemDto {
    private String customer;
    private CurrencyPair ccyPair;
    private TradeType type;
    private Style style;
    private Direction direction;
    private Strategy strategy;
    private LocalDate tradeDate;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal rate;
    private LocalDate deliveryDate;
    private LocalDate expiryDate;
    private LocalDate excerciseStartDate;
    private Currency payCcy;
    private String premium;
    private Currency premiumCcy;
    private String premiumType;
    private LocalDate premiumDate;
    private LocalDate valueDate;
    private String legalEntity;
    private String trader;

    public Style getStyle() {
        return style;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public Currency getPayCcy() {
        return payCcy;
    }

    public String getPremium() {
        return premium;
    }

    public Currency getPremiumCcy() {
        return premiumCcy;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public LocalDate getPremiumDate() {
        return premiumDate;
    }

    public String getCustomer() {
        return customer;
    }

    public CurrencyPair getCcyPair() {
        return ccyPair;
    }

    public TradeType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public BigDecimal getAmount1() {
        return amount1;
    }

    public BigDecimal getAmount2() {
        return amount2;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public String getTrader() {
        return trader;
    }

    public static TradeCheckerItemDtoBuilder builder() {
        return new TradeCheckerItemDtoBuilder();
    }

    public static final class TradeCheckerItemDtoBuilder {
        private String customer;
        private CurrencyPair ccyPair;
        private TradeType type;
        private Style style;
        private Direction direction;
        private Strategy strategy;
        private LocalDate tradeDate;
        private BigDecimal amount1;
        private BigDecimal amount2;
        private BigDecimal rate;
        private LocalDate deliveryDate;
        private LocalDate expiryDate;
        private LocalDate excerciseStartDate;
        private Currency payCcy;
        private String premium;
        private Currency premiumCcy;
        private String premiumType;
        private LocalDate premiumDate;
        private LocalDate valueDate;
        private String legalEntity;
        private String trader;

        private TradeCheckerItemDtoBuilder() {
        }

        public TradeCheckerItemDtoBuilder withCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public TradeCheckerItemDtoBuilder withCcyPair(CurrencyPair ccyPair) {
            this.ccyPair = ccyPair;
            return this;
        }

        public TradeCheckerItemDtoBuilder withType(TradeType type) {
            this.type = type;
            return this;
        }

        public TradeCheckerItemDtoBuilder withStyle(Style style) {
            this.style = style;
            return this;
        }

        public TradeCheckerItemDtoBuilder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public TradeCheckerItemDtoBuilder withStrategy(Strategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public TradeCheckerItemDtoBuilder withTradeDate(LocalDate tradeDate) {
            this.tradeDate = tradeDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withAmount1(BigDecimal amount1) {
            this.amount1 = amount1;
            return this;
        }

        public TradeCheckerItemDtoBuilder withAmount2(BigDecimal amount2) {
            this.amount2 = amount2;
            return this;
        }

        public TradeCheckerItemDtoBuilder withRate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withDeliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withExcerciseStartDate(LocalDate excerciseStartDate) {
            this.excerciseStartDate = excerciseStartDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withPayCcy(Currency payCcy) {
            this.payCcy = payCcy;
            return this;
        }

        public TradeCheckerItemDtoBuilder withPremium(String premium) {
            this.premium = premium;
            return this;
        }

        public TradeCheckerItemDtoBuilder withPremiumCcy(Currency premiumCcy) {
            this.premiumCcy = premiumCcy;
            return this;
        }

        public TradeCheckerItemDtoBuilder withPremiumType(String premiumType) {
            this.premiumType = premiumType;
            return this;
        }

        public TradeCheckerItemDtoBuilder withPremiumDate(LocalDate premiumDate) {
            this.premiumDate = premiumDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withValueDate(LocalDate valueDate) {
            this.valueDate = valueDate;
            return this;
        }

        public TradeCheckerItemDtoBuilder withLegalEntity(String legalEntity) {
            this.legalEntity = legalEntity;
            return this;
        }

        public TradeCheckerItemDtoBuilder withTrader(String trader) {
            this.trader = trader;
            return this;
        }

        public TradeCheckerItemDto build() {
            TradeCheckerItemDto tradeCheckerItemDto = new TradeCheckerItemDto();
            tradeCheckerItemDto.style = this.style;
            tradeCheckerItemDto.deliveryDate = this.deliveryDate;
            tradeCheckerItemDto.customer = this.customer;
            tradeCheckerItemDto.amount2 = this.amount2;
            tradeCheckerItemDto.valueDate = this.valueDate;
            tradeCheckerItemDto.tradeDate = this.tradeDate;
            tradeCheckerItemDto.excerciseStartDate = this.excerciseStartDate;
            tradeCheckerItemDto.type = this.type;
            tradeCheckerItemDto.expiryDate = this.expiryDate;
            tradeCheckerItemDto.trader = this.trader;
            tradeCheckerItemDto.direction = this.direction;
            tradeCheckerItemDto.premiumCcy = this.premiumCcy;
            tradeCheckerItemDto.premiumDate = this.premiumDate;
            tradeCheckerItemDto.legalEntity = this.legalEntity;
            tradeCheckerItemDto.ccyPair = this.ccyPair;
            tradeCheckerItemDto.payCcy = this.payCcy;
            tradeCheckerItemDto.rate = this.rate;
            tradeCheckerItemDto.premium = this.premium;
            tradeCheckerItemDto.strategy = this.strategy;
            tradeCheckerItemDto.premiumType = this.premiumType;
            tradeCheckerItemDto.amount1 = this.amount1;
            return tradeCheckerItemDto;
        }
    }
}
