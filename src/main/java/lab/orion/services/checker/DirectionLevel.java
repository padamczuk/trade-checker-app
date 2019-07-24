package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectionLevel {
    private Set<ViolationChecker<TradeCheckerItemDto, CheckError>> violationCheckerSet = new HashSet<>();

    public Set<ViolationChecker<TradeCheckerItemDto, CheckError>> getViolationCheckerSet() {
        return violationCheckerSet;
    }
}
