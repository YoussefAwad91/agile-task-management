package entities.users;

import entities.Enum.*;
import util.IDGenerator;

public abstract class User {
    private String id;
    private String name ;
    private String email;
    private String username;
    private String password;
    private UserRole role ;

    public User(String name, String email, String username, String password, UserRole role) {
    this.id=IDGenerator.generateID(this);
    this.name=name;
    this.email=email;
    this.username=username;
    this.password=password;
    this.role=role;
    database.Database.users.add(this);

    }

    public User (){}
    public String getId(){
        return id;
    }

    public String getName(){
        return name;

    }
    public String getEmail(){
        return email;
    }
     public String getPassword(){
    return password;
   }
    public String getUsername(){
        return username;

    }
    public UserRole getRole(){
        return role;
    
    }
    public void setName(String name){
        this.name=name;
    }
    
    public void setEmail(String email){
        this.email=email;

    }
    public void setPassword(String password){
        this.password=password;
    }
    public boolean login(String username, String password){
         if(this.username.equals(username) && this.password.equals(password)){
        return true;
    } else {
        return false;
    }
    }
    
    public String toString(){
     return "User {" +
   "id='" + id + '\'' +
    ", name='" + name + '\'' +
     ", email='" + email + '\'' +
     ", username='" + username + '\'' +
     ", role=" + role +
      '}';
    }

}
