package entities.workitems;

import entities.Enum.*;
import entities.users.*;
import java.util.ArrayList;


public class Story extends WorkItem {
 //attributes
 private Epic parentEpic; 
 private ArrayList<Task> subtasks;
 
 
 public Story(){
    super();
 }
 public Story(String id,String title, String description, Status status, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type){
    super(id,title, description,status, createdBy,assignedTo,estimatedHrs,type);
    this.subtasks= new ArrayList<>();
 }
 
 //Methods
 public void addSubtask(Task task){
    subtasks.add(task);
 } 
public boolean isCompleted(){
    for( int i=0; i<subtasks.size();i++){
        Task task=subtasks.get(i);
        if(task.getStatus() != Status.VERIFIED)
        {return false;}
    }
        return true;
    }
public Epic getparentepic(){
    return parentEpic;
} 
public ArrayList<Task> getSubtasks(){
    return subtasks;
}    
}
