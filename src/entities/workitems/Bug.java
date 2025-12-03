package src.entities.workitems;

public class Bug extends WorkItem{
     private User reportedBy; 
     private Severity severity;
      public Bug(){
        super();
    }
     public Bug(String id,String title, String description, User createdby, 
      User assignedTo, int estimatedhrs,WorkItemType type, User reportedBy,Severity severity){
    super(id,title, description, createdby,assignedTo,estimatedhrs,type);
    this.reportedBy=reportedBy;
    this.severity=severity;
     }

    public Severity getSeverity(){return this.severity;} 
    public User getReporter(){return this.reportedBy;}  
}
