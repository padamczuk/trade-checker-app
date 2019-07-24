package lab.orion.services.checker;

import lab.orion.services.dto.CheckError;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ViolationChecker<T, E> {
    protected Set<Class> priorCheckers;

    public ViolationChecker() {
        this.priorCheckers = Collections.emptySet();
    }
    public ViolationChecker(Class ... priorCheckers) {
        this.priorCheckers = Arrays.stream(priorCheckers)
                .collect(Collectors.toSet());
    }

    public boolean hasPriorCheckers() {
        return !priorCheckers.isEmpty();
    }

    public Set<Class> getPriorCheckers() {
        return priorCheckers;
    }

    /**
     * Method verifies if for given assumed checkers there are checkers that have already found violation,
     * this will then prevent current checker from certain failure.
     * @param violations
     * @return
     */
    public boolean hasFailureDependency(Map<Class<? extends ViolationChecker>, CheckError> violations) {
        return priorCheckers.stream()
                .filter(priorClass -> violations.get(priorClass) != null)
                .findFirst()
                .isPresent();
    }

    public abstract boolean hasViolation(T entity);
    public abstract E getViolation(T entity);
}
