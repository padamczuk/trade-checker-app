package lab.orion.services.checker;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.Style;
import lab.orion.services.dto.TradeCheckerItemDto;
import lab.orion.services.dto.TradeType;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/* Enumeration checker */
public class OptionStyleViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {
    public final Set<Style> validOptionStyles;

    @Inject
    public OptionStyleViolationChecker(@Named("valid.optionstyles") String validOptionStyles) {
        this.validOptionStyles = Arrays.stream(validOptionStyles.split(","))
                .map(name -> Style.valueOf(name))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        if (entity.getType() != TradeType.OPTION) {
            return false;
        }

        return !validOptionStyles.contains(entity.getStyle());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.THE_STYLE_CAN_BE_EITHER_AMERICAN_OR_EUROPEAN)
                .build();
    }
}
