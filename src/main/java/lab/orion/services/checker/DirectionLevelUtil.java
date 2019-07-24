package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

import java.util.Set;
import java.util.stream.Collectors;

public class DirectionLevelUtil {
    private DirectionLevelUtil() {}

    public static DirectionLevel createIndirectLevel(Set<ViolationChecker<TradeCheckerItemDto, CheckError>> directViolationCheckers) {
        DirectionLevel directionLevel = new DirectionLevel();
        directionLevel.getViolationCheckerSet().addAll(directViolationCheckers);
        return directionLevel;
    }

    public static DirectionLevel createDirectionLevel(LevelData levelData) {
        DirectionLevel directionLevel = new DirectionLevel();
        Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentRelatedToPreviousButNotToItSelf = getRelatedCheckers(levelData.getCurrentCheckerCandidates(), levelData.getPreviousCheckerCandidatesClasses(), levelData.getCurrentCheckerCandidatesClasses());
        directionLevel.getViolationCheckerSet().addAll(currentRelatedToPreviousButNotToItSelf);
        return directionLevel;
    }

    private static Set<ViolationChecker<TradeCheckerItemDto, CheckError>> getRelatedCheckers(Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentViolationCheckers, Set<Class> previousLevelCheckersClasses, Set<Class> currentLevelCheckersClasses) {
        Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentRelatedToPreviousButNotToItSelf = currentViolationCheckers
                .stream()
                .filter(violationChecker -> !currentLevelCheckersClasses.stream()
                        .anyMatch(indirectClass -> violationChecker.getPriorCheckers().contains(indirectClass)))
                .filter(violationChecker -> previousLevelCheckersClasses.stream()
                        .anyMatch(indirectClass -> violationChecker.getPriorCheckers().contains(indirectClass)))
                .collect(Collectors.toSet());
        return currentRelatedToPreviousButNotToItSelf;
    }

}
