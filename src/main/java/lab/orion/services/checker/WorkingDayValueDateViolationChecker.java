package lab.orion.services.checker;

import com.google.inject.Inject;
import lab.orion.services.WorkingDaysService;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class WorkingDayValueDateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {
    private WorkingDaysService workingDaysService;

    @Inject
    public WorkingDayValueDateViolationChecker(WorkingDaysService workingDaysService) {
        super(ValueDatePresentChecker.class);
        this.workingDaysService = workingDaysService;
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return !workingDaysService.isWorkingDay(entity.getValueDate());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.VALUE_DATE_CANNOT_FALL_ON_WEEKEND_OR_NON_WORKING_DAY)
                .build();
    }
}
