package dao;
import java.util.ArrayList;
import database.Database;
import entities.workitems.Story;
public class StoryDAO {
    // method adding story
    public void addStory(Story story) {
        Database.stories.add(story);
    }
    // method finds stories by id and returns it if found
    public Story findById(String id) {
        // Loop through each story in the database
        for (Story s : Database.stories) {
            if (id.equals(s.getId())) return s;
        }
        return null;
    }
    //method returns the entire list of stories
    public ArrayList<Story> getAll() {
        return Database.stories;
    }
}

