package src.entities.workitems;
import src.entities.users.*;
import src.entities.Enum.*;


public class Bug extends WorkItem{
     private User reportedBy; 
     private Severity severity;
      public Bug(){
        super();
    }
     public Bug(String id,String title, String description, Status status, User createdBy, 
      User assignedTo, int estimatedHrs,WorkItemType type, User reportedBy,Severity severity){

      super(id, title, description, status, createdBy, assignedTo, estimatedHrs, type);

      this.reportedBy=reportedBy;
      this.severity=severity;
    }

    public Severity getSeverity(){
      return severity;
    } 
    public User getReporter(){
      return reportedBy;
      }  
}
