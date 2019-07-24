package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionPremiumDatePresentViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return entity.getType() == TradeType.OPTION && entity.getPremiumDate() == null;
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.PREMIUM_DATE_CANNOT_BE_EMPTY)
                .build();
    }
}
