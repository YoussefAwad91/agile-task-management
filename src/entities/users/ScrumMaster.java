package entities.users;

import entities.Enum.*;
import entities.workitems.*;
import java.time.LocalDate;

public class ScrumMaster extends User { 

public ScrumMaster(){}
public ScrumMaster(String id, String name, String email, String username, String password, UserRole role){
super(id, name,email,username, password,role) ;
}

//Methods
public Sprint createSprint(String id, LocalDate start, LocalDate end, String objective){
    Sprint sprint = new Sprint(id, start, end, objective);
    return sprint ;

}

public void assignWorkItemToSprint(WorkItem item, Sprint sprint){
    if (sprint == null || item == null) {
        System.out.println("Cannot assign");
        return;
    }

    sprint.addWorkItem(item);
    System.out.println("WorkItem: " + item.getId() + " added to Sprint: " + sprint.getId());
}


public void assignUserToSprint(User user, Sprint sprint){
if (user == null || sprint == null) {
System.out.println("Cannot assign user");
 return;
 }
 sprint.addTeamMember(user);   

 System.out.println("User: " + user.getUsername() + " added to Sprint: " + sprint.getId());
}

public void reopenWorkItem(WorkItem item){
    // if (item == null) {
        //System.out.println("Cannot reopen: WorkItem is null.");
       // return;
    
    if (item.getStatus() != Status.VERIFIED) {
        System.out.println("Cannot reopen: WorkItem is not VERIFIED.");
        return;
    }

    item.setStatus(Status.REOPENED);
    System.out.println("WorkItem: " + item.getId() + " has been REOPENED.");
    
}
}