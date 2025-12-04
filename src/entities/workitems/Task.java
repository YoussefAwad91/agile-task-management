package entities.workitems;

import entities.users.*;
import entities.Enum.*;

public class Task extends WorkItem {
    public Task(){
    }

     public Task(String id,String title, String description, Status status, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
      super(id,title, description, status,createdBy,assignedTo,estimatedHrs,type);
     }
    public void setStatus(Status status){
        this.status=status;

    }

  @Override
public void changeStatus(Status status){
    this.status = status;
}

    
}
