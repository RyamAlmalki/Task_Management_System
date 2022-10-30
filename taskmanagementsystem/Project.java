package taskmanagementsystem3;

import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    private final int projectID;
    private String projectTitle;
    private final int teamspace;
    static int count = 1000;
    public State state;
    public ArrayList<Task> task = new ArrayList<Task>();
    public ArrayList<Integer> membersAssigned = new ArrayList<Integer>();


    /**
     *
     * @param projectTitle the name of the project.
     * @param teamspace the teamspace id.
     */
    public Project(String projectTitle, int teamspace)
    {
        this.projectTitle = projectTitle;
        this.teamspace = teamspace;
        projectID = count++;
    }

    /**
     *
     * @param projectTitle the name the project.
     */
    public void setProjectTitle(String projectTitle)
    {
        this.projectTitle = projectTitle;
    }

    /**
     *
     * @return return the project title.
     */
    public String getProjectTitle()
    {
        return projectTitle;
    }

    /**
     *
     * @return the teamspce id.
     */
    public int getTeamspace()
    {
        return teamspace;
    }

    /**
     *
     * @return return the project id.
     */
    public int getProjectID()
    {
        return projectID;
    }

    public void print()
    {
        System.out.printf("%30s %20S", getProjectTitle(), getProjectID());
    }

    public void addTask() {
        Scanner input = new Scanner(System.in);
        String taskName;

        System.out.print("Enter Task Name:");
        taskName = input.nextLine();

        task.add(new Task(taskName, getTeamspace(), getProjectID()));
    }

    public void taskUnderProject() {
        for (Task value : task) {
            value.print();
        }
    }

    public void assignTask(int memberID, Member member) {
        Scanner input = new Scanner(System.in);
        int taskID;
        boolean isFound = false;

        System.out.println("\n\nPlease, enter task id for member to be assigned:");
        System.out.print("Task ID:");
        taskID = input.nextInt();

        for (Task value : task) {
            if(value.getTaskID() == taskID) {
                value.setTaskMemberID(memberID);
                isFound = true;
            }
        }

        if(!isFound) {
            System.out.println("Please add a task or chose a valid id!");
        }
    }

    public void assignMember(int member) {
       membersAssigned.add(member);
    }

    public void viewAssignMember(int memberID) {
        for(Integer value: membersAssigned){
           if(value == memberID){
               System.out.printf("%30S %15s\n", getProjectTitle(), getProjectID());
           }
        }
    }


}
