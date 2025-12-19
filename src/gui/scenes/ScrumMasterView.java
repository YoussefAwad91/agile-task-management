package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ScrumMasterView {

    private final Label welcomeLabel = new Label();
    private final Button createSprintButton = new Button("Create Sprint");
    private final Button createTaskButton = new Button("Create Task");
    private final Button assignWorkItemButton = new Button("Assign Work Item to Sprint");
    private final Button assignUserButton = new Button("Assign User to Sprint");
    private final Button reopenWorkItemButton = new Button("Reopen Work Item");
    private final Button viewSprintsButton = new Button("View All Sprints");
    private final Button viewProgressButton = new Button("View Sprint Progress");
    private final Button logoutButton = new Button("Logout");
    
    // Create Sprint Fields
    private final TextField sprintTitleField = new TextField();
    private final TextField sprintObjectiveField = new TextField();
    private final DatePicker startDatePicker = new DatePicker();
    private final DatePicker endDatePicker = new DatePicker();
    
    // Create Task Fields
    private final TextField taskTitleField = new TextField();
    private final TextArea taskDescriptionArea = new TextArea();
    private final TextField taskEstimatedHoursField = new TextField();
    private final ComboBox<String> parentStoryComboBox = new ComboBox<>();
    private final ComboBox<String> assignDeveloperComboBox = new ComboBox<>();
    
    // Assign Work Item Fields
    private final ComboBox<String> workItemComboBox = new ComboBox<>();
    private final ComboBox<String> workItemSprintComboBox = new ComboBox<>();
    
    // Assign User Fields
    private final ComboBox<String> userComboBox = new ComboBox<>();
    private final ComboBox<String> userSprintComboBox = new ComboBox<>();
    
    // Reopen Work Item Fields
    private final ComboBox<String> reopenWorkItemComboBox = new ComboBox<>();
    
    // Display areas
    private final TextArea sprintsDisplayArea = new TextArea();
    private final TextArea progressDisplayArea = new TextArea();
    private final Label statusLabel = new Label();

    public Parent getRoot() {
        BorderPane root = new BorderPane();
        root.setPrefSize(950, 750);

        // Top: Welcome and Logout
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

        // Center: Content Area (TabPane)
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create Sprint Tab
        Tab createSprintTab = new Tab("Create Sprint");
        createSprintTab.setContent(createSprintForm());
        
        // Create Task Tab
        Tab createTaskTab = new Tab("Create Task");
        createTaskTab.setContent(createTaskForm());
        
        // Assign Work Item Tab
        Tab assignWorkTab = new Tab("Assign Work Item");
        assignWorkTab.setContent(createAssignWorkItemForm());
        
        // Assign User Tab
        Tab assignUserTab = new Tab("Assign User");
        assignUserTab.setContent(createAssignUserForm());
        
        // Reopen Work Item Tab
        Tab reopenTab = new Tab("Reopen Work Item");
        reopenTab.setContent(createReopenForm());
        
        // View Sprints Tab
        Tab sprintsTab = new Tab("All Sprints");
        sprintsTab.setContent(createSprintsDisplay());
        
        // Progress Tab
        Tab progressTab = new Tab("Sprint Progress");
        progressTab.setContent(createProgressDisplay());

        tabPane.getTabs().addAll(
            createSprintTab,
            createTaskTab,
            assignWorkTab, 
            assignUserTab, 
            reopenTab, 
            sprintsTab, 
            progressTab
        );
        root.setCenter(tabPane);

        // Bottom: Status
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(10, 20, 10, 20));
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        statusLabel.setFont(new Font(12));
        statusLabel.setTextFill(javafx.scene.paint.Color.web("#2e7d32"));
        bottomBox.getChildren().add(statusLabel);
        root.setBottom(bottomBox);

        return root;
    }

    private VBox createSprintForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Create New Sprint:");
        instructionLabel.setFont(new Font(16));

        Label titleLabel = new Label("Sprint Title:");
        titleLabel.setFont(new Font(14));
        sprintTitleField.setPromptText("Enter sprint title");
        sprintTitleField.setPrefWidth(500);

        Label objectiveLabel = new Label("Sprint Objective:");
        objectiveLabel.setFont(new Font(14));
        sprintObjectiveField.setPromptText("Enter sprint objective/goal");
        sprintObjectiveField.setPrefWidth(500);

        Label startLabel = new Label("Start Date:");
        startLabel.setFont(new Font(14));
        startDatePicker.setPromptText("Select start date");
        startDatePicker.setPrefWidth(300);

        Label endLabel = new Label("End Date:");
        endLabel.setFont(new Font(14));
        endDatePicker.setPromptText("Select end date");
        endDatePicker.setPrefWidth(300);

        Button submitButton = new Button("Create Sprint");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> createSprintButton.fire());

        Label infoLabel = new Label("Note: Sprint duration is typically 2-4 weeks.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));

        form.getChildren().addAll(
            instructionLabel,
            titleLabel, sprintTitleField,
            objectiveLabel, sprintObjectiveField,
            startLabel, startDatePicker,
            endLabel, endDatePicker,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createTaskForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Create New Task:");
        instructionLabel.setFont(new Font(16));

        Label titleLabel = new Label("Task Title:");
        titleLabel.setFont(new Font(14));
        taskTitleField.setPromptText("Enter task title");
        taskTitleField.setPrefWidth(500);

        Label descLabel = new Label("Description:");
        descLabel.setFont(new Font(14));
        taskDescriptionArea.setPromptText("Enter task description");
        taskDescriptionArea.setPrefHeight(150);
        taskDescriptionArea.setWrapText(true);

        Label hoursLabel = new Label("Estimated Hours:");
        hoursLabel.setFont(new Font(14));
        taskEstimatedHoursField.setPromptText("Enter estimated hours");
        taskEstimatedHoursField.setPrefWidth(200);

        Label storyLabel = new Label("Parent Story:");
        storyLabel.setFont(new Font(14));
        parentStoryComboBox.setPromptText("Select parent story");
        parentStoryComboBox.setPrefWidth(500);

        Label developerLabel = new Label("Assign to Developer:");
        developerLabel.setFont(new Font(14));
        assignDeveloperComboBox.setPromptText("Select developer (optional)");
        assignDeveloperComboBox.setPrefWidth(500);

        Button submitButton = new Button("Create Task");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> createTaskButton.fire());

        Label infoLabel = new Label("Note: Tasks must be linked to a parent Story.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));

        form.getChildren().addAll(
            instructionLabel,
            titleLabel, taskTitleField,
            descLabel, taskDescriptionArea,
            hoursLabel, taskEstimatedHoursField,
            storyLabel, parentStoryComboBox,
            developerLabel, assignDeveloperComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createAssignWorkItemForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Assign Work Item to Sprint:");
        instructionLabel.setFont(new Font(16));

        Label workItemLabel = new Label("Select Work Item:");
        workItemLabel.setFont(new Font(14));
        workItemComboBox.setPromptText("Choose work item (Epic/Story/Task/Bug)");
        workItemComboBox.setPrefWidth(500);

        Label sprintLabel = new Label("Select Sprint:");
        sprintLabel.setFont(new Font(14));
        workItemSprintComboBox.setPromptText("Choose target sprint");
        workItemSprintComboBox.setPrefWidth(500);

        Button submitButton = new Button("Assign to Sprint");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> assignWorkItemButton.fire());

        form.getChildren().addAll(
            instructionLabel,
            workItemLabel, workItemComboBox,
            sprintLabel, workItemSprintComboBox,
            submitButton
        );

        return form;
    }

    private VBox createAssignUserForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Assign User to Sprint:");
        instructionLabel.setFont(new Font(16));

        Label userLabel = new Label("Select User:");
        userLabel.setFont(new Font(14));
        userComboBox.setPromptText("Choose team member");
        userComboBox.setPrefWidth(500);

        Label sprintLabel = new Label("Select Sprint:");
        sprintLabel.setFont(new Font(14));
        userSprintComboBox.setPromptText("Choose sprint");
        userSprintComboBox.setPrefWidth(500);

        Button submitButton = new Button("Assign to Sprint");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> assignUserButton.fire());

        Label infoLabel = new Label("Note: Check developer capacity before assigning tasks.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        infoLabel.setWrapText(true);

        form.getChildren().addAll(
            instructionLabel,
            userLabel, userComboBox,
            sprintLabel, userSprintComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createReopenForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Reopen Work Item:");
        instructionLabel.setFont(new Font(16));

        Label workItemLabel = new Label("Select Work Item:");
        workItemLabel.setFont(new Font(14));
        reopenWorkItemComboBox.setPromptText("Choose work item to reopen");
        reopenWorkItemComboBox.setPrefWidth(500);

        Button submitButton = new Button("Reopen Work Item");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> reopenWorkItemButton.fire());

        Label infoLabel = new Label("Note: This will change the status to REOPENED for items that need rework.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        infoLabel.setWrapText(true);

        form.getChildren().addAll(
            instructionLabel,
            workItemLabel, reopenWorkItemComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createSprintsDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("All Sprints:");
        displayLabel.setFont(new Font(16));

        sprintsDisplayArea.setEditable(false);
        sprintsDisplayArea.setWrapText(true);
        sprintsDisplayArea.setPrefHeight(500);

        Button refreshButton = new Button("Refresh Sprints");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewSprintsButton.fire());

        container.getChildren().addAll(displayLabel, sprintsDisplayArea, refreshButton);
        return container;
    }

    private VBox createProgressDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("Sprint Progress Report:");
        displayLabel.setFont(new Font(16));

        progressDisplayArea.setEditable(false);
        progressDisplayArea.setWrapText(true);
        progressDisplayArea.setPrefHeight(500);

        Button refreshButton = new Button("Calculate Progress");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewProgressButton.fire());

        Label infoLabel = new Label("Shows completion percentage for each sprint");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));

        container.getChildren().addAll(displayLabel, progressDisplayArea, refreshButton, infoLabel);
        return container;
    }

    // Getters
    public Label getWelcomeLabel() { return welcomeLabel; }
    public Button getCreateSprintButton() { return createSprintButton; }
    public Button getCreateTaskButton() { return createTaskButton; }
    public Button getAssignWorkItemButton() { return assignWorkItemButton; }
    public Button getAssignUserButton() { return assignUserButton; }
    public Button getReopenWorkItemButton() { return reopenWorkItemButton; }
    public Button getViewSprintsButton() { return viewSprintsButton; }
    public Button getViewProgressButton() { return viewProgressButton; }
    public Button getLogoutButton() { return logoutButton; }
    
    public TextField getSprintObjectiveField() { return sprintObjectiveField; }
    public TextField getSprintTitleField() { return sprintTitleField; }
    public DatePicker getStartDatePicker() { return startDatePicker; }
    public DatePicker getEndDatePicker() { return endDatePicker; }
    
    public TextField getTaskTitleField() { return taskTitleField; }
    public TextArea getTaskDescriptionArea() { return taskDescriptionArea; }
    public TextField getTaskEstimatedHoursField() { return taskEstimatedHoursField; }
    public ComboBox<String> getParentStoryComboBox() { return parentStoryComboBox; }
    public ComboBox<String> getAssignDeveloperComboBox() { return assignDeveloperComboBox; }
    
    public ComboBox<String> getWorkItemComboBox() { return workItemComboBox; }
    public ComboBox<String> getWorkItemSprintComboBox() { return workItemSprintComboBox; }
    public ComboBox<String> getUserComboBox() { return userComboBox; }
    public ComboBox<String> getUserSprintComboBox() { return userSprintComboBox; }
    public ComboBox<String> getReopenWorkItemComboBox() { return reopenWorkItemComboBox; }
    
    public TextArea getSprintsDisplayArea() { return sprintsDisplayArea; }
    public TextArea getProgressDisplayArea() { return progressDisplayArea; }
    public Label getStatusLabel() { return statusLabel; }
    
    // Utility methods
    public void clearSprintForm() {
        sprintTitleField.clear();
        sprintObjectiveField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }
    
    public void clearTaskForm() {
        taskTitleField.clear();
        taskDescriptionArea.clear();
        taskEstimatedHoursField.clear();
        parentStoryComboBox.getSelectionModel().clearSelection();
        assignDeveloperComboBox.getSelectionModel().clearSelection();
    }
    
    public void clearAssignWorkItemForm() {
        workItemComboBox.getSelectionModel().clearSelection();
        workItemSprintComboBox.getSelectionModel().clearSelection();
    }
    
    public void clearAssignUserForm() {
        userComboBox.getSelectionModel().clearSelection();
        userSprintComboBox.getSelectionModel().clearSelection();
    }
    
    public void clearReopenForm() {
        reopenWorkItemComboBox.getSelectionModel().clearSelection();
    }
}