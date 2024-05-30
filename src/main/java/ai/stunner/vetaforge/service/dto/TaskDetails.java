package ai.stunner.vetaforge.service.dto;

import ai.stunner.vetaforge.service.flowable.entities.TaskImportance;

public class TaskDetails {
    private TaskImportance importance;

    public TaskImportance getImportance() {
        return importance;
    }

    public void setImportance(TaskImportance importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "TaskDetails{" +
            "importance=" + importance +
            '}';
    }
}
