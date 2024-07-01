package pat_3;

import javax.swing.JOptionPane;

/**
 * This is where part 2 mainly takes place a new class was created and called
 *
 *
 */
public class Task {

    // Arrays to store task details
    private static String[] taskName;
    private static String[] taskDescription;
    private static float[] taskDuration;
    private static String[] developerName;
    private static String[] taskStatus;
    private static String[] taskID;
    
    // Variables to track the number of tasks and total hours
    private static int numTasks = 0;
    private static int totalHours = 0;

    // Main method to run the task management system
    public static void runTaskManagement(String name, String surname) {
        int choice;
        do {
            choice = showMenu();
            JOptionPane.showMessageDialog(null, "\nLoading System: " + choice);

            if (choice == 1) {
                AddTasks(name, surname);
            } else if (choice == 2) {
                optionShowReport();
            } else {
                JOptionPane.showMessageDialog(null, "Exiting System...");
            }
        } while (choice != 3);
    }

    // Method to display the menu and get user's choice
    public static int showMenu() {
        int choice = 0;
        try {
            String menuMessage = "\tMenu:\n\n"
                    + "============================================================\n"
                    + "\t1. Option 1 - Add Tasks\n"
                    + "\t2. Option 2 - Show Report\n"
                    + "\t3. Option 3 - Exit\n"
                    + "============================================================\n"
                    + "Enter your choice:";
            String choiceString = JOptionPane.showInputDialog(null, menuMessage);
            choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > 3) {
                JOptionPane.showMessageDialog(null, "Invalid option. Enter a number from 1 to 3.");
                choice = 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Enter a number from 1 to 3.");
            choice = 0;
        }
        return choice;
    }

    // Method to add tasks to the system
    public static void AddTasks(String name, String surname) {
        int tasks = Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks do you want to add?"));
        
        // Initialize arrays based on the number of tasks
        taskName = new String[tasks];
        taskDescription = new String[tasks];
        taskDuration = new float[tasks];
        developerName = new String[tasks];
        taskStatus = new String[tasks];
        taskID = new String[tasks];

        for (int i = 0; i < tasks; i++) {
            JOptionPane.showMessageDialog(null, "Option 1 - Add Tasks");

            String Name = JOptionPane.showInputDialog(null, "Enter the task name:");
            taskName[i] = Name;

            // Loop to ensure task description is within 50 characters
            while (true) {
                String Description = JOptionPane.showInputDialog(null, "Enter the task's description (50 or fewer characters):");
                if (checkTaskDescription(Description)) {
                    taskDescription[i] = Description;
                    JOptionPane.showMessageDialog(null, "Task successfully captured.");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of 50 or fewer characters.");
                }
            }

            float Duration = returnTotalHours();
            taskDuration[i] = Duration;
            totalHours += Duration;

            String Status = getTaskStatus();
            taskStatus[i] = Status;

            String ID = createTaskID(Name, i, surname);
            taskID[i] = ID;
            developerName[i] = name + " " + surname;

            numTasks++;
        }
    }

