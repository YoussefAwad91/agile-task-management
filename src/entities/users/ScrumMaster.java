package src.entities.users;

public class Scrummaster extends User { 
public Scrummaster(){}
public Scrummaster(String id, String name, String email, String username, String password, UserRole role){
super(id, name,email,username, password,role) ;
}

//Methods
public Sprint createSprint(){

}
public void assignWorkItemToSprint(WorkItem item, Sprint sprint){

}
public void assignUserToSprint(User user, Sprint sprint){

}
public void reopenWorkItem(WorkItem item){
    
}

}