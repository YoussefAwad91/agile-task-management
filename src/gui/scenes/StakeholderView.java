package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class StakeholderView {

    private final Label welcomeLabel = new Label();
    private final Button createEpicButton = new Button("Create Epic");
    private final Button createStoryButton = new Button("Create Story");
    private final Button viewMyItemsButton = new Button("View My Work Items");
    private final Button logoutButton = new Button("Logout");
    
    // Create Epic Fields
    private final TextField epicTitleField = new TextField();
    private final TextArea epicDescriptionArea = new TextArea();
    private final TextField epicEstimatedHoursField = new TextField();
    
    // Create Story Fields
    private final TextField storyTitleField = new TextField();
    private final TextArea storyDescriptionArea = new TextArea();
    private final TextField storyEstimatedHoursField = new TextField();
    private final ComboBox<String> parentEpicComboBox = new ComboBox<>();
    
    // Display area
    private final TextArea displayArea = new TextArea();
    private final Label statusLabel = new Label();

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

        // Center: tab pane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //tabs
        Tab epicTab = new Tab("Create Epic");
        epicTab.setContent(createEpicForm());
        
        Tab storyTab = new Tab("Create Story");
        storyTab.setContent(createStoryForm());
        
        Tab displayTab = new Tab("Work Items");
        displayTab.setContent(createDisplayArea());

        tabPane.getTabs().addAll(epicTab, storyTab, displayTab);
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

    private VBox createEpicForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label titleLabel = new Label("Epic Title:");
        titleLabel.setFont(new Font(14));
        epicTitleField.setPromptText("Enter epic title");
        epicTitleField.setPrefWidth(400);

        Label descLabel = new Label("Description:");
        descLabel.setFont(new Font(14));
        epicDescriptionArea.setPromptText("Enter epic description");
        epicDescriptionArea.setPrefHeight(150);
        epicDescriptionArea.setWrapText(true);

        Label hoursLabel = new Label("Estimated Hours:");
        hoursLabel.setFont(new Font(14));
        epicEstimatedHoursField.setPromptText("Enter estimated hours");
        epicEstimatedHoursField.setPrefWidth(200);

        Button submitEpicButton = new Button("Create Epic");
        submitEpicButton.setFont(new Font(14));
        submitEpicButton.setPrefWidth(150);
        submitEpicButton.setOnAction(e -> createEpicButton.fire());

        form.getChildren().addAll(
            titleLabel, epicTitleField,
            descLabel, epicDescriptionArea,
            hoursLabel, epicEstimatedHoursField,
            submitEpicButton
        );

        return form;
    }

    private VBox createStoryForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.TOP_LEFT);

        Label titleLabel = new Label("Story Title:");
        titleLabel.setFont(new Font(14));
        storyTitleField.setPromptText("Enter story title");
        storyTitleField.setPrefWidth(400);

        Label descLabel = new Label("Description:");
        descLabel.setFont(new Font(14));
        storyDescriptionArea.setPromptText("Enter story description");
        storyDescriptionArea.setPrefHeight(150);
        storyDescriptionArea.setWrapText(true);

        Label hoursLabel = new Label("Estimated Hours:");
        hoursLabel.setFont(new Font(14));
        storyEstimatedHoursField.setPromptText("Enter estimated hours");
        storyEstimatedHoursField.setPrefWidth(200);

        Label epicLabel = new Label("Parent Epic:");
        epicLabel.setFont(new Font(14));
        parentEpicComboBox.setPromptText("Select parent epic");
        parentEpicComboBox.setPrefWidth(400);

        Button submitStoryButton = new Button("Create Story");
        submitStoryButton.setFont(new Font(14));
        submitStoryButton.setPrefWidth(150);
        submitStoryButton.setOnAction(e -> createStoryButton.fire());

        form.getChildren().addAll(
            titleLabel, storyTitleField,
            descLabel, storyDescriptionArea,
            hoursLabel, storyEstimatedHoursField,
            epicLabel, parentEpicComboBox,
            submitStoryButton
        );

        return form;
    }

    private VBox createDisplayArea() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        Label displayLabel = new Label("My Work Items:");
        displayLabel.setFont(new Font(16));

        displayArea.setEditable(false);
        displayArea.setWrapText(true);
        displayArea.setPrefHeight(500);

        Button refreshButton = new Button("Refresh");
        refreshButton.setFont(new Font(14));
        refreshButton.setOnAction(e -> viewMyItemsButton.fire());

        container.getChildren().addAll(displayLabel, displayArea, refreshButton);
        return container;
    }

    // Getters
    public Label getWelcomeLabel() { return welcomeLabel; }
    public Button getCreateEpicButton() { return createEpicButton; }
    public Button getCreateStoryButton() { return createStoryButton; }
    public Button getViewMyItemsButton() { return viewMyItemsButton; }
    public Button getLogoutButton() { return logoutButton; }
    
    public TextField getEpicTitleField() { return epicTitleField; }
    public TextArea getEpicDescriptionArea() { return epicDescriptionArea; }
    public TextField getEpicEstimatedHoursField() { return epicEstimatedHoursField; }
    
    public TextField getStoryTitleField() { return storyTitleField; }
    public TextArea getStoryDescriptionArea() { return storyDescriptionArea; }
    public TextField getStoryEstimatedHoursField() { return storyEstimatedHoursField; }
    public ComboBox<String> getParentEpicComboBox() { return parentEpicComboBox; }
    
    public TextArea getDisplayArea() { return displayArea; }
    public Label getStatusLabel() { return statusLabel; }
    
    // Utility methods
    public void clearEpicForm() {
        epicTitleField.clear();
        epicDescriptionArea.clear();
        epicEstimatedHoursField.clear();
    }
    
    public void clearStoryForm() {
        storyTitleField.clear();
        storyDescriptionArea.clear();
        storyEstimatedHoursField.clear();
        parentEpicComboBox.getSelectionModel().clearSelection();
    }
}