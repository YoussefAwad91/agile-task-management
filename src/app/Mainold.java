// package app;

// import database.Database;
// import entities.Enum.*;
// import entities.users.*;
// import entities.workitems.*;

// public class Main {
//     public static void main(String[] args) {
//         // Initialize in-memory "database"
//         Database.initialize();
//         System.out.println("Database initialized.");

//         // Create users (provide simple manual IDs)
//         Developer dev = new Developer("Dev One", "dev1@example.com", "dev1", "pass1", UserRole.DEVELOPER);
//         QAEngineer qa = new QAEngineer("QA One", "qa1@example.com", "qa1", "pass2", UserRole.QA);
//         ScrumMaster sm = new ScrumMaster("SM One", "sm1@example.com", "sm1", "pass3", UserRole.SCRUM_MASTER);
//         Stakeholder sh = new Stakeholder("SH One", "sh1@example.com", "sh1", "pass4", UserRole.STAKEHOLDER);

//         System.out.println("Created users:");
//         for (User u : Database.users) {
//             System.out.println(" - " + u);
//         }
//         // Create a Task
//         Task task1 = new Task("Implement login", "Implement login feature", sh, dev, 8 ,WorkItemType.TASK, Status.TODO);
//         System.out.println("\nCreated Task: " + task1);

//         // Developer starts working
//         dev.changeTaskStatus(task1, Status.IN_PROGRESS);
//         System.out.println("After dev starts: " + task1);

//         // Developer finishes
//         dev.changeTaskStatus(task1, Status.DONE);
//         System.out.println("After dev finishes: " + task1);

//         // QA verifies
//         qa.verifyTask(task1);
//         System.out.println("After QA verify: " + task1);

//         // Report a bug for the task (QA)
//         Bug bug1 = qa.reportBug(task1, "Login fails when username contains spaces", 3, Severity.MEDIUM, dev);
//         System.out.println("\nReported Bug: " + bug1);

//         // Print database summary
//         System.out.println("\nDatabase summary:");
//         System.out.println(" - Users: " + Database.users.size());
//         System.out.println(" - Tasks: " + Database.tasks.size());
//         System.out.println(" - Bugs: " + Database.bugs.size());

//         System.out.println("\nAll tasks:");
//         for (Task t : Database.tasks) {
//             System.out.println(" - " + t);
//         }

//         System.out.println("\nAll bugs:");
//         for (Bug b : Database.bugs) {
//             System.out.println(" - " + b);
//         }

//         System.out.println("\nTest run complete.");
//     }
// }
