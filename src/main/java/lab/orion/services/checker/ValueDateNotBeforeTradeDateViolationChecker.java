package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class ValueDateNotBeforeTradeDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public ValueDateNotBeforeTradeDateViolationChecker() {
        super(ValueDatePresentChecker.class, TradeDatePresentChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return entity.getValueDate().isBefore(entity.getTradeDate());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.VALUE_DATE_CANNOT_BE_BEFORE_TRADE_DATE)
                .build();
    }
}
