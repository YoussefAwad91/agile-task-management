package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class LoginView {

    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button signInButton = new Button("Sign In");
    private final Button registerButton = new Button("Register");
    private final Label errorLabel = new Label();

    public Parent getRoot() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 400);

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);

        // ---------------- GridPane ----------------
        GridPane grid = new GridPane();
        grid.setPrefSize(580, 198);
        grid.setPadding(new Insets(60, 40, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(10);
        col1.setPrefWidth(180);
        col1.setMaxWidth(285);
        col1.setHgrow(Priority.SOMETIMES);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMinWidth(10);
        col2.setPrefWidth(400);
        col2.setMaxWidth(400);
        col2.setHgrow(Priority.SOMETIMES);

        grid.getColumnConstraints().addAll(col1, col2);

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10);
        row1.setPrefHeight(54);
        row1.setMaxHeight(79.5);
        row1.setVgrow(Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10);
        row2.setPrefHeight(131.5);
        row2.setMaxHeight(132);
        row2.setVgrow(Priority.SOMETIMES);

        grid.getRowConstraints().addAll(row1, row2);

        // Labels
        Label usernameLabel = createLabel("Username");
        Label passwordLabel = createLabel("Password");
        GridPane.setRowIndex(passwordLabel, 1);

        // Fields
        usernameField.setPrefSize(287, 39);
        usernameField.setFont(new Font(18));
        GridPane.setColumnIndex(usernameField, 1);

        passwordField.setFont(new Font(18));
        GridPane.setColumnIndex(passwordField, 1);
        GridPane.setRowIndex(passwordField, 1);

        grid.getChildren().addAll(usernameLabel, passwordLabel, usernameField, passwordField);

        // ---------------- Buttons ----------------
        HBox buttonBox = new HBox(20, signInButton, registerButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPrefSize(600, 68);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));
        signInButton.setFont(new Font(14));
        registerButton.setFont(new Font(14));

        // ---------------- Error Label ----------------
        errorLabel.setTextFill(javafx.scene.paint.Color.web("#d71f1f"));
        errorLabel.setAlignment(Pos.CENTER);

        // Assemble
        centerBox.getChildren().addAll(grid, buttonBox, errorLabel);
        root.setCenter(centerBox);

        return root;
    }

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.setFont(new Font(18));
        GridPane.setHalignment(lbl, javafx.geometry.HPos.RIGHT);
        GridPane.setMargin(lbl, new Insets(0, 10, 0, 0));
        return lbl;
    }

    // ---------------- Getters ----------------
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getSignInButton() { return signInButton; }
    public Button getRegisterButton() { return registerButton; }
    public Label getErrorLabel() { return errorLabel; }
}
