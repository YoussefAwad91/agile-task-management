package service;

import entities.users.User;
import entities.workitems.*;
import entities.Enum.*;


public class WorkItemService {

    public Epic createEpic(String title, String description, User createdBy, User assignedTo, int estimatedHrs){
        return new Epic (title, description, createdBy, assignedTo, estimatedHrs,WorkItemType.EPIC, Status.TODO);
    }
    public Story createStory(String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
        return new Story(title, description, createdBy, assignedTo, estimatedHrs,WorkItemType.STORY,  Status.TODO);

    }
    public Task createTask(String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
        return new Task(title, description, createdBy, assignedTo, estimatedHrs, WorkItemType.TASK, Status.TODO);

    }
    public void assignUserToWorkItem(WorkItem item, User user){
        item.setAssignedTo(user);

    } 
    public void changeWorkItemStatus(WorkItem item, Status status){
        item.changeStatus(status);
    }


}
