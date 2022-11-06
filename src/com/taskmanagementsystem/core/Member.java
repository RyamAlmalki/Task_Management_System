package com.taskmanagementsystem.core;

import java.util.Scanner;

/**
 * This class handles the operations a member performs.
 * A member-only exists when an admin adds the generated email to his workspace.
 * The main purpose of the member is to handle the work the admin assign to him.
 */
public class Member extends User{
    private UserState state;
    private Admin admin = null;

    /**
     * When a member gets created, the admin who created the member
     * will be added with the admins' TeamspaceId.
     * This approach will help to know who is the admin of this member and
     * from there, we can access all the projects the admin has.
     * @param email email for user
     * @param teamspaceId teamspace for user
     */
    public Member(String email, int teamspaceId, Admin admin) {
        super.setEmail(email);
        super.setTeamspaceId(teamspaceId);
        super.setId(numberOfUser);
        setState(UserState.INACTIVE);
        this.admin = admin;
    }

    public void setState(UserState state){
        this.state = state;
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public UserState getState() {
        return state;
    }

    /**
     * This method will print all the members details.
     */
    public void print()
    {
        System.out.printf("%30s %25s %15s %15s\n", getEmail(), getName(), getId(), getState());
    }


    /**
     * This method is used to show the member profile page.
     * it has multiple operations the member can do.
     * @return Return true when member choice was to deactivate the account. So when he goes back to the main page, he will be exited immediately from the admin page.
     */
    @Override
    public boolean profile() {
        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);
        String name, password;

        do{
            Menu.memberProfilePage(getName(), getEmail(), getPassword(), getTeamspaceId());
            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-5), inclusive)");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 5) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-5), inclusive)");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            System.out.println("Enter your information");
                            System.out.print("Full Name:");
                            name = Validation.inputStringValidation(); // validate that the input is a string full name
                            setName(name); // set name
                        }
                        case 2 -> {
                            System.out.println("Enter your information");
                            System.out.print("Password:");
                            password = Validation.passwordFormatValidation(); // validate that the input is a correct format for a password
                            setPassword(password); // set password
                        }
                        case 3 -> {
                            deactivateAccount(); // calls deactivate account method
                            return true; // return true to exit immediately
                        }
                        case 4 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);

        return false; // return true to indicate that user didn't deactivate account
    }


    /**
     * This Method shows the members the project he is assigned to.
     */
    @Override
    public void viewProject(){
        boolean isFound = false;

        Menu.viewProjectPage();
        for (Project value : admin.project) { // loops through all the members admin project
            for (Integer memberId : value.membersAssignedToProject) { // loops through the project membersAssignedToProject to check if member id is in the list.
                if (getId() == memberId) // if project exist and member is assigned to one of the task then it will print project name.
                {
                   value.print();
                   isFound = true;
                   break;
                }
            }
        }

        if(!isFound){ // when no project was found assigned to this member.
            Menu.emptyMessageForMemberPage("project");
        }
    }


    /**
     * method to verify if the member was even assigned to a project or not
     * @return true that he is assigned to project.
     */
    public boolean projectAssigned(){
        for (Project value : admin.project) {
            for (Integer memberId : value.membersAssignedToProject) {
                if (getId() == memberId) // if project exist and member is assigned to one of the task then it will return true
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TThis method shows all the projects the member is assigned to.
     * When a member chooses a project to view, all the tasks under this project will show.
     * This gives the member a glance at where he and his team are at with completing the task.
     */
    @Override
    public void dashboard() {
        int projectId;

        viewProject();  // call the print project method to make the user chose id from the list given
        if(projectAssigned()){ // checks if the member has assigned project
            System.out.println("\nEnter the id of project you want to view!");
            System.out.println("Project id:");
            projectId = Validation.intValidation(); // validate that the input is an integer only

            for (Project value : admin.project) {  // loops through admin projects
               if(value.getProjectId() == projectId){ // look for the project the member wants to view
                   value.viewTask();
               }
            }
        }
    }


    /**
     * This method deactivates the member's account but does not delete the email of the member.
     * This method gives the member a chance to exit from the teamspace and removes all his previous data.
     * The email can be used by another member the admin wants to add.
     *  If the admin wants to remove the email permanently, then he has to deleteMember.
     */
    @Override
    public void deactivateAccount() {
        Scanner input = new Scanner(System.in);
        boolean isDone = false;
        char requestedChoice;


        do {
            System.out.print("Are you sure you want to delete Your Account  \\(˚☐˚”)/ ! (N\\Y)? ");

            if (input.hasNextInt())
            {
                input.nextLine();
                System.out.println("Please enter a either (N|Y), inclusive)");
            }
            else
            {
                requestedChoice = input.next().charAt(0);
                if (requestedChoice != 'Y' && requestedChoice != 'y' && requestedChoice != 'N' && requestedChoice != 'n') {
                    System.out.println(requestedChoice + " isn't an option");
                } else
                {

                    for(int i = 0; i < admin.project.size(); i++) // loop though all the admin project
                    {
                        for(int j = 0; j < admin.project.get(i).membersAssignedToProject.size(); j++) // loops through the projects
                        {
                            if(getId() == admin.project.get(i).membersAssignedToProject.get(j))  // checks if the project member assigned has this member
                            {
                                for(Task task: admin.project.get(i).task) // loops through the project tasks
                                {
                                    if(getId() == task.getTaskMemberID()) // check if task was assigned to this member
                                    {
                                        if(task.getState() == TaskState.RUNNING){ // if the task state was running then it returns it to todo since the member left it.
                                            task.setState(TaskState.TODO); // returns it to TODO
                                        }
                                        task.setTaskMemberID(0); //removes the member id
                                    }
                                }

                                admin.project.get(i).membersAssignedToProject.remove(j); // remove the member from the membersAssignedToProject
                            }
                        }
                    }

                    setPassword(null); // return the password to null for the next member to create own password
                    setState(UserState.INACTIVE); // change account from active to inactive
                    setName(null); // return the name to null for the next member to create own password

                    System.out.println("We're sad to see you go  (❀⃙⃕⃠⃝⃘⃚౪❀⃙⃕⃠⃝⃘⃚  )");
                    isDone = true;

                }
            }
        }while (!isDone) ;
    }



    /**
     * This method member to edit a given task state.
     * It first looks for the selected project.
     * Then it asks the user to choose a state from the options showing.
     * This method will keep looping until the member exits.
     */
    @Override
    public void editTask() {
        Scanner input = new Scanner(System.in);
        int taskId;
        boolean isExitRequested = false;
        int requestedChoice;

        viewTaskAssigned(); // view member assigned tasks
        if(projectAssigned()){ // if project assigned is true it means we  have any task

            System.out.println("Enter the id of task you want to edit!");
            System.out.println("Task id:");
            taskId = Validation.intValidation(); // validate int input

            for(Project value: admin.project){ // loops through all projects
                for (Task valueTask : value.task) { // loops through project task
                    if(valueTask.getTaskID() == taskId) // checks for the asked task
                    {
                        do {
                            Menu.editTaskStatusPage(); // prints the edit menu
                            if (!input.hasNextInt()) {
                                input.nextLine();
                                System.out.println("Please enter a number (1-4), inclusive)");
                            } else {
                                requestedChoice = input.nextInt();
                                if (requestedChoice < 1 || requestedChoice > 4) {
                                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-4), inclusive)");
                                } else {
                                    switch (requestedChoice) {
                                        case 1 -> {
                                            valueTask.setState(TaskState.TODO);
                                        }
                                        case 2 -> {
                                            valueTask.setState(TaskState.RUNNING);
                                        }
                                        case 3 -> {
                                            valueTask.setState(TaskState.COMPLETED);
                                        }
                                        case 4 -> isExitRequested = true;
                                    }
                                }
                            }
                        } while (!isExitRequested);
                    }
                }
            }
        }
    }


    /**
     * This method prints all the task assigned to this member
     */
    public void viewTaskAssigned(){
        boolean isFound = false;

        Menu.taskAssignedPage();
        for(Project value: admin.project){ // loops through the project
            for(Integer memberId: value.membersAssignedToProject)
            {
                if(getId() == memberId) // if project exist and member is assigned to one of the task then
                {
                    value.taskAssignedToMember(getId());
                    isFound = true;
                }
            }
        }

        if(!isFound){ // when no task was found
            Menu.emptyMessageForMemberPage("task");
        }
    }



}
