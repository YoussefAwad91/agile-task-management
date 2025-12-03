package src.entities.users;
public class Stakeholder extends User{

public Stakeholder(String id, String name, String email, String username, String password, UserRole role){
super(id, name,email,username, password,role) ;
}


public void createEpic(){
    //todo: integrate with GUI
    Epic();
}
public void createStory(){

}

}
