package entities.users;

import database.Database;
import entities.Enum.*;
import entities.workitems.*;
import util.IDGenerator;


public class QAEngineer extends User {
 public QAEngineer(String id, String name, String email, String username, String password, UserRole role){
    super( id, name, email, username, password, role ) ;
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
public Bug reportBug (Task task, String description){
    Bug bug = new Bug(
            IDGenerator.generateID("BUG"),    
            "Bug reported on: " + task.getTitle(),
            description,
            this,                              
            Severity.MEDIUM                    
        );

    
        bug.setAssignedTo(task.getAssignedTo());


        Database.bugs.add(bug);

        System.out.println("Bug reported for Task " + task.getId() + ": " + description);

        return bug;
    }
}