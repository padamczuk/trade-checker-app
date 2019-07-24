package lab.orion.web;

import com.fasterxml.jackson.databind.module.SimpleModule;
import lab.orion.services.dto.CurrencyPair;
import lab.orion.services.dto.CurrencyPairDeserializer;
import lab.orion.services.dto.TradeType;
import lab.orion.services.dto.TradeTypeDeserializer;

public class TypesModule extends SimpleModule {
    public TypesModule() {
        addDeserializer(TradeType.class, TradeTypeDeserializer.INSTANCE);
        addDeserializer(CurrencyPair.class, CurrencyPairDeserializer.INSTANCE);
    }
}
