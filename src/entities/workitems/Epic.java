package src.entities.workitems;
import src.entities.Enum.*;
import java.util.ArrayList;

public class Epic extends WorkItem {    
    private ArrayList<Story> stories= new ArrayList<>();
    public Epic(){
        super();
    }
     public Epic(String id,String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type) {
        super(id,title, description, createdBy,assignedTo,estimatedHrs,type);
        this.stories = new ArrayList<>();}

       public void setParentEpic(Epic epic) {
       this.parentepic = epic;
    }


    public void addStory(Story story){
         stories.add(story); 
        }
    public ArrayList<Story> getStories(){ 
        return stories;
    }
    
}