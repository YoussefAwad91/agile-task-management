package src.entities.users;

import src.entities.Enum.*;
import src.entities.workitems.*;
import s;


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


public Bug reportBug (Task task, String description){
    Bug b1 =new Bug();
    IDGenerator
        return Bug; 
   
}
}
