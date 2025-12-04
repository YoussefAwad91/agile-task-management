package src.entities.users;

import java.time.LocalDate;

import src.entities.Enum.*;
import src.entities.workitems.*;


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
    System.out.println("WorkItem " + item.getId() + " added to Sprint " + sprint.getId());
}


public void assignUserToSprint(User user, Sprint sprint){

}
public void reopenWorkItem(WorkItem item){
    
}

}