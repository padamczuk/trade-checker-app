package lab.orion.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Optional;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TradeType {
    SPOT("Spot"), FORWARD("Forward"), OPTION("VanillaOption");
    private String value;
    TradeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //@JsonCreator
    public static TradeType fromValue(String value) {
        for (TradeType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
