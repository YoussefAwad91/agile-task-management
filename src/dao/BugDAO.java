package dao;
import java.util.ArrayList;
import database.Database;
import entities.workitems.Bug;
public class BugDAO {
    // method adding bug
    public void addBug(Bug bug) {
        Database.bugs.add(bug);
    }
    // method finds bugs by id and returns it if found
    public Bug findById(String id) {
        // Loop through each bugs in the database
        for (Bug b : Database.bugs) {
            if (id.equals(b.getId())) return b;
        }
        return null;
    }
    //method returns the entire list of bugs
    public ArrayList<Bug> getAll() {
        return Database.bugs;
    }
}

