package util;
import entities.users.User;
import entities.workitems.*;
import database.Database;
import entities.Sprint;

public class IDGenerator {
    public static String generateID(Object obj){
        if (obj instanceof Epic){
            return "E"+ (Database.epics.size()+1);
        }
        if (obj instanceof Story){
            return "S"+ (Database.stories.size()+1);
        }
        if (obj instanceof Task){
            return "T"+ (Database.tasks.size()+1);
        }
        if (obj instanceof Bug){
            return "B"+ (Database.bugs.size()+1);
        }
        if (obj instanceof Sprint){
            return "P"+ (Database.sprints.size()+1);
        }
        if (obj instanceof User){
            return "U"+ (Database.users.size()+1);
        }
        else{
            return "NULL";
        }
    }
}
