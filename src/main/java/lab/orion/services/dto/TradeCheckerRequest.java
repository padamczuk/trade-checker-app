package lab.orion.services.dto;

import java.util.List;

public class TradeCheckerRequest {
    private List<TradeCheckerItemDto> tradeItems;
    public List<TradeCheckerItemDto> getTradeItems() {
        return tradeItems;
    }

    public static TradeCheckerRequestBuilder builder() {
        return new TradeCheckerRequestBuilder();
    }

    public static final class TradeCheckerRequestBuilder {
        private List<TradeCheckerItemDto> tradeItems;

        private TradeCheckerRequestBuilder() {
        }


        public TradeCheckerRequestBuilder withTradeItems(List<TradeCheckerItemDto> tradeItems) {
            this.tradeItems = tradeItems;
            return this;
        }

        public TradeCheckerRequest build() {
            TradeCheckerRequest tradeCheckerRequest = new TradeCheckerRequest();
            tradeCheckerRequest.tradeItems = this.tradeItems;
            return tradeCheckerRequest;
        }
    }
}
