package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.Style;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionExcerciseStartDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    public OptionExcerciseStartDateViolationChecker() {
        super(TradeDatePresentChecker.class, OptionExcerciseStartDatePresentViolationChecker.class);
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getStyle() != Style.AMERICAN || entity.getType() != TradeType.OPTION) {
            return false;
        }
//        return !(entity.getExcerciseStartDate().isAfter(entity.getTradeDate()) &&
//                entity.getExcerciseStartDate().isBefore(entity.getExpiryDate()));
        return !entity.getExcerciseStartDate().isAfter(entity.getTradeDate()) ||
                !entity.getExcerciseStartDate().isBefore(entity.getExpiryDate());

    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.THE_EXCERCISESTARTDATE_HAS_TO_BE_AFTER_THE_TRADE_DATE_AND_BEFORE_THE_EXPIRY_DATE)
                .build();
    }
}
