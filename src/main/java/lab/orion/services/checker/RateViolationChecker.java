package lab.orion.services.checker;

import com.google.inject.Inject;
import lab.orion.clients.FixerClient;
import lab.orion.clients.dto.CurrencyRates;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class RateViolationChecker extends ViolationChecker<TradeCheckerItemDto, CheckError> {
    private FixerClient fixerClient;

    @Inject
    public RateViolationChecker(FixerClient fixerClient) {
        super(ValueDatePresentChecker.class, CurrencyPairPresentViolationChecker.class, WorkingDayValueDateViolationChecker.class);
        this.fixerClient = fixerClient;
    }

    @Override
    public boolean hasViolation(TradeCheckerItemDto entity) {
        CurrencyRates currencyRates = fixerClient.getCurrencyPairRate(entity.getValueDate(), entity.getCcyPair());
        return entity.getRate().compareTo(currencyRates.getRates().get(entity.getCcyPair().getTargetCurrency())) != 0;
    }

    @Override
    public CheckError getViolation(TradeCheckerItemDto entity) {
        return CheckError.builder()
                .withMessage(CheckError.INCORRECT_CURRENCY_RATE)
                .build();
    }
}
