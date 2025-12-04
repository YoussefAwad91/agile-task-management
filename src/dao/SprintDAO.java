package dao;
import java.util.ArrayList;
import database.Database;
import entities.workitems.Sprint;
public class SprintDAO {
    // method adding sprint
    public void addSprint(Sprint sprint) {
        Database.sprints.add(sprint);
    }
    // method finds sprint by id and returns it if found
    public Sprint findById(String id) {
        // Loop through each sprint in the database
        for (Sprint s : Database.sprints) {
            if (id.equals(s.getId())) return s;
        }
        return null;
    }
    //method returns the entire list of sprints
    public ArrayList<Sprint> getAll() {
        return Database.sprints;
    }
}

