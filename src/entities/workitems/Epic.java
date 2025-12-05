package entities.workitems;

import entities.Enum.*;
import entities.users.*;
import java.util.ArrayList;

public class Epic extends WorkItem {    

    private ArrayList<Story> stories= new ArrayList<>();
    public Epic(){
        super();
    }
     public Epic(String id,String title, String description, User createdBy, User assignedTo, int estimatedHrs,WorkItemType type, Status status) {
        super(id,title, description, status ,createdBy,assignedTo,estimatedHrs,type );
        this.stories = new ArrayList<>();
        database.Database.epics.add(this);
    }

    public void addStory(Story story){
         stories.add(story); 
        }

    public ArrayList<Story> getStories(){ 
        return stories;
    }
    
}