package lab.orion.services.checker;

import lab.orion.services.LastPossibleValueDateCalculator;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

import java.time.LocalDate;

/* Date range checker*/
public class ForwardValueDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public ForwardValueDateViolationChecker() {
        super(ValueDatePresentChecker.class, TradeDatePresentChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getType() != TradeType.FORWARD) {
            return false;
        }
        LocalDate lastDate = LastPossibleValueDateCalculator.calculateForwardValueDate(entity.getTradeDate());
        return entity.getValueDate().isAfter(lastDate);
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.INVALID_FORWARD_VALUE_DATE)
                .build();
    }
}
