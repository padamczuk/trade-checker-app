package lab.orion.services.dto;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class TradeCheckerItemStatusDto {
    private CheckStatus status;
    private List<CheckError> errors;

    public CheckStatus getStatus() {
        return status;
    }

    public List<CheckError> getErrors() {
        return errors;
    }

    public static TradeCheckerItemStatusDtoBuilder builder() {
        return new TradeCheckerItemStatusDtoBuilder();
    }

    public static final class TradeCheckerItemStatusDtoBuilder {
        private CheckStatus status;
        private List<CheckError> errors = Lists.newArrayList();

        private TradeCheckerItemStatusDtoBuilder() {
        }

        public TradeCheckerItemStatusDtoBuilder withError(CheckError error) {
            this.errors.add(error);
            return this;
        }

        public TradeCheckerItemStatusDtoBuilder withErrors(Collection<CheckError> error) {
            this.errors.addAll(error);
            return this;
        }

        public TradeCheckerItemStatusDto build() {
            TradeCheckerItemStatusDto tradeCheckerItemStatusDto = new TradeCheckerItemStatusDto();
            tradeCheckerItemStatusDto.errors = this.errors;
            if (errors.isEmpty()) {
                tradeCheckerItemStatusDto.status = CheckStatus.SUCCESS;
            } else {
                tradeCheckerItemStatusDto.status = CheckStatus.ERROR;
            }
            return tradeCheckerItemStatusDto;
        }
    }
}
