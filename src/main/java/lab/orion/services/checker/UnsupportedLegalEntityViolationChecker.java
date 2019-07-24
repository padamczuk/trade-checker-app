package lab.orion.services.checker;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class UnsupportedLegalEntityViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {
    private final ImmutableSet<String> validLegalEntities;

    @Inject
    public UnsupportedLegalEntityViolationChecker(@Named("valid.legalentities") String validLegalEntities) {
        this.validLegalEntities = ImmutableSet.copyOf(validLegalEntities.split(","));
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return !validLegalEntities.contains(entity.getLegalEntity());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.UNSUPPORTED_LEGAL_ENTITY)
                .build();
    }
}
