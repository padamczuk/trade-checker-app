package lab.orion.clients.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.time.LocalDate;

public class HolidayLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        return LocalDate.of(
                (Integer)((IntNode)node.get("year")).numberValue(),
                (Integer)((IntNode)node.get("month")).numberValue(),
                (Integer)((IntNode)node.get("day")).numberValue());
    }
}
