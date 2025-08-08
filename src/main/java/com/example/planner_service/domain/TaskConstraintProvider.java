// File: domain/TaskConstraintProvider.java
package com.example.planner_service.domain;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class TaskConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                employeeConflict(constraintFactory),
                balanceWorkload(constraintFactory),
                preferredEmployee(constraintFactory)  // explicitly added here
        };
    }


    private Constraint employeeConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class,
                        org.optaplanner.core.api.score.stream.Joiners.equal(Task::getAssignedEmployee))
                .penalize(HardSoftScore.ONE_HARD,(task1, task2) -> 1)
                    .asConstraint("Employee conflict");
    }

    private Constraint balanceWorkload(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                .groupBy(Task::getAssignedEmployee, ConstraintCollectors.count())
                    .penalize( HardSoftScore.ONE_SOFT, (employee, taskCount) -> taskCount * taskCount)
                        .asConstraint("Workload imbalance");
    }

    private Constraint preferredEmployee(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                .filter(task -> task.getPreferredEmployee() != null
                        && !task.getPreferredEmployee().getName().equals(task.getAssignedEmployee().getName()))
                .penalize(HardSoftScore.ONE_SOFT)
                    .asConstraint("Preferred employee not matched");
    }



}
