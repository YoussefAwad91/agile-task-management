package src.entities.workitems;
import java.util.ArrayList;
public class Epic extends WorkItem {    
    private ArrayList<Story> stories= new ArrayList<>();
    public Epic(){
        super();
    }
     public Epic(String id,String title, String description, User createdby, User assignedTo, int estimatedhrs,WorkItemType type) {
        super(id,title, description, createdby,assignedTo,estimatedhrs,type);
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