    // Method to check if task description is within 50 characters
    public static boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Method to get task duration from user and validate input
    public static float returnTotalHours() {
        float totalHours = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String hoursString = JOptionPane.showInputDialog(null, "Enter the task duration in hours:");
                totalHours = Float.parseFloat(hoursString);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }
        return totalHours;
    }

    // Method to get task status from user
    public static String getTaskStatus() {
        String[] options = {"To Do", "In Progress", "Done"};
        int statusIndex = JOptionPane.showOptionDialog(null, "Select the task status:", "Task Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String taskStatus = options[statusIndex];
        return taskStatus;
    }

    // Method to create a task ID
    public static String createTaskID(String taskName, int index, String surname) {
        String initials = taskName.substring(0, 2).toUpperCase();
        String id = initials + index + surname.substring(0, 2).toUpperCase();
        return id;
    }

    // Method to show the report options and handle user's choice
    public static void optionShowReport() {
        JOptionPane.showMessageDialog(null, "Option 2 - Report");

        if (numTasks > 0) {
            String reportString = JOptionPane.showInputDialog(null,
                    "Select an option:\n\n"
                    + "1. Display the Developer, Task Name, Task Duration, and Task ID\n"
                    + "2. Display the longest duration\n"
                    + "3. Search for a task by task Name \n"
                    + "4. Search for all task by developer details \n"
                    + "5. Delete a task using the task Name\n"
                    + "6. Display all captured tasks");

            int reportMenu = Integer.parseInt(reportString);

            if (reportMenu == 1) {
                displayStatus("Done");
            } else if (reportMenu == 2) {
                LongestDuration();
            } else if (reportMenu == 3) {
                searchName();
            } else if (reportMenu == 4) {
                searchDeveloper();
            } else if (reportMenu == 5) {
                deleteTaskByName();
            } else if (reportMenu == 6) {
                AllTasks();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tasks added yet.");
        }
    }

    // Method to display tasks with a specific status
    public static void displayStatus(String status) {
        JOptionPane.showMessageDialog(null, "Tasks with status '" + status + "':");
        boolean foundTasks = false;
        for (int i = 0; i < numTasks; i++) {
            if (taskStatus[i].equalsIgnoreCase(status)) {
                foundTasks = true;
                printTaskDetails(i);
            }
        }
        if (!foundTasks) {
            JOptionPane.showMessageDialog(null, "No tasks with status '" + status + "' found.");
        }
    }

    // Method to print task details
    public static void printTaskDetails(int index) {
        JOptionPane.showMessageDialog(null,
                "Task Name: " + taskName[index] + "\n"
                + "Task Description: " + taskDescription[index] + "\n"
                + "Task Duration: " + taskDuration[index] + " hours\n"
                + "Developer: " + developerName[index] + "\n"
                + "Task Status: " + taskStatus[index] + "\n"
                + "Task ID: " + taskID[index]);
    }

    // Method to search tasks by developer name
    public static void searchDeveloper() {
        String developer = JOptionPane.showInputDialog(null, "Enter the developer's name:", "Developer Details", JOptionPane.QUESTION_MESSAGE);
        boolean foundTasks = false;
        for (int i = 0; i < numTasks; i++) {
            if (developerName[i].equalsIgnoreCase(developer)) {
                foundTasks = true;
                printTaskDetails(i);
            }
        }
        if (!foundTasks) {
            JOptionPane.showMessageDialog(null, "No tasks assigned to developer '" + developer + "' found.", "Developer Details", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete a task by name
    public static void deleteTaskByName() {
        String Name = JOptionPane.showInputDialog(null, "Enter the task name:");
        boolean deleted = false;
        for (int i = 0; i < numTasks; i++) {
            if (taskName[i] != null && taskName[i].equalsIgnoreCase(Name)) {
                deleted = true;
                deleteTaskAt(i);
                JOptionPane.showMessageDialog(null, "Task '" + Name + "' deleted.");
                break;
            }
        }
        if (!deleted) {
            JOptionPane.showMessageDialog(null, "Task '" + Name + "' not found.");
        }
    }

    // Method to delete a task at a specific index
    public static void deleteTaskAt(int index) {
        taskName[index] = null;
        taskDescription[index] = null;
        taskDuration[index] = 0;
        developerName[index] = null;
        taskStatus[index] = null;
        taskID[index] = null;
    }

    public static void searchName() {
        String Nameoftask = JOptionPane.showInputDialog(null, "Enter the task name:", "Task name",JOptionPane.QUESTION_MESSAGE);
        boolean foundTask = false;
        for (int i = 0; i < numTasks; i++) {
            if (taskName[i].equalsIgnoreCase(Nameoftask)) {
                foundTask = true;
                printTaskDetails(i);
            }
        }
        if (!foundTask) {
            JOptionPane.showMessageDialog(null, "Task number '" + Nameoftask + "'was not found.", "Task name",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void AllTasks() {
        for (int i = 0; i < numTasks; i++) {
            JOptionPane.showMessageDialog(null, "Task " + (i + 1) + " Details:\n");
            printTaskDetails(i);
        }
    }

    public static void LongestDuration() {
        float longestDuration = -1;
        int longestDurationIndex = -1;
        for (int i = 0; i < numTasks; i++) {
            if (taskDuration[i] > longestDuration) {
                longestDuration = taskDuration[i];
                longestDurationIndex = i;
            }
        }
        if (longestDurationIndex != -1) {
            JOptionPane.showMessageDialog(null, "Task with longest duration:\n"
                    + "Developer: " + developerName[longestDurationIndex] + "\n"
                    + "Duration: " + taskDuration[longestDurationIndex] + " hours\n"
                    + "Task ID: " + taskID[longestDurationIndex], "Duration",JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        }
    }
}
