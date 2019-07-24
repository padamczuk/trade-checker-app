package lab.orion.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import lab.orion.services.checker.DirectionLevel;
import lab.orion.services.checker.LevelData;
import lab.orion.services.checker.ViolationChecker;
import lab.orion.services.dto.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

import static lab.orion.services.checker.DirectionLevelUtil.createDirectionLevel;
import static lab.orion.services.checker.DirectionLevelUtil.createIndirectLevel;

/**
 * https://comparic.pl/futures-spot-swap-forward-put-call-podstawowe-definicje-zwiazane-z-zabezpieczeniem-ryzyka-walutowego/
 */
@Path("/tradeChecker")
public class TradeCheckerResource {
    private final List<DirectionLevel> directionLevels = Lists.newArrayList();

    @Inject
    public TradeCheckerResource(Set<ViolationChecker<TradeCheckerItemDto, CheckError>> violationCheckers) {
        Set<ViolationChecker<TradeCheckerItemDto, CheckError>> directViolationCheckers = violationCheckers.stream()
                .filter(checker -> !checker.hasPriorCheckers())
                .collect(Collectors.toSet());

        DirectionLevel directionLevel = createIndirectLevel(directViolationCheckers);
        directionLevels.add(directionLevel);

        Set<ViolationChecker<TradeCheckerItemDto, CheckError>> indirectViolationCheckers = violationCheckers.stream()
                .filter(checker -> checker.hasPriorCheckers())
                .collect(Collectors.toSet());

        LevelData levelData = LevelData.builder()
                .withCurrentCheckerCandidates(indirectViolationCheckers)
                .withCurrentCheckerCandidatesClasses(indirectViolationCheckers.stream()
                        .map(checker->checker.getClass())
                        .collect(Collectors.toSet()))
                .withPreviousCheckerCandidatesClasses(directViolationCheckers.stream()
                        .map(checker->checker.getClass())
                        .collect(Collectors.toSet()))
                .build();

        while (!levelData.getCurrentCheckerCandidates().isEmpty()) {
            DirectionLevel relatedDirectionLevel = createDirectionLevel(levelData);
            directionLevels.add(relatedDirectionLevel);
            Set<ViolationChecker<TradeCheckerItemDto, CheckError>> notRelatedCheckers = Sets.difference(levelData.getCurrentCheckerCandidates(), relatedDirectionLevel.getViolationCheckerSet());
            levelData = LevelData.builder()
                    .withCurrentCheckerCandidates(notRelatedCheckers)
                    .withPreviousCheckerCandidatesClasses(levelData.getCurrentCheckerCandidatesClasses())
                    .withCurrentCheckerCandidatesClasses(notRelatedCheckers.stream()
                            .map(checker -> checker.getClass())
                            .collect(Collectors.toSet()))
                    .build();
        }
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response check(TradeCheckerRequest tradeCheckerRequest) {
        List<TradeCheckerItemStatusDto> tradeCheckerItemStatuses = tradeCheckerRequest.getTradeItems()
                .stream()
                .map(this::checkItem)
                .collect(Collectors.toList());
        return Response.status(200)
                .entity(TradeCheckerResponse.builder()
                        .withTradeCheckerItemStatuses(tradeCheckerItemStatuses)
                        .build())
                .build();
    }

    private TradeCheckerItemStatusDto checkItem(TradeCheckerItemDto tradeCheckerItemDto) {
        Map<Class<? extends ViolationChecker>, CheckError> errorsMap = new HashMap<>();
        Set<CheckError> checkError = new HashSet<>();
        directionLevels.stream().forEach((directionLevel) -> {
            Map<Class<? extends ViolationChecker>, CheckError> currentErrorsMap =
            directionLevel.getViolationCheckerSet()
                    .stream()
                    .filter(checker -> !checker.hasFailureDependency(errorsMap) && checker.hasViolation(tradeCheckerItemDto))
                    .collect(Collectors.toMap(checker -> checker.getClass(), checker -> checker.getViolation(tradeCheckerItemDto)));
            checkError.addAll(currentErrorsMap.values());
            errorsMap.putAll(currentErrorsMap);
        });
        return TradeCheckerItemStatusDto.builder()
                .withErrors(errorsMap.values())
                .build();
    }

}
