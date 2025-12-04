package src.entities;

public class Enum {
    public enum Status { TODO, IN_PROGRESS, DONE, VERIFIED, REOPENED } 
    public enum UserRole { STAKEHOLDER, DEVELOPER, QA, SCRUM_MASTER } 
    public enum WorkItemType { EPIC, STORY, TASK, BUG } 
    public enum Severity { LOW, MEDIUM, HIGH, CRITICAL } 
}
