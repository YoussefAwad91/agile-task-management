package database;

import java.util.ArrayList;
import entities.users.User;
import entities.workitems.Epic;
import entities.workitems.Story;
import entities.workitems.Task;
import entities.workitems.Bug;
import entities.Sprint;

public class Database {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Epic> epics = new ArrayList<>();
    public static ArrayList<Story> stories = new ArrayList<>();
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Bug> bugs = new ArrayList<>();
    public static ArrayList<Sprint> sprints = new ArrayList<>();

    public static void initialize() {
        // If the (whatever) list is null, create an empty ArrayList for it.
        // Make sure list exist before we use them
        if (users == null) users = new ArrayList<>();
        if (epics == null) epics = new ArrayList<>();
        if (stories == null) stories = new ArrayList<>();
        if (tasks == null) tasks = new ArrayList<>();
        if (bugs == null) bugs = new ArrayList<>();
        if (sprints == null) sprints = new ArrayList<>();
    }
}
