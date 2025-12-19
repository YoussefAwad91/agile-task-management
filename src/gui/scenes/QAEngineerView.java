package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class QAEngineerView {

    private final Label welcomeLabel = new Label();
    private final Button verifyTaskButton = new Button("Verify Task");
    private final Button reportBugButton = new Button("Report Bug");
    private final Button viewTasksButton = new Button("View Tasks to Verify");
    private final Button viewBugsButton = new Button("View Reported Bugs");
    private final Button assignTaskToQAButton = new Button("Assign Task to Me");
    private final Button logoutButton = new Button("Logout");
    
    // Verify Task Fields
    private final ComboBox<String> verifyTaskComboBox = new ComboBox<>();
    
    // Assign Task to QA Fields
    private final ComboBox<String> assignTaskComboBox = new ComboBox<>();
    
    // Report Bug Fields
    private final ComboBox<String> bugTaskComboBox = new ComboBox<>();
    private final TextField bugTitleField = new TextField();
    private final TextArea bugDescriptionArea = new TextArea();
    private final ComboBox<String> severityComboBox = new ComboBox<>();
    private final TextField bugEstimatedHoursField = new TextField();
    
    // Display areas
    private final TextArea tasksDisplayArea = new TextArea();
    private final TextArea bugsDisplayArea = new TextArea();
    private final Label statusLabel = new Label();

    public QAEngineerView(){
        severityComboBox.getItems().addAll("LOW", "MEDIUM", "HIGH", "CRITICAL");
    }

    public Parent getRoot() {
        BorderPane root = new BorderPane();
        root.setPrefSize(900, 700);

        // Top
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(15, 20, 15, 20));
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(20);
        
        welcomeLabel.setFont(new Font(20));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        logoutButton.setFont(new Font(14));
        
        topBox.getChildren().addAll(welcomeLabel, spacer, logoutButton);
        root.setTop(topBox);

        // Center: (tab pane)
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //tabs
        Tab tasksTab = new Tab("Tasks to Verify");
        tasksTab.setContent(createTasksDisplay());
        
        Tab verifyTab = new Tab("Verify Task");
        verifyTab.setContent(createVerifyTaskForm());
        
        Tab assignTab = new Tab("Assign Task to Me");
        assignTab.setContent(createAssignTaskForm());
        
        Tab bugTab = new Tab("Report Bug");
        bugTab.setContent(createReportBugForm());
        
        Tab bugsTab = new Tab("Reported Bugs");
        bugsTab.setContent(createBugsDisplay());

        tabPane.getTabs().addAll(tasksTab, verifyTab, assignTab, bugTab, bugsTab);
        root.setCenter(tabPane);

        // Bottom
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(10, 20, 10, 20));
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        statusLabel.setFont(new Font(12));
        statusLabel.setTextFill(javafx.scene.paint.Color.web("#2e7d32"));
        bottomBox.getChildren().add(statusLabel);
        root.setBottom(bottomBox);

        return root;
    }

    private VBox createTasksDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("Tasks Ready for Verification (Status: DONE):");
        displayLabel.setFont(new Font(16));

        tasksDisplayArea.setEditable(false);
        tasksDisplayArea.setWrapText(true);
        tasksDisplayArea.setPrefHeight(500);

        Button refreshButton = new Button("Refresh Tasks");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewTasksButton.fire());

        container.getChildren().addAll(displayLabel, tasksDisplayArea, refreshButton);
        return container;
    }

    private VBox createVerifyTaskForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Verify Completed Task:");
        instructionLabel.setFont(new Font(16));

        Label taskLabel = new Label("Select Task:");
        taskLabel.setFont(new Font(14));
        verifyTaskComboBox.setPromptText("Choose a task with status DONE");
        verifyTaskComboBox.setPrefWidth(500);

        Button submitButton = new Button("Mark as VERIFIED");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> verifyTaskButton.fire());

        Label infoLabel = new Label("Note: Only tasks with status DONE can be verified.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        infoLabel.setWrapText(true);

        form.getChildren().addAll(
            instructionLabel,
            taskLabel, verifyTaskComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createAssignTaskForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Assign Task to Yourself for Testing:");
        instructionLabel.setFont(new Font(16));

        Label taskLabel = new Label("Select Task:");
        taskLabel.setFont(new Font(14));
        assignTaskComboBox.setPromptText("Choose a completed task (status DONE)");
        assignTaskComboBox.setPrefWidth(500);

        Button submitButton = new Button("Assign to Me");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> assignTaskToQAButton.fire());

        Label infoLabel = new Label("Note: Only tasks with status DONE can be assigned. This allows you to track testing work.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        infoLabel.setWrapText(true);

        form.getChildren().addAll(
            instructionLabel,
            taskLabel, assignTaskComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createReportBugForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Report Bug:");
        instructionLabel.setFont(new Font(16));

        Label taskLabel = new Label("Related Task:");
        taskLabel.setFont(new Font(14));
        bugTaskComboBox.setPromptText("Select the task with the bug");
        bugTaskComboBox.setPrefWidth(500);

        Label titleLabel = new Label("Bug Title:");
        titleLabel.setFont(new Font(14));
        bugTitleField.setPromptText("Enter bug title");
        bugTitleField.setPrefWidth(500);

        Label descLabel = new Label("Bug Description:");
        descLabel.setFont(new Font(14));
        bugDescriptionArea.setPromptText("Enter detailed bug description");
        bugDescriptionArea.setPrefHeight(150);
        bugDescriptionArea.setWrapText(true);

        Label severityLabel = new Label("Severity:");
        severityLabel.setFont(new Font(14));
        severityComboBox.setPromptText("Select severity level");
        severityComboBox.setPrefWidth(300);
        

        Label hoursLabel = new Label("Estimated Hours to Fix:");
        hoursLabel.setFont(new Font(14));
        bugEstimatedHoursField.setPromptText("Enter estimated hours");
        bugEstimatedHoursField.setPrefWidth(200);

        Button submitButton = new Button("Report Bug");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> reportBugButton.fire());

        form.getChildren().addAll(
            instructionLabel,
            taskLabel, bugTaskComboBox,
            titleLabel, bugTitleField,
            descLabel, bugDescriptionArea,
            severityLabel, severityComboBox,
            hoursLabel, bugEstimatedHoursField,
            submitButton
        );

        return form;
    }

    private VBox createBugsDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("My Reported Bugs:");
        displayLabel.setFont(new Font(16));

        bugsDisplayArea.setEditable(false);
        bugsDisplayArea.setWrapText(true);
        bugsDisplayArea.setPrefHeight(500);

        Button refreshButton = new Button("Refresh Bugs");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewBugsButton.fire());

        container.getChildren().addAll(displayLabel, bugsDisplayArea, refreshButton);
        return container;
    }

    // Getters
    public Label getWelcomeLabel() { return welcomeLabel; }
    public Button getVerifyTaskButton() { return verifyTaskButton; }
    public Button getReportBugButton() { return reportBugButton; }
    public Button getAssignTaskToQAButton() { return assignTaskToQAButton; }
    public Button getViewTasksButton() { return viewTasksButton; }
    public Button getViewBugsButton() { return viewBugsButton; }
    public Button getLogoutButton() { return logoutButton; }
    
    public ComboBox<String> getVerifyTaskComboBox() { return verifyTaskComboBox; }
    public ComboBox<String> getAssignTaskComboBox() { return assignTaskComboBox; }
    public ComboBox<String> getBugTaskComboBox() { return bugTaskComboBox; }
    public TextField getBugTitleField() { return bugTitleField; }
    public TextArea getBugDescriptionArea() { return bugDescriptionArea; }
    public ComboBox<String> getSeverityComboBox() { return severityComboBox; }
    public TextField getBugEstimatedHoursField() { return bugEstimatedHoursField; }
    
    public TextArea getTasksDisplayArea() { return tasksDisplayArea; }
    public TextArea getBugsDisplayArea() { return bugsDisplayArea; }
    public Label getStatusLabel() { return statusLabel; }
    
    // Utility methods
    public void clearVerifyForm() {
        verifyTaskComboBox.getSelectionModel().clearSelection();
    }
    
    public void clearAssignForm() {
        assignTaskComboBox.getSelectionModel().clearSelection();
    }
    
    public void clearBugForm() {
        bugTaskComboBox.getSelectionModel().clearSelection();
        bugTitleField.clear();
        bugDescriptionArea.clear();
        severityComboBox.getSelectionModel().clearSelection();
        bugEstimatedHoursField.clear();
    }
}