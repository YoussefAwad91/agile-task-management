package entities.users;

import database.Database;
import entities.Enum.*;
import entities.workitems.*;

public class QAEngineer extends User {
 public QAEngineer(String name, String email, String username, String password, UserRole role){
    super(name, email, username, password, role ) ;
    }

public void verifyTask(Task task){

    if( task.getStatus () !=  Status.DONE){
        System.out.println("Cannot verify: Task is not completed yet.");
        return;
    }

 task.setStatus(Status.VERIFIED);
 System.out.print("Task"+ task.getId()+ "has been VERIFIED");
}

//String id,String title, String description, Status status, User createdBy, 
     // User assignedTo, int estimatedHrs,WorkItemType type, User reportedBy,Severity severity
public Bug reportBug (Task task, String description, int estimatedHrs, Severity severity, User assignedTo){
    Bug bug = new Bug("Bug reported on: " + task.getTitle(), description, Status.TODO, this, assignedTo, estimatedHrs, this, severity);
    bug.setAssignedTo(task.getAssignedTo());

    Database.bugs.add(bug);

    System.out.println("Bug reported for Task " + task.getId() + ": " + description);

    return bug;
    }
}