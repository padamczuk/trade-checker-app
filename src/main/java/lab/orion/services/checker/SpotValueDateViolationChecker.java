package lab.orion.services.checker;

import lab.orion.services.LastPossibleValueDateCalculator;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

import java.time.LocalDate;

/* Date range checker*/
public class SpotValueDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public SpotValueDateViolationChecker() {
        super(ValueDatePresentChecker.class, TradeDatePresentChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getType() != TradeType.SPOT) {
            return false;
        }
        LocalDate lastDate = LastPossibleValueDateCalculator.calculateSpotValueDate(entity.getTradeDate());
        return entity.getValueDate().isAfter(lastDate);
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.INVALID_SPOT_VALUE_DATE)
                .build();
    }
}
