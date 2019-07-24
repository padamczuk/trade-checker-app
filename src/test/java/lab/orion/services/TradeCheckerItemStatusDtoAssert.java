package lab.orion.services;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.CheckStatus;
import lab.orion.services.dto.TradeCheckerItemStatusDto;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TradeCheckerItemStatusDtoAssert extends AbstractAssert<TradeCheckerItemStatusDtoAssert, TradeCheckerItemStatusDto> {
    public TradeCheckerItemStatusDtoAssert(TradeCheckerItemStatusDto tradeCheckerItemStatusDto) {
        super(tradeCheckerItemStatusDto, TradeCheckerItemStatusDtoAssert.class);
    }

    public static TradeCheckerItemStatusDtoAssert assertThat(TradeCheckerItemStatusDto actual) {
        return new TradeCheckerItemStatusDtoAssert(actual);
    }

    public TradeCheckerItemStatusDtoAssert hasStatus(CheckStatus checkStatus) {
        Assertions.assertThat(actual.getStatus()).isEqualTo(checkStatus);
        return this;
    }

    public TradeCheckerItemStatusDtoAssert hasNoErrors() {
        Assertions.assertThat(actual.getErrors()).isEmpty();
        return this;
    }

    public TradeCheckerItemStatusDtoAssert hasExactlyErrors(CheckError ... errors) {
        Assertions.assertThat(actual.getErrors()).usingFieldByFieldElementComparator().containsExactly(errors);
        return this;
    }
}
