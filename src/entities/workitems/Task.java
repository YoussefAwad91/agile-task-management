package src.entities.workitems;
import src.entities.users.*;
import src.entities.Enum.*;


public class Task extends WorkItem {
    public Task(){
    }

     public Task(String id,String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
      super(id,title, description, createdBy,assignedTo,estimatedHrs,type);
     }
    public void setStatus(Status status){
        this.status=status;

    }

  @Override
public void changeStatus(Status status){
    this.status = status;
}

    
}
