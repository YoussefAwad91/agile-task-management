// javac -d ../out (Get-ChildItem -Recurse -Filter *.java).FullName
// java -cp ../out app.Main
package app;
import database.Database;
import entities.*;
import entities.users.*;
import entities.workitems.*;
import entities.Enum.*;
import service.*;
import dao.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        //Initialize database memory 
        Database.initialize();

        // DAO objects 
        EpicDAO epicDAO = new EpicDAO();
        StoryDAO storyDAO = new StoryDAO();
        TaskDAO taskDAO = new TaskDAO();
        SprintDAO sprintDAO = new SprintDAO();

        // Service objects
        AuthService authService = new AuthService();
        WorkItemService workItemService = new WorkItemService();

        // Create sample users 
        User stakeholder1 = new Stakeholder("Malak", "malak@mail.com", "malak1", "10206", UserRole.STAKEHOLDER);
        User developer1 = new Developer("konouz", "Konouz@mail.com", "konouz2", "8206", UserRole.DEVELOPER);
        User qa1 = new QAEngineer("Amr","Amr@mail.com", "Amr3", "17804", UserRole.QA);
        User scrumMaster1 = new ScrumMaster("Awad", "Awad@mail.com", "Awad4", "9105", UserRole.SCRUM_MASTER);
        authService.register(stakeholder1);
        authService.register(developer1);
        authService.register(qa1);
        authService.register(scrumMaster1);

        System.out.println("Users registered successfully!");


        //Create an Epic 
        Epic epic1 = new Epic("Epic1 Title", "Epic1 Description", stakeholder1, developer1, 3, WorkItemType.EPIC, Status.TODO);
        epicDAO.addEpic(epic1);

        System.out.println("Epic created!");


        //Create a Story under the Epic
        Story story1 = new Story( "Story Title", "Story Description", stakeholder1, developer1, 5, WorkItemType.STORY, Status.TODO);
        storyDAO.addStory(story1);
        epic1.addStory(story1);

        System.out.println("Story created and linked to Epic!");

        //Creates Task 
        Task task1 = new Task("Task Title", "Task Description", stakeholder1, null, 3, WorkItemType.TASK, Status.TODO);
        taskDAO.addTask(task1);
        story1.addSubtask(task1);

        System.out.println("Task created and added to Story!");

        //Assign developer to task
        workItemService.assignUserToWorkItem(task1, developer1);
        System.out.println("Task assigned to Developer!");

        // Change task status
        workItemService.changeWorkItemStatus(task1, Status.IN_PROGRESS);
        System.out.println("Task moved to IN_PROGRESS!");

        // QA verifies task 
        workItemService.changeWorkItemStatus(task1, Status.DONE);
        ((QAEngineer) qa1).verifyTask(task1);
        System.out.println("Task verified by QA!");

        // Scrummaster creates a sprint 
        Sprint sprint = new Sprint("SP1", LocalDate.now(), LocalDate.now().plusDays(7), "Finish user login");
        sprintDAO.addSprint(sprint);
        sprint.addTeamMember(developer1);
        sprint.addWorkItem(task1);

        System.out.println("Sprint created and Task added!");


        System.out.println(sprint.getBacklog());
        //results
        System.out.println("Epic: " + epic1.getTitle());
        System.out.println("Story: " + story1.getTitle());
        System.out.println("Task Status: " + task1.getStatus());
        System.out.println("Sprint Progress: " + sprint.calculateProgress() + "%");

        System.out.println("\n Everything is working successfully");

        //System.out.println(Database.users);
    }
}
