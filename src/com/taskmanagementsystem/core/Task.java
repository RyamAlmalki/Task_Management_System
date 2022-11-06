package com.taskmanagementsystem.core;

import java.util.Scanner;

/**
 * This class is responsible for manging all the related operation of the task
 */
public class Task {
    private int taskId;
    private String taskName;
    private int taskMemberId;
    private int teamspaceId;
    private TaskState state;
    private TaskPriority priority;
    static int numberOfTask = 0;

    /**
     * For each new task created by the admin couple of information will be set.
     * The name and Teamspace will be given by the admin.
     * Where as the if will be set by the counter, which counts each new task added.
     * This approach will prevent any project from having the same id as the other.
     * Next, a task state is given and set to default TODO.
     * It will be changed by the admin and members when working on the task.
     * @param taskName The name of the project set by admin
     * @param teamspaceId the id of the admin workspace
     */
    Task(String taskName, int teamspaceId, TaskPriority priority) {
        this.taskName = taskName;
        this.teamspaceId = teamspaceId;
        setTaskID( numberOfTask++);
        setState(TaskState.TODO);
        this.priority = priority;
    }


    public TaskPriority getPriority() {
        return priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskID() {
        return taskId;
    }

    public TaskState getState() {
        return state;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskID(int taskId){
        this.taskId = taskId;
    }

    /**
     * This method allows us to manipulate the state of the task.
     * @param state is an enum class
     */
    public void setState(TaskState state) {
        this.state = state;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    /**
     * This method sets the id for the member responsible for completing this task.
     * @param taskMemberId the member id given by the admin.
     */
    public void setTaskMemberID(int taskMemberId)
    {
        this.taskMemberId = taskMemberId;
    }

    /**
     * This method returns the id of the member responsible for doing this task.
     * @return member id that has been assigned by admin.
     */
    public int getTaskMemberID()
    {
        return taskMemberId;
    }

    /**
     * This method is responsible for printing information in the dashboard for both admin and member.
     */
    public void print()
    {
        System.out.printf("%30S %15s %15s %15s %15s\n", getTaskName(), getTaskID(), getState(), getTaskMemberID(), getPriority());
    }

    /**
     * This method prints information about the task and project to the Admin.
     * @param projectName project name given by the admin.
     */
    public void print(String projectName)
    {
        System.out.printf("%30S %30s %15s %15s %15s\n", projectName, getTaskName(), getTaskID(), getState(), getPriority());
    }

    /**
     * This method is used to edit the task state priority and name.
     * @param task task the task it will edit
     */
    public void editTask(Task task){
        Scanner input = new Scanner(System.in);
        boolean isExitRequested = false;
        int requestedChoice;

        do {
            Menu.editTaskPage();
            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please enter a number (1-4), inclusive)");
            }
            else
            {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 4) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-4), inclusive)");
                }
                else
                {
                    switch (requestedChoice)
                    {
                        case 1 -> {
                            task.editTaskName();
                        }
                        case 2 -> {
                            task.editTaskState();
                        }
                        case 3 -> {
                            task.editTaskPriority();
                        }
                        case 4 -> isExitRequested = true;
                    }
                }
            }
        } while (!isExitRequested);
    }


    /**
     * This method changes the task name depending on the member input.
     */
    public void editTaskName(){
        String name;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Task Name: ");
        name = input.nextLine();
        setTaskName(name);
    }

    /**
     * This method is used to edit task priority
     */
    public void editTaskPriority(){
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        Menu.taskPriorityPage();
        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Please enter a number (1-3), inclusive)");
        } else {
            requestedChoice = input.nextInt();
            if (requestedChoice < 1 || requestedChoice > 3) {
                System.out.println(requestedChoice + " isn't an option, please enter a number (1-3), inclusive)");
            } else {
                switch (requestedChoice) {
                    case 1 -> {
                        setPriority(TaskPriority.HIGH);
                    }
                    case 2 -> {
                        setPriority(TaskPriority.NORMAL);
                    }
                    case 3 -> {
                        setPriority(TaskPriority.LOW);
                    }
                }
            }
        }
    }

    /**
     * This method is used to edit task State
     */
    public void editTaskState(){
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        Menu.editTaskStatusPage();
        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Please enter a number (1-3), inclusive)");
        } else {
            requestedChoice = input.nextInt();
            if (requestedChoice < 1 || requestedChoice > 3) {
                System.out.println(requestedChoice + " isn't an option, please enter a number (1-3), inclusive)");
            } else {
                switch (requestedChoice) {
                    case 1 -> {
                        setState(TaskState.TODO);
                    }
                    case 2 -> {
                        setState(TaskState.RUNNING);
                    }
                    case 3 -> {
                        setState(TaskState.COMPLETED);
                    }
                }
            }
        }
    }

}

