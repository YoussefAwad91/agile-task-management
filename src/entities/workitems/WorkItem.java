package entities.workitems;

import entities.users.User;
import entities.Enum.*;


public abstract class WorkItem {
    
    //attributes 
    protected String id;
    protected String title;
    protected String description;
    protected Status status;
    protected User createdBy;
    protected User assignedTo;
    protected int estimatedHrs;
    protected WorkItemType type;
   
    //Constructor
    public WorkItem(){}
    public WorkItem(String id, String title, String description, Status status, User createdBy, User assignedTo, int estimatedHrs, WorkItemType type) {
    this.id=id;
    this.title=title;
    this.description=description;
    this.status=status;
    this.createdBy=createdBy;
    this.assignedTo= assignedTo;
    this.estimatedHrs=estimatedHrs;
    this.type=type;
    
}
//public abstract void changeStatus(Status newStatus);



    //Methods needed
    public String getId(){
    return id;

    }
    public String getTitle(){
        return title;

    }
    public String getDescription(){
        return description;

    }
    public Status getStatus(){
        return status;
        
    }
    public User getcreatedby(){
        return this.createdBy;
    }
    public User getAssignedTo(){
        return this.assignedTo;
    }
    public int getEstimatedHours(){
    return this.estimatedHrs;
    }



    public void setAssignedTo(User user){
        this.assignedTo=user;
    }
    public void setStatus(Status status){
        this.status=status;
    }
    public void changeStatus(Status newStatus){
        this.status=newStatus;
    }

// ana msh fahma eh da lesa 
    @Override
    public String toString() {
        String text;
        if (assignedTo == null) {
            text = "Nothing";
        } else {
            text = assignedTo.getUsername();
      
            }
              return "ID: " + id +
            ", Title: " + title +
            ", Status: " + status +
            ", Assigned to: " +text;
    }
    }