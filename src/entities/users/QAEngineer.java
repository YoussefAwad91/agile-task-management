package src.entities.users;

public class Qaengineer extends User {
 public Qaengineer(String id, String name, String email, String username, String password, UserRole role){
super(id, name,email,username, password,role) ;
}

public void verifyTask(Task task){
    if(task.getStatus!= this){
        System.out.println("Cannot verify: Task is not completed yet.");
        return;
    }
 task.setStatus(Status.VERIFIED);
}
public Bug reportBug(Task task, String description);
}