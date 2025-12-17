package entities.workitems;

import entities.users.*;
import util.IDGenerator;
import entities.Enum.*;

public class Task extends WorkItem {
    public Task(){
    }

     public Task(String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type, Status status){
      super(IDGenerator.generateID(new Task()),title, description, status,createdBy,assignedTo,estimatedHrs,type);
      database.Database.tasks.add(this);
     }
    public void setStatus(Status status){
        this.status=status;

    }

    @Override
    public void changeStatus(Status status){
        this.status = status;
    }

    public String getTitle(){
        return title;
    }

    
}
