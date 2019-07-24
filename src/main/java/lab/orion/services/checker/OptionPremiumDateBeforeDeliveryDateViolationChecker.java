package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionPremiumDateBeforeDeliveryDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public OptionPremiumDateBeforeDeliveryDateViolationChecker(){
        super(OptionPremiumDatePresentViolationChecker.class, OptionDeliveryDatePresentViolationChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getType() != TradeType.OPTION) {
            return false;
        }
        return !entity.getPremiumDate().isBefore(entity.getDeliveryDate());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.PREMIUM_DATE_SHALL_BE_BEFORE_DELIVERY_DATE)
                .build();
    }
}
