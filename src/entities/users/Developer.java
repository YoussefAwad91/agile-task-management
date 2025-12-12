package entities.users;

import java.util.ArrayList;

import entities.Enum.*;
import entities.workitems.*;

public class Developer extends User  {

public Developer(String name, String email, String username, String password, UserRole role){
super(name,email,username, password,role) ;
}

//attributes 
private int capacityHours;
private ArrayList<Task> assignedTasks;

//methodes
public int getCapacity(){
return capacityHours;
 }

public void reduceCapacity(int hours) {
    if (hours > 0) {
        this.capacityHours = this.capacityHours - hours;
        if (this.capacityHours < 0) {
            this.capacityHours = 0;
        }
    }
}

public void increaseCapacity(int hours){
    if(hours > 0 ) {
        this.capacityHours= capacityHours +hours;

    }

}

//Validate assignment 
public void changeTaskStatus(Task task, Status newStatus){

    if (task.getAssignedTo() != this) {
        System.out.println("Error: This task is not assigned to this developer.");
        return;
    }
    task.setStatus(newStatus);
    System.out.println("Task " + task.getId() + " status updated to " + newStatus);
}

public ArrayList<Task> getAssignedTasks(){
    return assignedTasks;
}

}


