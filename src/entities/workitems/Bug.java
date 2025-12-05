package entities.workitems;

import entities.users.*;
import entities.Enum.*;


public class Bug extends WorkItem{
     private User reportedBy; 
     private Severity severity;
      public Bug(){
        super();
    }
     public Bug(String id,String title, String description, Status status, User createdBy, 
      User assignedTo, int estimatedHrs, User reportedBy,Severity severity){

      super(id, title, description, status, createdBy, assignedTo, estimatedHrs, WorkItemType.BUG);

      this.reportedBy=reportedBy;
      this.severity=severity;
      database.Database.bugs.add(this);
    }

    public Severity getSeverity(){
      return severity;
    } 
    public User getReporter(){
      return reportedBy;
      }  
}
