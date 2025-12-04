package src.entities.workitems;
import src.entities.Enum.*;
import java.util.ArrayList;


public class Story extends WorkItem {
 private Epic parentepic; 
 private ArrayList<Task> subtasks;
 public Story(){
    super();
 }
 public Story(String id,String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
    super(id,title, description, createdBy,assignedTo,estimatedHrs,type);
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
