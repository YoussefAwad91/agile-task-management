package src.entities.workitems;
public class Task extends WorkItem {
    public Task(){
        super();
    }
     public Task(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs,WorkItemType type){
    super(id,title, description, createdby,assignedTo,estimatedhrs,type);
     }
    public void setStatus(Status status){
        this.status=status;

    }

  //  @Override
//public void changeStatus(Status status){
    //this.status = status;
//}

    
}
