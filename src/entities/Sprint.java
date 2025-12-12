package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.users.User;
import entities.workitems.WorkItem;
import entities.Enum.*;

public class Sprint {

    private String id; 
    private LocalDate startDate; 
    private LocalDate endDate; 
    private String objective; 
    private ArrayList<User> teamMembers; 
    private ArrayList<WorkItem> sprintBacklog; 

    public Sprint(){}
    public Sprint(String id, LocalDate startDate, LocalDate endDate, String objective){
        this.id = id; 
        this.startDate = startDate; 
        this.endDate = endDate; 
        this.objective = objective; 
        this.teamMembers = new ArrayList<User>();
        this.sprintBacklog = new ArrayList<WorkItem>();
        database.Database.sprints.add(this);
    }

    public void addTeamMember(User user){
        if(!teamMembers.contains(user))
            teamMembers.add(user); 
    }

    public void addWorkItem(WorkItem item){
        if(!sprintBacklog.contains(item))
            sprintBacklog.add(item);
    } 

    public boolean containsUser(User user){
        return teamMembers.contains(user);
    }

    public int calculateProgress(){
        if(sprintBacklog.isEmpty()){
            return 0; 
        } 
        int completedItems = 0;
        for(WorkItem item : sprintBacklog){
            if(item.getStatus() == Status.VERIFIED){
                completedItems++; 
            }
        }
        return (completedItems / sprintBacklog.size())*100;
    }

    public boolean isActive(LocalDate today){ //Local date methods
        return (today.isEqual(startDate) || today.isAfter(startDate)) && 
               (today.isEqual(endDate) || today.isBefore(endDate));
    }

    public String getId() {
        return id;
    }
    public String getObjective() {
        return objective;
    }

    public ArrayList<WorkItem> getBacklog(){
        return sprintBacklog;
    }
}
    