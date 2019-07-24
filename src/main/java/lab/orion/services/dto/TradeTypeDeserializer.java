package lab.orion.services.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class TradeTypeDeserializer extends JsonDeserializer<TradeType> {
    public static final TradeTypeDeserializer INSTANCE = new TradeTypeDeserializer();
    @Override
    public TradeType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String value = jp.getText();
        return TradeType.fromValue(value);
    }
}
