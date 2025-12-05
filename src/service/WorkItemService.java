package service;

import entities.users.User;
import entities.workitems.*;
import entities.Enum.*;

import entities.*;

public class WorkItemService {

    public Epic createEpic(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs){
        return new Epic(id,title, description, createdby, assignedTo, estimatedhrs, WorkItemType.EPIC);
    }
    public Story createStory(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs,WorkItemType type){
        return new Story(id,title, description, createdby, assignedTo, estimatedhrs,type);

    }
    public Task createTask(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs,WorkItemType type){
        return new Task(id,title, description, createdby, assignedTo, estimatedhrs,type);

    }
    public void assignUserToWorkItem(WorkItem item, User user){

    } 
    public void changeWorkItemStatus(WorkItem item, Status status){

    }
    public boolean validateStatusTransition(User user, WorkItem item, Status newStatus){

    }

}
