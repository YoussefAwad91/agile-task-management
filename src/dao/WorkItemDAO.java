package dao;
import java.util.ArrayList;
import database.Database;
import entities.users.User;
import entities.workitems.WorkItem;
import entities.workitems.Epic;
import entities.workitems.Story;
import entities.workitems.Task;
import entities.workitems.Bug;
public class WorkItemDAO {
    public WorkItem findById(String id) {
        // loop in each arraylist
        for (Epic e : Database.epics) {
            if (id.equals(e.getId())) return e;
        }
        for (Story s : Database.stories) {
            if (id.equals(s.getId())) return s;
        }
        for (Task t : Database.tasks) {
            if (id.equals(t.getId())) return t;
        }
        for (Bug b : Database.bugs) {
            if (id.equals(b.getId())) return b;
        }
        // return null if not found
        return null;
    }

    public ArrayList<WorkItem> getByAssignee(User user) {
        ArrayList<WorkItem> result = new ArrayList<>();
        if (user == null) return result;
        // loop in each arraylist and check (not empty and matching assignee)
        for (Epic e : Database.epics) {
            if (e.getAssignedTo() != null &&
                    user.getId().equals(e.getAssignedTo().getId())) {
                result.add(e);
            }
        }
        for (Story s : Database.stories) {
            if (s.getAssignedTo() != null &&
                    user.getId().equals(s.getAssignedTo().getId())) {
                result.add(s);

            }
        }
        for (Task t : Database.tasks) {
            if (t.getAssignedTo() != null &&
                    user.getId().equals(t.getAssignedTo().getId())) {
                result.add(t);
            }
        }
        for (Bug b : Database.bugs) {
            if (b.getAssignedTo() != null &&
                    user.getId().equals(b.getAssignedTo().getId())) {
                result.add(b);
            }
        }
        return result; // return found items
    }
    public void update(WorkItem item) {
        //check if item or item id is null
        if (item == null || item.getId() == null) return;
String id = item.getId();
// check in each arraylist if item found in specific one, update arraylist and no need to check in other lists
        if (item instanceof Epic) {
            for (int i = 0; i < Database.epics.size(); i++) {
                if (id.equals(Database.epics.get(i).getId())) {
                    Database.epics.set(i, (Epic) item);
                    return;
                }
            }
        }
        else if (item instanceof Story) {
            for (int i = 0; i < Database.stories.size(); i++) {
                if (id.equals(Database.stories.get(i).getId())) {
                    Database.stories.set(i, (Story) item);
                    return;
                }
            }
        }
        else if (item instanceof Task) {
            for (int i = 0; i < Database.tasks.size(); i++) {
                if (id.equals(Database.tasks.get(i).getId())) {
                    Database.tasks.set(i, (Task) item);
                    return;
                }
            }
        }
        else if (item instanceof Bug) {
            for (int i = 0; i < Database.bugs.size(); i++) {
                if (id.equals(Database.bugs.get(i).getId())) {
                    Database.bugs.set(i, (Bug) item);
                    return;
                }
            }
        }
    }
}