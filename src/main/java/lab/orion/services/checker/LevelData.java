package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

import java.util.Set;

public class LevelData {
    private Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentCheckerCandidates;
    private Set<Class> previousCheckerCandidatesClasses;
    private Set<Class> currentCheckerCandidatesClasses;

    public Set<ViolationChecker<TradeCheckerItemDto, CheckError>> getCurrentCheckerCandidates() {
        return currentCheckerCandidates;
    }

    public Set<Class> getPreviousCheckerCandidatesClasses() {
        return previousCheckerCandidatesClasses;
    }

    public Set<Class> getCurrentCheckerCandidatesClasses() {
        return currentCheckerCandidatesClasses;
    }

    public static LevelDataBuilder builder() {
        return new LevelDataBuilder();
    }

    public static final class LevelDataBuilder {
        private Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentCheckerCandidates;
        private Set<Class> previousCheckerCandidatesClasses;
        private Set<Class> currentCheckerCandidatesClasses;

        private LevelDataBuilder() {
        }

        public LevelDataBuilder withCurrentCheckerCandidates(Set<ViolationChecker<TradeCheckerItemDto, CheckError>> currentCheckerCandidates) {
            this.currentCheckerCandidates = currentCheckerCandidates;
            return this;
        }

        public LevelDataBuilder withPreviousCheckerCandidatesClasses(Set<Class> previousCheckerCandidatesClasses) {
            this.previousCheckerCandidatesClasses = previousCheckerCandidatesClasses;
            return this;
        }

        public LevelDataBuilder withCurrentCheckerCandidatesClasses(Set<Class> currentCheckerCandidatesClasses) {
            this.currentCheckerCandidatesClasses = currentCheckerCandidatesClasses;
            return this;
        }

        public LevelData build() {
            LevelData levelData = new LevelData();
            levelData.currentCheckerCandidates = this.currentCheckerCandidates;
            levelData.previousCheckerCandidatesClasses = this.previousCheckerCandidatesClasses;
            levelData.currentCheckerCandidatesClasses = this.currentCheckerCandidatesClasses;
            return levelData;
        }
    }
}
