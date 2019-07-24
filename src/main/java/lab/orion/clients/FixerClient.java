package lab.orion.clients;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
//import com.hp.cache4guice.Cached;
import lab.orion.clients.dto.CurrencyRates;
import lab.orion.services.dto.CurrencyPair;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FixerClient {

    private static final String REST_URI = "http://data.fixer.io/api";
    private ClientConfig clientConfig;
    private Client client;

    @Inject
    public FixerClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        client = ClientBuilder.newBuilder()
                .withConfig(clientConfig)
                .build();
    }
//    @Cached
    public CurrencyRates getCurrencyPairRate(LocalDate localDate, CurrencyPair currencyPair) {
        Set<Currency> currencies = new HashSet<Currency>();
        currencies.add(currencyPair.getSourceCurrency());
        currencies.add(currencyPair.getTargetCurrency());
        CurrencyRates currencyRates = getCurrencyRates(localDate, currencies);
        BigDecimal euroToSourceRate = currencyRates.getRates().get(currencyPair.getSourceCurrency());
        BigDecimal sourceToEuroRate = BigDecimal.ONE.divide(euroToSourceRate);
        BigDecimal euroToTargetRate = currencyRates.getRates().get(currencyPair.getTargetCurrency());
        return CurrencyRates.builder()
                .withBase(currencyPair.getSourceCurrency())
                .withDate(currencyRates.getDate())
                .withSuccess(currencyRates.isSuccess())
                .withHistorical(true).withTimestamp(currencyRates.getTimestamp())
                .withRates(ImmutableMap.<Currency, BigDecimal>builder()
                        .put(currencyPair.getTargetCurrency(), sourceToEuroRate.multiply(euroToTargetRate))
                        .build())
                .build();
    }

    public CurrencyRates getCurrencyRates(LocalDate localDate, Set<Currency> currencies) {
        List<String> currencyCodes = currencies
                .stream()
                .map(currency -> currency.getCurrencyCode())
                .collect(Collectors.toList());
        String joinedCodes = String.join(",", currencyCodes);
        return client
                .target(REST_URI)
                .path(DateTimeFormatter.ISO_LOCAL_DATE.format(localDate))
                .queryParam("symbols", joinedCodes)
                .queryParam("access_key", "46583557500ccd7f9e7e7e0e799086c0")
                .queryParam("format","1")
                .request(MediaType.APPLICATION_JSON)
                .get(CurrencyRates.class);
    }
}
