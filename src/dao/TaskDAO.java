package dao;
import java.util.ArrayList;
import database.Database;
import entities.workitems.Task;
public class TaskDAO {
    // method adding task
    public void addTask(Task task) {
        Database.tasks.add(task);
    }
    // method finds task by id and returns it if found
    public Task findById(String id) {
        // Loop through each task in the database
        for (Task t : Database.tasks) {
            if (id.equals(t.getId())) return t;
        }
        return null;
    }
    //method returns the entire list of tasks
    public ArrayList<Task> getAll() {
        return Database.tasks;
    }
}

