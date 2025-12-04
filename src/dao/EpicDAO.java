package dao;
import java.util.ArrayList;
import database.Database;
import entities.workitems.Epic;
public class EpicDAO {
    // method adding epic
    public void addEpic(Epic epic) {
        Database.epics.add(epic);
    }
    // method finds epic by id and returns it if found
    public Epic findById(String id) {
        // Loop through each epic in the database
        for (Epic e : Database.epics) {
            if (id.equals(e.getId())) return e;
        }
return null;
    }
    //method returns the entire list of epics
    public ArrayList<Epic> getAll() {
        return Database.epics;
    }
    }

