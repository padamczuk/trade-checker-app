package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionExpiryDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public OptionExpiryDateViolationChecker(){
        super(OptionExpiryDatePresentViolationChecker.class, OptionDeliveryDatePresentViolationChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getType() != TradeType.OPTION) {
            return false;
        }
        return !entity.getExpiryDate().isBefore(entity.getDeliveryDate());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.EXPIRY_DATE_SHALL_BE_BEFORE_DELIVERY_DATE)
                .build();
    }
}