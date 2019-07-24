package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.Style;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

public class OptionExcerciseStartDatePresentViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getStyle() != Style.AMERICAN || entity.getType() != TradeType.OPTION) {
            return false;
        }
        return entity.getExcerciseStartDate() == null;
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.THE_EXCERCISESTARTDATE_CANNOT_BE_EMPTY)
                .build();
    }
}
