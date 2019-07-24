package lab.orion.services.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Currency;

public class CurrencyPairDeserializer extends JsonDeserializer<CurrencyPair> {
    public static final CurrencyPairDeserializer INSTANCE = new CurrencyPairDeserializer();
    @Override
    public CurrencyPair deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String value = jp.getText();
        if (value != null && value.length() == 6) {
            return CurrencyPair.of(Currency.getInstance(value.substring(0, 3)), Currency.getInstance(value.substring(3, 6)));
        }
        return null;
    }
}