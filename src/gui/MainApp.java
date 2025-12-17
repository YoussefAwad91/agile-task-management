package gui;

import service.AuthService;
import entities.users.Developer;
import entities.users.QAEngineer;
import entities.users.ScrumMaster;
import entities.users.Stakeholder;
import entities.users.User;
import database.Database;
import entities.workitems.*;

import gui.scenes.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private AuthService authService = new AuthService();
    private User loggedInUser = null;

    @Override
    public void start(Stage stage) {
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        StakeholderView stakeholderView = new StakeholderView();
        ScrumMasterView scrumMasterView = new ScrumMasterView();
        QAEngineerView qaEngineerView = new QAEngineerView();
        DeveloperView developerView = new DeveloperView();

        // control
        // ---------------- sign in ----------------
        (loginView.getSignInButton()).setOnAction(event -> {
            User user = authService.login((loginView.getUsernameField()).getText(), (loginView.getPasswordField()).getText());
            if (user != null) {
                loggedInUser = user;
                (loginView.getErrorLabel()).setText("");
                System.out.println(loggedInUser.toString());
                Scene scene = null;
                if(loggedInUser instanceof Developer){
                    scene = new Scene(developerView.getRoot(), 500, 400);
                }
                else if (loggedInUser instanceof QAEngineer){
                    scene = new Scene(qaEngineerView.getRoot(), 600, 650);
                }
                else if (loggedInUser instanceof ScrumMaster){
                    scene = new Scene(scrumMasterView.getRoot(), 700, 500);
                }
                else if (loggedInUser instanceof Stakeholder){
                    scene = new Scene(stakeholderView.getRoot(), 600, 600);
                }
                else{
                    scene = new Scene(loginView.getRoot(), 500, 300);
                }
                stage.setScene(scene);
                stage.show();
            } else {
               (loginView.getErrorLabel()).setText("Username doesn't exist or password incorrect");
            }
        });

        (loginView.getRegisterButton()).setOnAction(event -> {
            Scene scene = new Scene(registerView.getRoot(), 600, 400);
            stage.setTitle("Register User");
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
            ArrayList<Task> tasks = ((Developer)loggedInUser).getAssignedTasks();
            String tasks_string = "";
            for (Task task: tasks){
                tasks_string += task.getTitle();
            }
            (developerView.getTasksDisplayArea()).setText(tasks_string);

        });

        (developerView.getLogoutButton()).setOnAction(event ->{
            loggedInUser = null;
            Scene scene = new Scene(loginView.getRoot(), 500, 300);
            stage.setScene(scene);
        });




        Scene scene = new Scene(loginView.getRoot(), 500, 300);
        //scene = new Scene(registerView.getRoot(), 500, 300);
        //scene = new Scene(stakeholderView.getRoot(), 600, 600);
        //scene = new Scene(scrumMasterView.getRoot(), 700, 500);
        //scene = new Scene(developerView.getRoot(), 500, 400);
        //scene = new Scene(qaEngineerView.getRoot(), 600, 650);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.initialize();
        launch(args);
    }
}
