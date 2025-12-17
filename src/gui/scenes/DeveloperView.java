package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class DeveloperView {

    private final Label welcomeLabel = new Label();
    private final Label capacityLabel = new Label();
    private final Button viewMyTasksButton = new Button("View My Tasks");
    private final Button changeTaskStatusButton = new Button("Change Task Status");
    private final Button viewSprintsButton = new Button("View My Sprints");
    private final Button logoutButton = new Button("Logout");
    
    // Task Status Change Fields
    private final ComboBox<String> taskComboBox = new ComboBox<>();
    private final ComboBox<String> statusComboBox = new ComboBox<>();
    
    // Display areas
    private final TextArea tasksDisplayArea = new TextArea();
    private final TextArea sprintsDisplayArea = new TextArea();
    private final Label statusLabel = new Label();

    public Parent getRoot() {
        BorderPane root = new BorderPane();
        root.setPrefSize(900, 700);

        // Top: Welcome, Capacity, and Logout
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(15, 20, 15, 20));
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(20);
        
        welcomeLabel.setFont(new Font(20));
        capacityLabel.setFont(new Font(16));
        capacityLabel.setTextFill(javafx.scene.paint.Color.web("#1976d2"));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        logoutButton.setFont(new Font(14));
        
        topBox.getChildren().addAll(welcomeLabel, capacityLabel, spacer, logoutButton);
        root.setTop(topBox);

        // Center: Content Area (TabPane)
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Tasks Tab
        Tab tasksTab = new Tab("My Tasks");
        tasksTab.setContent(createTasksDisplay());
        
        // Change Status Tab
        Tab statusTab = new Tab("Change Task Status");
        statusTab.setContent(createStatusChangeForm());
        
        // Sprints Tab
        Tab sprintsTab = new Tab("My Sprints");
        sprintsTab.setContent(createSprintsDisplay());

        tabPane.getTabs().addAll(tasksTab, statusTab, sprintsTab);
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

    private VBox createTasksDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("My Assigned Tasks:");
        displayLabel.setFont(new Font(16));

        tasksDisplayArea.setEditable(false);
        tasksDisplayArea.setWrapText(true);
        tasksDisplayArea.setPrefHeight(500);

        Button refreshButton = new Button("Refresh Tasks");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewMyTasksButton.fire());

        container.getChildren().addAll(displayLabel, tasksDisplayArea, refreshButton);
        return container;
    }

    private VBox createStatusChangeForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label instructionLabel = new Label("Change Task Status:");
        instructionLabel.setFont(new Font(16));

        Label taskLabel = new Label("Select Task:");
        taskLabel.setFont(new Font(14));
        taskComboBox.setPromptText("Choose a task");
        taskComboBox.setPrefWidth(500);

        Label statusLabelForm = new Label("New Status:");
        statusLabelForm.setFont(new Font(14));
        statusComboBox.setPromptText("Choose status");
        statusComboBox.setPrefWidth(300);
        
        // Populate status options
        statusComboBox.getItems().addAll("TODO", "IN_PROGRESS", "DONE");

        Button submitButton = new Button("Update Status");
        submitButton.setFont(new Font(14));
        submitButton.setPrefWidth(150);
        submitButton.setOnAction(e -> changeTaskStatusButton.fire());

        Label infoLabel = new Label("Note: You can only change status of tasks assigned to you.");
        infoLabel.setFont(new Font(12));
        infoLabel.setTextFill(javafx.scene.paint.Color.web("#666666"));
        infoLabel.setWrapText(true);

        form.getChildren().addAll(
            instructionLabel,
            taskLabel, taskComboBox,
            statusLabelForm, statusComboBox,
            submitButton,
            infoLabel
        );

        return form;
    }

    private VBox createSprintsDisplay() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("My Sprint Assignments:");
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

    // Getters
    public Label getWelcomeLabel() { return welcomeLabel; }
    public Label getCapacityLabel() { return capacityLabel; }
    public Button getViewMyTasksButton() { return viewMyTasksButton; }
    public Button getChangeTaskStatusButton() { return changeTaskStatusButton; }
    public Button getViewSprintsButton() { return viewSprintsButton; }
    public Button getLogoutButton() { return logoutButton; }
    
    public ComboBox<String> getTaskComboBox() { return taskComboBox; }
    public ComboBox<String> getStatusComboBox() { return statusComboBox; }
    
    public TextArea getTasksDisplayArea() { return tasksDisplayArea; }
    public TextArea getSprintsDisplayArea() { return sprintsDisplayArea; }
    public Label getStatusLabel() { return statusLabel; }
    
    // Utility methods
    public void clearStatusForm() {
        taskComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
    }
}