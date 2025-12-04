package src.entities.users;
import src.entities.Enum.*;


public class Stakeholder extends User{

public Stakeholder(String id, String name, String email, String username, String password, UserRole role){
super(id, name,email,username, password,role) ;
}

//todo: integrate with GUI
public void createEpic(){
    Epic();
}
public void createStory(){

}

}
