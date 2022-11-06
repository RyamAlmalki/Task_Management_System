package com.taskmanagementsystem.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is responsible for manging all the related operation of the project
 */
public class Project {
    private int projectId;
    private String projectName;
    private int teamspaceId;
    protected ArrayList<Task> task = new ArrayList<Task>();
    protected ArrayList<Integer> membersAssignedToProject = new ArrayList<Integer>();
    protected static int numberOfProject = 0;

    /**
     *  For each new project created by the admin couple of information will be set.
     *  Each project will have a name and a Teamspace id
     *  And the id of each project will be set with the help of numberOfProject after that,
     *  we will increment for the next project to use a different id.
     * @param projectName the name of the project.
     * @param teamspaceId the teamspace id.
     */
    public Project(String projectName, int teamspaceId)
    {
        this.projectName = projectName;
        this.teamspaceId = teamspaceId;
        setProjectId(numberOfProject++);
    }


    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getTeamspaceId() {
        return teamspaceId;
    }


    /**
     *
     * This method will only be called when there is a project Since a task can't exist with a project.
     * after the admin set a project, the admin will be asked for the name of the task
     * which will then be added to the task list inside the project
     */
    public void addTask() {
        Scanner input = new Scanner(System.in);
        String taskName;

        System.out.print("Enter Task Name:");
        taskName = input.nextLine();

        task.add(new Task(taskName, getTeamspaceId(), TaskPriority.NORMAL)); // task gets created with the teamspaceId and priority normal until the admin decide to change

        if(task.size() < 2){ // checks if this is the first task created
            Menu.firstTaskAddedMessagePage();
        }else{
            System.out.printf("Task has been created!        %n");
        }
    }


    /**
     * This method will assign the id of the member who is working inside this project.
     * @param memberId the member id given by the admin.
     */
    public void assignMember(int memberId){
        membersAssignedToProject.add(memberId);
    }


    /**
     * This method shows all the task under this project
     */
    public void viewTask(){
        boolean isFound = false;

        Menu.dashboardPage(getProjectName());
        task.sort(Comparator.comparing(Task::getPriority));
        for (Task valueTask : task) {
            valueTask.print();
            isFound = true;
        }

        if(!isFound){
            Menu.emptyMessagePage("task");
        }
    }


    /**
     * This method will loop through all the tasks available in this project and find the task selected by the admin
     * then calls the sets the taskMemberId.
     * @param memberID the member id given by the admin.
     */
    public void assignTask(int memberID){
        int taskID;
        boolean isFound = false;

        viewTask(); // view all the task under this project
        if(task.size() >= 1){ // check if the project has a task
            System.out.println("\n\nPlease, enter task id for member to be assigned:");
            System.out.print("Task ID:");
            taskID = Validation.intValidation(); // validate int input


            for (Task value : task) {
                if(value.getTaskID() == taskID) {
                    value.setTaskMemberID(memberID); // change the taskMemberid to the member who will be assigned
                    System.out.println("Task has been Assigned!");
                    isFound = true;
                }
            }

            if(!isFound) {
                System.out.println("Oops, Invalid Task ID (つ﹏⊂) ");
            }
        }
    }


    /**
     * This function retrieves the task assigned to the member by looping through all the task lists and checking
     * if the memberID matches with the taskMemberId.
     * @param memberID the member id given by the admin.
     */
    public void taskAssignedToMember(int memberID){
        boolean isFound = false;

        if(task.size() >= 1){ // check if task is available
            for (Task value : task) {
                if(value.getTaskMemberID() == memberID) {
                    value.print(getProjectName()); // print task and the project information
                    isFound = true;
                }
            }
        }
        if(!isFound){
            Menu.emptyMessageForMemberPage("task");
        }
    }


    /**
     * This method loops though all the tasks until it finds the one which matches the given id.
     * Then ask the admin if he is sure about the decision or not.
     * If yes, the task gets removed from the list immediately; else the adminPage comes back.
     */
    public void deleteTask(){
        Scanner input = new Scanner(System.in);
        int taskID;
        boolean isFound = false ;
        char choice;

        viewTask(); // view available task
        if(task.size() >= 1){ // check if task is available
            System.out.println("\n\nPlease, Select task and provide its ID for it to be deleted: ");
            System.out.print("Task ID:");
            taskID = Validation.intValidation(); // validate int input only

            for (int i = 0; i < task.size(); i++) {
                if(task.get(i).getTaskID() == taskID) {

                    System.out.printf("Are you sure you want to delete task %d  \\(˚☐˚”)/ ! (N\\Y)? %n", task.get(i).getTaskID());
                    choice = input.next().charAt(0);

                    if(choice == 'Y' || choice == 'y'){
                        task.remove(i); // task removed
                        System.out.println("Task has been deleted! ");
                    }else{
                        System.out.println("You had me scared  (❀⃙⃕⃠⃝⃘⃚౪❀⃙⃕⃠⃝⃘⃚  ) ");
                        break;
                    }
                    isFound = true;
                }
            }

            if(!isFound) { // invalid task id
                System.out.println("Oops, Invalid Task ID (つ﹏⊂) ");
            }
        }
    }



    /**
     * This method prints out the project details.
     */
    public void print()
    {
        System.out.printf("%35s %20S", getProjectName(), getProjectId());
    }

}
