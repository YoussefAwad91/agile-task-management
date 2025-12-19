package gui;

import service.AuthService;
import entities.users.Developer;
import entities.users.QAEngineer;
import entities.users.ScrumMaster;
import entities.users.Stakeholder;
import entities.users.User;
import database.Database;
import entities.workitems.*;
import entities.Enum.Status;
import entities.Enum.Severity;
import entities.Enum.WorkItemType;
import entities.Sprint;
import util.IDGenerator;
import java.time.LocalDate;

import gui.scenes.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private AuthService authService = new AuthService();
    private User loggedInUser = null;
    
    // View instances
    private LoginView loginView;
    private RegisterView registerView;
    private StakeholderView stakeholderView;
    private ScrumMasterView scrumMasterView;
    private QAEngineerView qaEngineerView;
    private DeveloperView developerView;

    @Override
    public void start(Stage stage) {
        loginView = new LoginView();
        registerView = new RegisterView();
        stakeholderView = new StakeholderView();
        scrumMasterView = new ScrumMasterView();
        qaEngineerView = new QAEngineerView();
        developerView = new DeveloperView();

        // control
        // ---------------- sign in ----------------
        (loginView.getSignInButton()).setOnAction(event -> {
            User user = authService.login((loginView.getUsernameField()).getText(), (loginView.getPasswordField()).getText());
            if (user != null) {
                loggedInUser = user;
                System.out.println(Database.epics);
                (loginView.getErrorLabel()).setText("");
                Scene scene = null;
                if(loggedInUser instanceof Developer){
                    populateDeveloperComboBoxes();
                    scene = new Scene(developerView.getRoot(), 500, 400);
                    scene.getStylesheets().add(
                        getClass().getResource("dark.css").toExternalForm()
                    );
                    stage.setTitle("Developer");
                }
                else if (loggedInUser instanceof QAEngineer){
                    populateQAComboBoxes();
                    scene = new Scene(qaEngineerView.getRoot(), 600, 650);
                    scene.getStylesheets().add(
                        getClass().getResource("dark.css").toExternalForm()
                    );
                    stage.setTitle("QA Engineer");
                }
                else if (loggedInUser instanceof ScrumMaster){
                    populateScrumMasterComboBoxes();
                    scene = new Scene(scrumMasterView.getRoot(), 900, 700);
                    scene.getStylesheets().add(
                        getClass().getResource("dark.css").toExternalForm()
                    );
                    stage.setTitle("Scrum Master");
                }
                else if (loggedInUser instanceof Stakeholder){
                    populateStakeholderComboBoxes();
                    scene = new Scene(stakeholderView.getRoot(), 600, 600);
                    scene.getStylesheets().add(
                        getClass().getResource("dark.css").toExternalForm()
                    );
                    stage.setTitle("Stakeholder");
                }
                else{
                    scene = new Scene(loginView.getRoot(), 500, 300);
                    scene.getStylesheets().add(
                        getClass().getResource("dark.css").toExternalForm()
                    );
                    stage.setTitle("Login");
                }
                stage.setScene(scene);
                stage.show();
            } else {
               (loginView.getErrorLabel()).setText("Username doesn't exist or password incorrect");
            }
        });

        (loginView.getRegisterButton()).setOnAction(event -> {
            Scene scene = new Scene(registerView.getRoot(), 600, 400);
            scene.getStylesheets().add(
                getClass().getResource("dark.css").toExternalForm()
            );
            stage.setTitle("Register");
            stage.setScene(scene);
        });

        // ---------------- register ----------------
        (registerView.getRegisterButton()).setOnAction(event ->{
            User user = null;
            switch((registerView.getRoleBox()).getValue()){
                case STAKEHOLDER:
                    user = new Stakeholder(
                        (registerView.getNameField()).getText(),
                        (registerView.getEmailField()).getText(),
                        (registerView.getUsernameField()).getText(),
                        (registerView.getPasswordField()).getText(), 
                        (registerView.getRoleBox()).getValue());
                    break;
                case QA:
                    user = new QAEngineer(
                        (registerView.getNameField()).getText(),
                        (registerView.getEmailField()).getText(),
                        (registerView.getUsernameField()).getText(),
                        (registerView.getPasswordField()).getText(), 
                        (registerView.getRoleBox()).getValue());
                    break;
                case DEVELOPER:
                    user = new Developer(
                        (registerView.getNameField()).getText(),
                        (registerView.getEmailField()).getText(),
                        (registerView.getUsernameField()).getText(),
                        (registerView.getPasswordField()).getText(), 
                        (registerView.getRoleBox()).getValue());
                    break;
                case SCRUM_MASTER:
                    user = new ScrumMaster(
                        (registerView.getNameField()).getText(),
                        (registerView.getEmailField()).getText(),
                        (registerView.getUsernameField()).getText(),
                        (registerView.getPasswordField()).getText(), 
                        (registerView.getRoleBox()).getValue());
                    break;
                case null:
                    break;
            }
            if(authService.register(user)){
                registerView.getNameField().setText("");
                registerView.getEmailField().setText("");
                registerView.getUsernameField().setText("");
                registerView.getPasswordField().setText("");

                Scene scene = new Scene(loginView.getRoot(), 500, 300);
                scene.getStylesheets().add(
                    getClass().getResource("dark.css").toExternalForm()
                );
                stage.setTitle("Login");
                stage.setScene(scene);
            }
            else{
                registerView.getUsernameField().setText("");
                (registerView.getErrorLabel()).setText("Username already exists");
            }
        });

        // --------- developer ----------
        (developerView.getViewMyTasksButton()).setOnAction(event ->{
            if (loggedInUser == null) return;
            StringBuilder tasks_string = new StringBuilder();
            developerView.getTaskComboBox().getItems().clear();
            int assignedCount = 0;
            for (Task task: Database.tasks){
                if(task.getAssignedTo() == loggedInUser && task.getStatus() != Status.DONE && task.getStatus() != Status.VERIFIED){
                    tasks_string.append(task.getTitle()).append("\n");
                    developerView.getTaskComboBox().getItems().add(task.getTitle());
                    assignedCount++;
                }
            }
            developerView.getTasksDisplayArea().setText(tasks_string.toString());
            // update welcome and capacity info if available
            developerView.getWelcomeLabel().setText("Welcome, " + loggedInUser.getUsername());
            developerView.getCapacityLabel().setText("Assigned tasks: " + assignedCount);
        });

        (developerView.getViewSprintsButton()).setOnAction(event ->{
            if (loggedInUser == null) return;
            StringBuilder sprints_string = new StringBuilder();
            for (Sprint sprint: Database.sprints){
                if (sprint.containsUser(loggedInUser)){
                    sprints_string.append("Sprint ID: ").append(sprint.getId())
                        .append(" - ").append(sprint.getObjective())
                        .append("\n");
                }
            }
            developerView.getSprintsDisplayArea().setText(sprints_string.toString());
        });

        (developerView.getChangeTaskStatusButton()).setOnAction(event ->{
            if (loggedInUser == null) return;
            String selectedTitle = developerView.getTaskComboBox().getValue();
            String selectedStatus = developerView.getStatusComboBox().getValue();
            if (selectedTitle == null || selectedStatus == null){
                developerView.getStatusLabel().setText("Please select a task and a status.");
                return;
            }
            Task target = null;
            for (Task task: Database.tasks){
                if(task.getAssignedTo() == loggedInUser && selectedTitle.equals(task.getTitle())){
                    target = task;
                    break;
                }
            }
            if (target == null){
                developerView.getStatusLabel().setText("Selected task not found or not assigned to you.");
                return;
            }
            try{
                Status newStatus = Status.valueOf(selectedStatus);
                if (loggedInUser instanceof Developer){
                    ((Developer) loggedInUser).changeTaskStatus(target, newStatus);
                } else {
                    target.changeStatus(newStatus);
                }
                developerView.getStatusLabel().setText("Task '" + target.getTitle() + "' updated to " + newStatus);
                // refresh lists
                developerView.getViewMyTasksButton().fire();
                developerView.getViewSprintsButton().fire();
                developerView.clearStatusForm();
            } catch (IllegalArgumentException ex){
                developerView.getStatusLabel().setText("Invalid status selected.");
            }
        });

        (developerView.getLogoutButton()).setOnAction(event ->{
            loggedInUser = null;
            Scene scene = new Scene(loginView.getRoot(), 500, 300);
            scene.getStylesheets().add(
                getClass().getResource("dark.css").toExternalForm()
            );
            stage.setTitle("Login");
            stage.setScene(scene);
        });

        // --------------- qa -----------------
        (qaEngineerView.getViewTasksButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            StringBuilder tasks_string = new StringBuilder();
            qaEngineerView.getVerifyTaskComboBox().getItems().clear();
            qaEngineerView.getBugTaskComboBox().getItems().clear();
            for (Task task : Database.tasks) {
                if (task.getStatus() == Status.DONE) {
                    tasks_string.append(task.toString()).append("\n");
                    qaEngineerView.getVerifyTaskComboBox().getItems().add(task.getTitle());
                    qaEngineerView.getBugTaskComboBox().getItems().add(task.getTitle());
                }
            }
            qaEngineerView.getTasksDisplayArea().setText(tasks_string.toString());
            qaEngineerView.getWelcomeLabel().setText("Welcome, " + loggedInUser.getUsername());
        });

        (qaEngineerView.getViewBugsButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            StringBuilder bugs_string = new StringBuilder();
            for (Bug bug : Database.bugs) {
                if (bug.getReporter() == loggedInUser) {
                    bugs_string.append("ID: ").append(bug.getId()).append(" - ")
                        .append(bug.getTitle()).append(" (Severity: ").append(bug.getSeverity()).append(")\n")
                        .append(bug.getDescription()).append("\n\n");
                }
            }
            qaEngineerView.getBugsDisplayArea().setText(bugs_string.toString());
        });

        (qaEngineerView.getVerifyTaskButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String selected = qaEngineerView.getVerifyTaskComboBox().getValue();
            if (selected == null) {
                qaEngineerView.getStatusLabel().setText("Please select a task to verify.");
                return;
            }
            Task target = null;
            for (Task t : Database.tasks) {
                if (t.getTitle().equals(selected) && t.getStatus() == Status.DONE) {
                    target = t;
                    break;
                }
            }
            if (target == null) {
                qaEngineerView.getStatusLabel().setText("Task not found or not in DONE status.");
                return;
            }
            target.changeStatus(Status.VERIFIED);
            qaEngineerView.getStatusLabel().setText("Task '" + target.getTitle() + "' marked VERIFIED.");
            qaEngineerView.getViewTasksButton().fire();
        });

        (qaEngineerView.getAssignTaskToQAButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String selected = qaEngineerView.getAssignTaskComboBox().getValue();
            if (selected == null) {
                qaEngineerView.getStatusLabel().setText("Please select a task to assign.");
                return;
            }
            Task target = null;
            for (Task t : Database.tasks) {
                if (t.getTitle().equals(selected) && t.getStatus() == Status.DONE) {
                    target = t;
                    break;
                }
            }
            if (target == null) {
                qaEngineerView.getStatusLabel().setText("Task not found or not in DONE status.");
                return;
            }
            target.setAssignedTo(loggedInUser);
            qaEngineerView.getStatusLabel().setText("Task '" + target.getTitle() + "' assigned to you for testing.");
            qaEngineerView.clearAssignForm();
            qaEngineerView.getViewTasksButton().fire();
        });

        (qaEngineerView.getReportBugButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String relatedTitle = qaEngineerView.getBugTaskComboBox().getValue();
            String title = qaEngineerView.getBugTitleField().getText();
            String desc = qaEngineerView.getBugDescriptionArea().getText();
            String sev = qaEngineerView.getSeverityComboBox().getValue();
            String hrsText = qaEngineerView.getBugEstimatedHoursField().getText();
            int hrs = 0;
            try { hrs = Integer.parseInt(hrsText); } catch (NumberFormatException ex) { hrs = 0; }
            Task related = null;
            for (Task t : Database.tasks) {
                if (t.getTitle().equals(relatedTitle)) { related = t; break; }
            }
            User assignedTo = (related == null) ? null : related.getAssignedTo();
            if (title == null || title.isEmpty()) {
                qaEngineerView.getStatusLabel().setText("Bug title required.");
                return;
            }
            Severity severity = Severity.LOW;
            try { if (sev != null) severity = Severity.valueOf(sev); } catch (IllegalArgumentException ex) { severity = Severity.LOW; }
            Bug bug = new Bug(title, desc, Status.TODO, loggedInUser, assignedTo, hrs, loggedInUser, severity);
            qaEngineerView.getStatusLabel().setText("Bug reported: " + title);
            qaEngineerView.clearBugForm();
            refreshAllComboBoxes();
            qaEngineerView.getViewBugsButton().fire();
        });

        (qaEngineerView.getLogoutButton()).setOnAction(event -> {
            loggedInUser = null;
            Scene scene = new Scene(loginView.getRoot(), 500, 300);
            scene.getStylesheets().add(
                getClass().getResource("dark.css").toExternalForm()
            );
            stage.setTitle("Login");
            stage.setScene(scene);
        });

        // ------------- scrummaster --------------
        (scrumMasterView.getCreateSprintButton()).setOnAction(event -> {
            String title = scrumMasterView.getSprintTitleField().getText();
            String objective = scrumMasterView.getSprintObjectiveField().getText();
            LocalDate start = scrumMasterView.getStartDatePicker().getValue();
            LocalDate end = scrumMasterView.getEndDatePicker().getValue();
            if (title == null || title.isEmpty() || objective == null || objective.isEmpty() || start == null || end == null) {
                scrumMasterView.getStatusLabel().setText("Please fill all fields: title, objective, start and end dates.");
                return;
            }
            String id = IDGenerator.generateID(new Sprint());
            Sprint sprint = new Sprint(id, title, start, end, objective);
            scrumMasterView.getStatusLabel().setText("Created sprint " + sprint.getId());
            scrumMasterView.clearSprintForm();
            refreshAllComboBoxes();
            scrumMasterView.getViewSprintsButton().fire();
        });

        (scrumMasterView.getCreateTaskButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String title = scrumMasterView.getTaskTitleField().getText();
            String desc = scrumMasterView.getTaskDescriptionArea().getText();
            String hrsText = scrumMasterView.getTaskEstimatedHoursField().getText();
            int hrs = 0; try { hrs = Integer.parseInt(hrsText); } catch (NumberFormatException ex) { hrs = 0; }
            if (title == null || title.isEmpty()) { scrumMasterView.getStatusLabel().setText("Task title required."); return; }
            
            // Get assigned developer if selected
            String selectedDeveloper = scrumMasterView.getAssignDeveloperComboBox().getValue();
            User assignedDev = null;
            if (selectedDeveloper != null && !selectedDeveloper.isEmpty()) {
                for (User u : Database.users) {
                    if (u.getUsername().equals(selectedDeveloper) && u instanceof Developer) {
                        assignedDev = u;
                        break;
                    }
                }
            }
            
            Task task = new Task(title, desc, loggedInUser, assignedDev, hrs, WorkItemType.TASK, Status.TODO);
            scrumMasterView.getStatusLabel().setText("Created task: " + task.getTitle());
            scrumMasterView.clearTaskForm();
            refreshAllComboBoxes();
            scrumMasterView.getViewSprintsButton().fire();
        });

        (scrumMasterView.getViewSprintsButton()).setOnAction(event -> {
            StringBuilder sb = new StringBuilder();
            scrumMasterView.getWorkItemSprintComboBox().getItems().clear();
            scrumMasterView.getUserSprintComboBox().getItems().clear();
            for (Sprint s : Database.sprints) {
                sb.append("ID: ").append(s.getId()).append(" - ").append(s.getTitle())
                  .append(" | ").append(s.getObjective())
                  .append(" (Backlog: ").append(s.getBacklog().size()).append(")\n");
                scrumMasterView.getWorkItemSprintComboBox().getItems().add(s.getId());
                scrumMasterView.getUserSprintComboBox().getItems().add(s.getId());
            }
            scrumMasterView.getSprintsDisplayArea().setText(sb.toString());
            // populate users and work items for assignment forms
            scrumMasterView.getUserComboBox().getItems().clear();
            scrumMasterView.getWorkItemComboBox().getItems().clear();
            scrumMasterView.getReopenWorkItemComboBox().getItems().clear();
            for (User u : Database.users) scrumMasterView.getUserComboBox().getItems().add(u.getUsername());
            for (Epic e : Database.epics) scrumMasterView.getWorkItemComboBox().getItems().add(e.getTitle());
            for (Story st : Database.stories) scrumMasterView.getWorkItemComboBox().getItems().add(st.getTitle());
            for (Task t : Database.tasks) scrumMasterView.getWorkItemComboBox().getItems().add(t.getTitle());
            for (Bug b : Database.bugs) scrumMasterView.getWorkItemComboBox().getItems().add(b.getTitle());
            // reopen list
            for (Epic e : Database.epics) scrumMasterView.getReopenWorkItemComboBox().getItems().add(e.getTitle());
            for (Story st : Database.stories) scrumMasterView.getReopenWorkItemComboBox().getItems().add(st.getTitle());
            for (Task t : Database.tasks) scrumMasterView.getReopenWorkItemComboBox().getItems().add(t.getTitle());
            for (Bug b : Database.bugs) scrumMasterView.getReopenWorkItemComboBox().getItems().add(b.getTitle());
        });

        (scrumMasterView.getAssignWorkItemButton()).setOnAction(event -> {
            String workItemTitle = scrumMasterView.getWorkItemComboBox().getValue();
            String sprintId = scrumMasterView.getWorkItemSprintComboBox().getValue();
            if (workItemTitle == null || sprintId == null) {
                scrumMasterView.getStatusLabel().setText("Select work item and sprint.");
                return;
            }
            WorkItem found = null;
            for (Epic e : Database.epics) if (e.getTitle().equals(workItemTitle)) { found = e; break; }
            for (Story s : Database.stories) if (found==null && s.getTitle().equals(workItemTitle)) { found = s; break; }
            for (Task t : Database.tasks) if (found==null && t.getTitle().equals(workItemTitle)) { found = t; break; }
            for (Bug b : Database.bugs) if (found==null && b.getTitle().equals(workItemTitle)) { found = b; break; }
            Sprint targetSprint = null;
            for (Sprint s : Database.sprints) if (s.getId().equals(sprintId)) { targetSprint = s; break; }
            if (found == null || targetSprint == null) {
                scrumMasterView.getStatusLabel().setText("Work item or sprint not found.");
                return;
            }
            targetSprint.addWorkItem(found);
            scrumMasterView.getStatusLabel().setText("Assigned " + workItemTitle + " to sprint " + sprintId);
            scrumMasterView.getViewSprintsButton().fire();
        });

        (scrumMasterView.getAssignUserButton()).setOnAction(event -> {
            String username = scrumMasterView.getUserComboBox().getValue();
            String sprintId = scrumMasterView.getUserSprintComboBox().getValue();
            if (username == null || sprintId == null) {
                scrumMasterView.getStatusLabel().setText("Select user and sprint.");
                return;
            }
            User found = null;
            for (User u : Database.users) if (u.getUsername().equals(username)) { found = u; break; }
            Sprint targetSprint = null;
            for (Sprint s : Database.sprints) if (s.getId().equals(sprintId)) { targetSprint = s; break; }
            if (found == null || targetSprint == null) {
                scrumMasterView.getStatusLabel().setText("User or sprint not found.");
                return;
            }
            targetSprint.addTeamMember(found);
            scrumMasterView.getStatusLabel().setText("Assigned user " + username + " to sprint " + sprintId);
            scrumMasterView.getViewSprintsButton().fire();
        });

        (scrumMasterView.getReopenWorkItemButton()).setOnAction(event -> {
            String item = scrumMasterView.getReopenWorkItemComboBox().getValue();
            if (item == null) { scrumMasterView.getStatusLabel().setText("Select work item to reopen."); return; }
            WorkItem found = null;
            for (Epic e : Database.epics) if (e.getTitle().equals(item)) { found = e; break; }
            for (Story s : Database.stories) if (found==null && s.getTitle().equals(item)) { found = s; break; }
            for (Task t : Database.tasks) if (found==null && t.getTitle().equals(item)) { found = t; break; }
            for (Bug b : Database.bugs) if (found==null && b.getTitle().equals(item)) { found = b; break; }
            if (found == null) { scrumMasterView.getStatusLabel().setText("Work item not found."); return; }
            found.changeStatus(Status.REOPENED);
            scrumMasterView.getStatusLabel().setText("Reopened " + item);
            scrumMasterView.getViewSprintsButton().fire();
        });

        (scrumMasterView.getViewProgressButton()).setOnAction(event -> {
            StringBuilder sb = new StringBuilder();
            for (Sprint s : Database.sprints) {
                sb.append("Sprint ").append(s.getId()).append(": ").append(s.calculateProgress()).append(" %\n");
            }
            scrumMasterView.getProgressDisplayArea().setText(sb.toString());
        });

        (scrumMasterView.getLogoutButton()).setOnAction(event -> {
            loggedInUser = null;
            Scene scene = new Scene(loginView.getRoot(), 500, 300);
            scene.getStylesheets().add(
                getClass().getResource("dark.css").toExternalForm()
            );
            stage.setTitle("Login");
            stage.setScene(scene);
        });

        // -------------- stakeholder --------------
        (stakeholderView.getCreateEpicButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String title = stakeholderView.getEpicTitleField().getText();
            String desc = stakeholderView.getEpicDescriptionArea().getText();
            String hrsText = stakeholderView.getEpicEstimatedHoursField().getText();
            int hrs = 0; try { hrs = Integer.parseInt(hrsText); } catch (NumberFormatException ex) { hrs = 0; }
            if (title == null || title.isEmpty()) { stakeholderView.getStatusLabel().setText("Epic title required."); return; }
            Epic epic = new Epic(title, desc, loggedInUser, null, hrs, WorkItemType.EPIC, Status.TODO);
            stakeholderView.getStatusLabel().setText("Created epic: " + epic.getTitle());
            stakeholderView.clearEpicForm();
            refreshAllComboBoxes();
            stakeholderView.getViewMyItemsButton().fire();
        });

        (stakeholderView.getCreateStoryButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            String title = stakeholderView.getStoryTitleField().getText();
            String desc = stakeholderView.getStoryDescriptionArea().getText();
            String hrsText = stakeholderView.getStoryEstimatedHoursField().getText();
            String parent = stakeholderView.getParentEpicComboBox().getValue();
            int hrs = 0; try { hrs = Integer.parseInt(hrsText); } catch (NumberFormatException ex) { hrs = 0; }
            if (title == null || title.isEmpty()) { stakeholderView.getStatusLabel().setText("Story title required."); return; }
            Story story = new Story(title, desc, loggedInUser, null, hrs, WorkItemType.STORY, Status.TODO);
            // attach to parent epic if selected
            if (parent != null) {
                for (Epic e : Database.epics) {
                    if (e.getTitle().equals(parent)) { e.addStory(story); break; }
                }
            }
            stakeholderView.getStatusLabel().setText("Created story: " + story.getTitle());
            stakeholderView.clearStoryForm();
            refreshAllComboBoxes();
            stakeholderView.getViewMyItemsButton().fire();
        });


        (stakeholderView.getViewMyItemsButton()).setOnAction(event -> {
            if (loggedInUser == null) return;
            StringBuilder sb = new StringBuilder();
            stakeholderView.getParentEpicComboBox().getItems().clear();
            for (Epic e : Database.epics) {
                if (e.getcreatedby() == loggedInUser) sb.append("Epic: ").append(e.getTitle()).append("\n");
                stakeholderView.getParentEpicComboBox().getItems().add(e.getTitle());
            }
            for (Story s : Database.stories) {
                if (s.getcreatedby() == loggedInUser) sb.append("Story: ").append(s.getTitle()).append("\n");
            }
            stakeholderView.getDisplayArea().setText(sb.toString());
            stakeholderView.getWelcomeLabel().setText("Welcome, " + loggedInUser.getUsername());
        });

        (stakeholderView.getLogoutButton()).setOnAction(event -> {
            loggedInUser = null;
            Scene scene = new Scene(loginView.getRoot(), 500, 300);
            scene.getStylesheets().add(
                getClass().getResource("dark.css").toExternalForm()
            );
            stage.setTitle("Login");
            stage.setScene(scene);
        });



        Scene scene = new Scene(loginView.getRoot(), 500, 300);
        scene.getStylesheets().add(
            getClass().getResource("dark.css").toExternalForm()
        );

        //scene = new Scene(registerView.getRoot(), 500, 300);
        //scene = new Scene(stakeholderView.getRoot(), 600, 600);
        //scene = new Scene(scrumMasterView.getRoot(), 700, 700);
        //scene = new Scene(developerView.getRoot(), 500, 400);
        //scene = new Scene(qaEngineerView.getRoot(), 600, 650);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void populateDeveloperComboBoxes() {
        developerView.getStatusComboBox().getItems().clear();
        developerView.getStatusComboBox().getItems().addAll("TODO", "IN_PROGRESS", "DONE");
        developerView.getTaskComboBox().getItems().clear();
        for (Task task : Database.tasks) {
            if (task.getAssignedTo() == loggedInUser) {
                developerView.getTaskComboBox().getItems().add(task.getTitle());
            }
        }
    }

    private void populateQAComboBoxes() {
        qaEngineerView.getVerifyTaskComboBox().getItems().clear();
        qaEngineerView.getAssignTaskComboBox().getItems().clear();
        qaEngineerView.getBugTaskComboBox().getItems().clear();

        for (Task task : Database.tasks) {
            if (task.getStatus() == Status.DONE) {
                qaEngineerView.getVerifyTaskComboBox().getItems().add(task.getTitle());
                qaEngineerView.getAssignTaskComboBox().getItems().add(task.getTitle());
                qaEngineerView.getBugTaskComboBox().getItems().add(task.getTitle());
            }
        }
    }

    private void populateScrumMasterComboBoxes() {
        scrumMasterView.getWorkItemComboBox().getItems().clear();
        scrumMasterView.getWorkItemSprintComboBox().getItems().clear();
        scrumMasterView.getUserComboBox().getItems().clear();
        scrumMasterView.getUserSprintComboBox().getItems().clear();
        scrumMasterView.getReopenWorkItemComboBox().getItems().clear();
        scrumMasterView.getParentStoryComboBox().getItems().clear();
        scrumMasterView.getAssignDeveloperComboBox().getItems().clear();
        
        for (Sprint s : Database.sprints) {
            scrumMasterView.getWorkItemSprintComboBox().getItems().add(s.getId());
            scrumMasterView.getUserSprintComboBox().getItems().add(s.getId());
        }
        for (User u : Database.users) {
            scrumMasterView.getUserComboBox().getItems().add(u.getUsername());
            if (u instanceof Developer) {
                scrumMasterView.getAssignDeveloperComboBox().getItems().add(u.getUsername());
            }
        }
        for (Epic e : Database.epics) {
            scrumMasterView.getWorkItemComboBox().getItems().add(e.getTitle());
            scrumMasterView.getReopenWorkItemComboBox().getItems().add(e.getTitle());
        }
        for (Story st : Database.stories) {
            scrumMasterView.getWorkItemComboBox().getItems().add(st.getTitle());
            scrumMasterView.getReopenWorkItemComboBox().getItems().add(st.getTitle());
            scrumMasterView.getParentStoryComboBox().getItems().add(st.getTitle());
        }
        for (Task t : Database.tasks) {
            scrumMasterView.getWorkItemComboBox().getItems().add(t.getTitle());
            scrumMasterView.getReopenWorkItemComboBox().getItems().add(t.getTitle());
        }
        for (Bug b : Database.bugs) {
            scrumMasterView.getWorkItemComboBox().getItems().add(b.getTitle());
            scrumMasterView.getReopenWorkItemComboBox().getItems().add(b.getTitle());
        }
    }

    private void populateStakeholderComboBoxes() {
        stakeholderView.getParentEpicComboBox().getItems().clear();
        for (Epic e : Database.epics) {
            stakeholderView.getParentEpicComboBox().getItems().add(e.getTitle());
        }
    }

    private void refreshAllComboBoxes() {
        populateDeveloperComboBoxes();
        populateQAComboBoxes();
        populateScrumMasterComboBoxes();
        populateStakeholderComboBoxes();
    }

    public static void main(String[] args) {
        Database.initialize();
        launch(args);
    }
}
