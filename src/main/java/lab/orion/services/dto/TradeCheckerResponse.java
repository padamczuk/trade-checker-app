package lab.orion.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TradeCheckerResponse {

    private List<TradeCheckerItemStatusDto> tradeCheckerItemStatuses;

    public List<TradeCheckerItemStatusDto> getTradeCheckerItemStatuses() {
        return tradeCheckerItemStatuses;
    }

    public static TradeCheckerResponseBuilder builder() {
        return new TradeCheckerResponseBuilder();
    }

    public static final class TradeCheckerResponseBuilder {
        private List<TradeCheckerItemStatusDto> tradeCheckerItemStatuses;

        private TradeCheckerResponseBuilder() {
        }

        public TradeCheckerResponseBuilder withTradeCheckerItemStatuses(List<TradeCheckerItemStatusDto> tradeCheckerItemStatuses) {
            this.tradeCheckerItemStatuses = tradeCheckerItemStatuses;
            return this;
        }

        public TradeCheckerResponse build() {
            TradeCheckerResponse tradeCheckerResponse = new TradeCheckerResponse();
            tradeCheckerResponse.tradeCheckerItemStatuses = this.tradeCheckerItemStatuses;
            return tradeCheckerResponse;
        }
    }
}
