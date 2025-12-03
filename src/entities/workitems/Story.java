package src.entities.workitems;
import java.util.ArrayList;
public class Story extends WorkItem {
 private Epic parentepic; 
 private ArrayList<Task> subtasks;
 public Story(){
    super();
 }
 public Story(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs,WorkItemType type){
    super(id,title, description, createdby,assignedTo,estimatedhrs,type);
    this.subtasks= new ArrayList<>();
 }
 public void addSubtask(Task task){
    subtasks.add(task);
 } 
public boolean isCompleted(){
    for( int i=0; i<subtasks.size();i++){
        Task task=subtasks.get(i);
        if(task.getStatus() !== status.VERIFIED)
        {return false;}
    }
        return true;
    }
public Epic getparentepic(){
    return this.parentepic;
} 
public ArrayList<Task> getSubtasks(){
    return subtasks;
}    
}
