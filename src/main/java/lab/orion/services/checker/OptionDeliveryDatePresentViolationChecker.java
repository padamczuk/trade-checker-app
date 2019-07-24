package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionDeliveryDatePresentViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return entity.getType() == TradeType.OPTION && entity.getDeliveryDate() == null;
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.DELIVERY_DATE_CANNOT_BE_EMPTY)
                .build();
    }
}
