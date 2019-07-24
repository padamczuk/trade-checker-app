package lab.orion.services.checker;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class CustomerViolationChecker  extends ViolationChecker<TradeCheckerItemDto, CheckError> {

    private final ImmutableSet<String> validCustomers;

    @Inject
    public CustomerViolationChecker(@Named("valid.customers") String validCustomers) {
        this.validCustomers = ImmutableSet.copyOf(validCustomers.split(","));
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        return !validCustomers.contains(entity.getCustomer());
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.UNSUPPORTED_COUNTERPARTY)
                .build();
    }
}
