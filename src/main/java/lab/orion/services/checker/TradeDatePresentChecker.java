package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class TradeDatePresentChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return entity.getTradeDate() == null;
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.TRADE_DATE_CANNOT_BE_EMPTY)
                .build();
    }
}
