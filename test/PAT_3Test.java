
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @
 */
public class PAT_3Test {

    public PAT_3Test() {
    }

    private String[] taskdescripArray;
    private String[] taskname;
    private String[] menuChoices;
    private int[] taskduration;
    private int numTasks;
    private int totalHours;
    private String name;
    private String surname;

    public int returnTotalHours(int taskIndex) {
        return taskduration[taskIndex];
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    public String createTaskID(String taskName, int taskNumber, String surname) {
        String initials = taskName.substring(0, 2).toUpperCase();
        return initials + ":" + taskNumber + ":" + surname.toUpperCase();
    }

    public String taskStatus(int choice) {
        String[] choices = {"TO DO", "DOING", "DONE"};
        if (choice >= 1 && choice <= 3) {
            return choices[choice - 1];
        } else {
            return "INVALID";
        }
    }

    public String option2(String name, String surname, int taskIndex) {
        StringBuilder output = new StringBuilder();
        output.append("Task Status: ").append(menuChoices[taskIndex]).append("\n");
        output.append("Developer Details: ").append(name).append(" ").append(surname).append("\n");
        output.append("Task Number: ").append(taskIndex).append("\n");
        output.append("Task Name: ").append(taskname[taskIndex]).append("\n");
        output.append("Task Description: ").append(taskdescripArray[taskIndex]).append("\n");
        output.append("Task ID: ").append(createTaskID(taskname[taskIndex], taskIndex, surname)).append("\n");
        output.append("Task Duration: ").append(taskduration[taskIndex]).append(" hours\n");
        output.append("\n");
        output.append("Total number of hours (rounded): ").append(totalHours);
        return output.toString();
    }

    @BeforeEach
    public void setUp() {
        taskdescripArray = new String[10];
        taskname = new String[10];
        menuChoices = new String[10];
        taskduration = new int[10];

        numTasks = 0;
        totalHours = 0;
        name = "";
        surname = "";
        taskname[0] = "Login Feature";
        taskdescripArray[0] = "Create Login to authenticate users";
        menuChoices[0] = "TO DO";
        taskduration[0] = 8;

    }

    @Test
    public void testTaskDescriptionLength_Success_Robyn() {
        String validDescription = "Create Login to authenticate users";
        boolean result = checkTaskDescription(validDescription);
        assertTrue(result);
    }

    @Test
    public void testTaskDescriptionLength_Failure_Robyn() {
        String invalidDescription = "This is a very long description that exceeds the maximum allowed length of 50 characters.";
        boolean result = checkTaskDescription(invalidDescription);
        assertFalse(result);
    }

    @Test
    public void testCreateTaskID_Robyn() {
        String taskName = "Login Feature";
        int taskNumber = 0;
        String surname = "Harrison";
        String expectedTaskID = "LO:0:SON";
        String actualTaskID = createTaskID(taskName, taskNumber, surname);
        assertEquals(expectedTaskID, actualTaskID);
    }

    @Test
    public void testTaskStatus_Robyn() {
        int choice = 1;
        String expectedStatus = "TO DO";
        String actualStatus = taskStatus(choice);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void testOption2_Task1Details_Robyn() {
        taskname[0] = "Login Feature";
        taskdescripArray[0] = "Create Login to authenticate users";
        menuChoices[0] = "TO DO";
        taskduration[0] = 8;
        numTasks = 1;
        name = "Robyn";
        surname = "Harrison";
        totalHours = returnTotalHours(0);

        String expectedOutput = "\nTask Status: TO DO\n"
                + "Developer Details: Robyn Harrison\n"
                + "Task Number: 0\n"
                + "Task Name: Login Feature\n"
                + "Task Description: Create Login to authenticate users\n"
                + "Task ID: LO:0:SON\n"
                + "Task Duration: 8 hours\n"
                + "\n"
                + "Total number of hours (rounded): 18\n";

        String actualOutput = option2(name, surname, 0);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testReturnTotalHours() {
        // Set up test data
        taskduration[0] = 10; // given values
        taskduration[1] = 12;
        taskduration[2] = 55;
        taskduration[3] = 11;
        taskduration[4] = 1;

        numTasks = 5;

        // Calculate the expected total hours
        int expectedTotalHours = taskduration[0] + taskduration[1] + taskduration[2] + taskduration[3] + taskduration[4];

        // Call the method under test
        int actualTotalHours = returnTotalHours(numTasks - 1);

        // Assert the result
        assertEquals(expectedTotalHours, actualTotalHours);
    }

}
