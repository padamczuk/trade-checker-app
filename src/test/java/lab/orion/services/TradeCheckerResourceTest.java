package lab.orion.services;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import lab.orion.services.dto.*;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@ExtendWith(GuiceExtension.class)
@IncludeModule({TestApplicationModule.class})
class TradeCheckerResourceTest {
    @Inject
    public TradeCheckerResource resource;

    @Test
    void shouldFindNoErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
            .withTradeItems(Lists.newArrayList(
                TradeCheckerItemDto.builder()
                    .withCustomer("PLUTO1")
                    .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                    .withType(TradeType.SPOT)
                    .withDirection(Direction.BUY)
                    .withTradeDate(LocalDate.parse("2016-07-11"))
                    .withAmount1(new BigDecimal("1000000.00"))
                    .withAmount2(new BigDecimal("1109940.00"))
                    .withRate(new BigDecimal("1.10994"))
                    .withValueDate(LocalDate.parse("2016-07-13"))
                    .withLegalEntity("CS Zurich")
                    .withTrader("Johan Bamfiddler")
                .build()))
            .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasNoErrors()
                .hasStatus(CheckStatus.SUCCESS);
    }
    @Test
    void shouldFindMissingCurrencyPairErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                //.withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.CURRENCY_PAIR_CANNOT_BE_EMPTY).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindIncorrectCustomerErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO12")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.UNSUPPORTED_COUNTERPARTY).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindForwardValueDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.FORWARD)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1118195.00"))
                                .withRate(new BigDecimal("1.118195"))
                                .withValueDate(LocalDate.parse("2016-09-19"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.INVALID_FORWARD_VALUE_DATE).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindUnsupportedLegalEntityErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS London")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.UNSUPPORTED_LEGAL_ENTITY).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindMissingExcerciseStartDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")

                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-12"))
                                .withStyle(Style.AMERICAN)

                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.THE_EXCERCISESTARTDATE_CANNOT_BE_EMPTY).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindExcerciseStartDateNotAfterTradeDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-10"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")

                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-12"))
                                .withExcerciseStartDate(LocalDate.parse("2016-07-10"))
                                .withStyle(Style.AMERICAN)
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.THE_EXCERCISESTARTDATE_HAS_TO_BE_AFTER_THE_TRADE_DATE_AND_BEFORE_THE_EXPIRY_DATE).build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindExcerciseStartDateNotBeforeExpiryDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-10"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-12"))
                                .withExcerciseStartDate(LocalDate.parse("2016-07-12"))
                                .withStyle(Style.AMERICAN)
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder().withMessage(CheckError.THE_EXCERCISESTARTDATE_HAS_TO_BE_AFTER_THE_TRADE_DATE_AND_BEFORE_THE_EXPIRY_DATE).build())
                .hasStatus(CheckStatus.ERROR);
    }

    @Test
    void shouldFindPremiumDateNotBeforeDeliveryDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-10"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-13"))
                                .withExcerciseStartDate(LocalDate.parse("2016-07-11"))
                                .withStyle(Style.AMERICAN)
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.PREMIUM_DATE_SHALL_BE_BEFORE_DELIVERY_DATE)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }

    @Test
    void shouldFindStyleNotAmericanOrEuropeanErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-10"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.10994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-12"))
                                .withExcerciseStartDate(LocalDate.parse("2016-07-11"))
                                .withStyle(Style.ASIAN)
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.THE_STYLE_CAN_BE_EITHER_AMERICAN_OR_EUROPEAN)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }

    @Test
    void shouldFindIncorrectRateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.OPTION)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-10"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1109940.00"))
                                .withRate(new BigDecimal("1.01994"))
                                .withValueDate(LocalDate.parse("2016-07-13"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .withDeliveryDate(LocalDate.parse("2016-07-13"))
                                .withExpiryDate(LocalDate.parse("2016-07-12"))
                                .withPremiumDate(LocalDate.parse("2016-07-12"))
                                .withExcerciseStartDate(LocalDate.parse("2016-07-11"))
                                .withStyle(Style.EUROPEAN)
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.INCORRECT_CURRENCY_RATE)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }

    @Test
    void shouldFindIncorrectSpotValueDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1110926.00"))
                                .withRate(new BigDecimal("1.110926"))
                                .withValueDate(LocalDate.parse("2016-07-14"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.INVALID_SPOT_VALUE_DATE)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindNotPresentValueDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-11"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1110926.00"))
                                .withRate(new BigDecimal("1.110926"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.VALUE_DATE_CANNOT_BE_EMPTY)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }

    @Test
    void shouldFindNotPresentTradeDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1110926.00"))
                                .withRate(new BigDecimal("1.110926"))
                                .withValueDate(LocalDate.parse("2016-07-14"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.TRADE_DATE_CANNOT_BE_EMPTY)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindValueDateBeforeTradeDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-15"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1110926.00"))
                                .withRate(new BigDecimal("1.110926"))
                                .withValueDate(LocalDate.parse("2016-07-14"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.VALUE_DATE_CANNOT_BE_BEFORE_TRADE_DATE)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }
    @Test
    void shouldFindNonworkingDayValueDateErrorInTrade() {
        //when
        Response response = resource.check(TradeCheckerRequest.builder()
                .withTradeItems(Lists.newArrayList(
                        TradeCheckerItemDto.builder()
                                .withCustomer("PLUTO1")
                                .withCcyPair(CurrencyPair.of(Currency.getInstance("EUR"), Currency.getInstance("USD")))
                                .withType(TradeType.SPOT)
                                .withDirection(Direction.BUY)
                                .withTradeDate(LocalDate.parse("2016-07-15"))
                                .withAmount1(new BigDecimal("1000000.00"))
                                .withAmount2(new BigDecimal("1110926.00"))
                                .withRate(new BigDecimal("1.110926"))
                                .withValueDate(LocalDate.parse("2016-07-16"))
                                .withLegalEntity("CS Zurich")
                                .withTrader("Johan Bamfiddler")
                                .build()))
                .build());
        TradeCheckerResponse result = (TradeCheckerResponse)response.getEntity();
        //then
        TradeCheckerItemStatusDtoAssert.assertThat(result.getTradeCheckerItemStatuses().get(0))
                .hasExactlyErrors(CheckError.builder()
                        .withMessage(CheckError.VALUE_DATE_CANNOT_FALL_ON_WEEKEND_OR_NON_WORKING_DAY)
                        .build())
                .hasStatus(CheckStatus.ERROR);
    }
    /*
    WorkingDayValueDateViolationChecker
    */
}
