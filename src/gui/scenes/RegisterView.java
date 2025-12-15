package gui.scenes;

import entities.Enum.UserRole;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class RegisterView {

    private final TextField nameField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final ChoiceBox<UserRole> roleBox = new ChoiceBox<>();
    private final Label errorLabel = new Label();
    private final Button registerButton = new Button();

    public RegisterView(){
        roleBox.getItems().addAll(UserRole.values());
    }

    public Parent getRoot() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 400);

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);

        // ---------------- GridPane Form ----------------
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 60, 5, 30));
        grid.setPrefSize(442, 350);

        // Column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(10);
        col1.setPrefWidth(171);
        col1.setMaxWidth(295.5);
        col1.setHgrow(Priority.SOMETIMES);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMinWidth(10);
        col2.setPrefWidth(429);
        col2.setMaxWidth(439);
        col2.setHgrow(Priority.SOMETIMES);

        grid.getColumnConstraints().addAll(col1, col2);

        // Row constraints (5 rows)
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(10);
            row.setPrefHeight(30);
            row.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(row);
        }

        // Labels
        Label nameLabel = label("Name");
        Label emailLabel = label("Email");
        Label usernameLabel = label("Username");
        Label passwordLabel = label("Password");
        Label roleLabel = label("Role");

        GridPane.setHalignment(nameLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(emailLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(usernameLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(roleLabel, javafx.geometry.HPos.RIGHT);

        GridPane.setRowIndex(emailLabel, 1);
        GridPane.setRowIndex(usernameLabel, 2);
        GridPane.setRowIndex(passwordLabel, 3);
        GridPane.setRowIndex(roleLabel, 4);

        // Input fields
        
        roleBox.setValue(UserRole.STAKEHOLDER);

        GridPane.setColumnIndex(nameField, 1);
        GridPane.setColumnIndex(emailField, 1);
        GridPane.setColumnIndex(usernameField, 1);
        GridPane.setColumnIndex(passwordField, 1);
        GridPane.setColumnIndex(roleBox, 1);
        GridPane.setRowIndex(emailField, 1);
        GridPane.setRowIndex(usernameField, 2);
        GridPane.setRowIndex(passwordField, 3);
        GridPane.setRowIndex(roleBox, 4);

        grid.getChildren().addAll(
                nameLabel, emailLabel, usernameLabel, passwordLabel, roleLabel,
                nameField, emailField, usernameField, passwordField, roleBox
        );

        // ---------------- Error Label ----------------
        errorLabel.setTextFill(javafx.scene.paint.Color.web("#d01f1f"));
        HBox errorBox = new HBox(errorLabel);
        errorBox.setAlignment(Pos.TOP_CENTER);

        // ---------------- Buttons ----------------
        registerButton.setText("Register User");
        registerButton.setFont(new Font(18));
        HBox buttonBox = new HBox(registerButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPrefHeight(85);

        // ---------------- Assemble ----------------
        centerBox.getChildren().addAll(grid, errorBox, buttonBox);
        root.setCenter(centerBox);

        return root;
    }

    private Label label(String text) {
        Label lbl = new Label(text);
        lbl.setFont(new Font(18));
        return lbl;
    }

    // Getters to use in controller logic
    public Button getRegisterButton() { return registerButton; }
    public TextField getNameField() { return nameField; }
    public TextField getEmailField() { return emailField; }
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public ChoiceBox<UserRole> getRoleBox() { return roleBox; }
    public Label getErrorLabel() { return errorLabel; }
}
