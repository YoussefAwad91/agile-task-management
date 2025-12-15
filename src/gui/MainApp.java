package gui;

import service.AuthService;
import entities.users.Developer;
import entities.users.QAEngineer;
import entities.users.ScrumMaster;
import entities.users.Stakeholder;
import entities.users.User;
import database.Database;

import gui.scenes.*;

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

        // control
        // ---------------- sign in ----------------
        (loginView.getSignInButton()).setOnAction(event -> {
            User user = authService.login((loginView.getUsernameField()).getText(), (loginView.getPasswordField()).getText());
            if (user != null) {
                loggedInUser = user;
                (loginView.getErrorLabel()).setText("");
                System.out.println(loggedInUser.toString());
                // todo: switch to appropriate scene
            } else {
               (loginView.getErrorLabel()).setText("Username doesn't exist or password incorrect");
            }
        });

        (loginView.getRegisterButton()).setOnAction(event -> {
            Scene scene = new Scene(registerView.getRoot(), 600, 400);
            stage.setTitle("Register User");
            stage.setScene(scene);
            stage.show();
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
                (registerView.getErrorLabel()).setText("Username not unique");
            }

            




        });







        Scene scene = new Scene(loginView.getRoot(), 500, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.initialize();
        launch(args);
    }
}
