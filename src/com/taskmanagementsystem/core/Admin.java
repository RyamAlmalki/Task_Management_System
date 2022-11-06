package com.taskmanagementsystem.core;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * This class provides complete access to many operations,
 * such as adding, editing, deleting, and assigning.
 * When an admin gets created, a project ArrayList will get created also.
 * This ArrayList is responsible for managing all the projects an admin creates.
 * When the admin no longer exists, the project and all the
 * information related to this admin will get removed.
 */
public class Admin extends User{
    protected static int numberOfTeamspaceId = 1; // this will get incremented everytime a new Admin gets created.
    protected int memberCount = 0; // This is used to track the number of user the admin has.
    protected ArrayList<Project> project = new ArrayList<Project>(); // A list of all the projects created by this admin.

    /**
     * initializing Admin information.
     * The teamspaceId is set by numberOfTeamspaceId then increment for the next admin.
     * The admin Id is set by the numberOfUser in the system then incremented for the next user in the setId method.
     * @param name name for member.
     * @param email email for member.
     * @param password password for member.
     */
    public Admin(String name, String email, String password) {
        super(name, email, password);
        super.setTeamspaceId(numberOfTeamspaceId++);
        super.setId(numberOfUser);
    }


    /**
     * This method is responsible for adding members to the Teamspace.
     * the admin can decide whether he wants to add the member or note by providing the following access email and code.
     * @param admin current admin will be given to the added member to link the two.
     */
    public void addMember(Admin admin) {
        Scanner input = new Scanner(System.in);
        String email;
        char choice;

        Menu.addMemberPage();
        email = "Member"+getId() + numberOfUser +"@tms.com"; // generated email using the admin id + the current system user with the domain of the system included
        System.out.println("Please provide the member with the following to join your teamspace!");
        System.out.printf("Generated Email:   %s%n", email);
        System.out.printf("Teamspace Code:    %s%n", getTeamspaceId());


        System.out.println("Are you sure you want to add this email to your system in order for the member to activate it? \\(˚☐˚”)/ ! (N\\Y)? ");
        choice = input.next().charAt(0);

        if(choice == 'Y' || choice == 'y'){
            Main.member.add(new Member(email, getTeamspaceId(), admin)); // member gets added with the admins teamspace id and admin reference.
            memberCount++; // the number of members this admin has got incremented.
            numberOfUser++; // the number of user this system has got incremented.
            System.out.println("Member has been added ʘ‿ʘ!");

        } else{ // else whatever the user adds will be ignored
            System.out.println("Member has NOT been added ಠ_ಠ!");
        }

    }


    /**
     * This method will loop through all the members found in the system.
     * Then it will check if the member has the same TeamspaceId as the admin.
     * If so, then it will show all the information of the member.
     */
    public void viewMember(){
        boolean isMemberFound = false;

        Menu.viewMemberPage();
        for (Member value : Main.member) { // loops through all the members in the system
            if(value.getTeamspaceId() == Main.currentUserTeamspaceId){ // Access only when the member has the same TeamspaceId as the admin.
                value.print(); // print the member information
                isMemberFound = true;
            }
        }

        if(!isMemberFound){ // when no member is found
            Menu.emptyMessagePage("member");
        }
    }


    /**
     * This Method is responsible for adding a project in the admins ArrayList project.
     */
    public void addProject(){
        Scanner input = new Scanner(System.in);
        String projectTitle;

        Menu.addProjectPage();
        System.out.print("Project Title: ");
        projectTitle = input.nextLine();
        project.add(new Project(projectTitle, getTeamspaceId())); // add the project with teamspaceID of the admin

        if(project.size() < 2){ // if we added our first project this message will show.
            Menu.firstProjectAddedMessagePage();
        }else{
            System.out.printf("Project has been created!        %n");
        }
    }


    /**
     * This method loops through all the projects stored inside the admin and calls the print on each project to provide its information.
     */
    @Override
    public void viewProject(){
        boolean isProjectFound = false;

        Menu.viewProjectPage();
        for (Project value : project) { // loops through all the projects the admin has created
            value.print();
            System.out.println();
            isProjectFound = true;
        }

        if(!isProjectFound){ // if no project was found.
            Menu.emptyMessagePage("project");
        }
    }


    /**
     * This method loops through all the projects stored in the Admins ArrayList project.
     * Then it checks each ID to see if it is the same as the requested project id.
     * If the ID was matched and the project is found, then it calls the project addTask method which adds a task to this project.
     */
    public void addTask() {
        int projectID;
        boolean isProjectID = false;

        viewProject();
        if(project.size() >= 1){ // checks if the admin has created a project
            System.out.println("\n\nPlease, Select a project and provide its ID for a task to be added:");
            System.out.print("Project ID: ");
            projectID = Validation.intValidation(); // validate that the input is an integer only

            for (Project value : project) { // loops through the admin project
                if (value.getProjectId() == projectID) { // checks if the id matched a project
                    Menu.addTaskPage();
                    value.addTask(); // calls a project addTask method
                    isProjectID = true;
                }
            }

            if(!isProjectID){ // if the user enters a wrong id.
                System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");
            }
        }
    }


    /**
     * This method loops through all the members until it finds the one which the admin requested.
     * if it finds the member it will then loop through the ArrayList project
     * Then it checks each ID to see if it is the same as the requested project id.
     * If the ID was matched and the project is found, then it calls the project assignTask method
     */
    public void assignTask(){
        int projectID, memberID;
        boolean isFound = false;

        Menu.assignProjectPage();
        viewMember(); // calls the view member method to check if the admin has a member or not
        if(memberCount >= 1){ // checks if the admin has a member
            System.out.println("\n\nPlease, Select a member to be assigned a task:");
            System.out.print("Provide the Member ID:");
            memberID = Validation.intValidation(); // validate that the input is an integer only

            for (Member member : Main.member) // loops through all the members
            {
                /*
                    check if requested member id matched and checks if the member is not inactive.
                    Because when member is inactive he/she will not be assigned a task
                 */
                if (member.getId() == memberID && member.getState() != UserState.INACTIVE)
                {
                    viewProject(); // call the print project method to make the user chose id from the list given
                    if(project.size() >= 1) // checks if the admin has added project
                    {
                        System.out.println("\n\nPlease, enter project id for member to be added in it:");
                        System.out.print("Project ID:");
                        projectID = Validation.intValidation(); // validate that the input is an integer only

                        for (Project value : project) // loops through all the projects the admin has created
                        {
                            if (value.getProjectId() == projectID) // check if the project id that has been requested is present
                            {
                                value.assignTask(member.getId()); // task the member if and calls the assignTask method in the Project class

                                if(!value.membersAssignedToProject.contains(member.getId())){ // condition to see if the member has not been added to the project membersAssignedToProject Arrylist
                                    value.assignMember(member.getId()); // he will only get added in the membersAssignedToProject if he hasn't been added before
                                }
                                isFound = true;
                            }
                        }
                    }
                    else{ // this helps to prevent the if(!isFound) from executing since the viewProject(); will print an error message if project was not found.
                        isFound = true;
                    }

                }

            }

            if(!isFound) { // this will only execute when the Admin enters a wrong projectID or value
                Menu.accountNotActiveMessagePage();
            }

        }
    }



    /**
     * This method loops through all the projects stored in the Admins ArrayList project.
     * Then it checks each ID to see if it is the same as the requested project id.
     * If the ID was matched and the project is found, then it calls the project deleteTask method.
     */
    public void deleteTask(){
        int projectID;
        boolean isProjectID = false;

        viewProject(); // call the print project method to make the user chose id from the list given
        if(project.size() >= 1){ // checks if the admin has added project
            System.out.println("\n\nPlease, Select a project and provide its ID to access the task you want to delete:");
            System.out.print("Project ID: ");
            projectID = Validation.intValidation(); // validate that the input is an integer only

            for (Project value : project) { // loops through all the projects the admin has created
                if (value.getProjectId() == projectID) { // check if the project id that has been requested is present
                    value.deleteTask(); // if project it will call a method to delete the task from the project
                    isProjectID = true;
                }
            }

            if (!isProjectID) { // if user enters an invalid project id
                System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");
            }
        }
    }


    /**
     *
     * This method loops through the member's ArrayList until it finds the id the admin is looking for.
     * If it finds matched id, it will loop through all the projects ArrayList membmembersAssignedToProjec, and Arraylist task.
     * And it will check if the member id was found within the ArrayList; it will call a deleteMember method for the ArrayList membmembersAssignedToProjec.
     * And for the Arraylist task it will set taskMemberId to 0.
     * After it loops thought project and task and removes the member it will then remove member from Arraylist member.
     */
    public void deleteMember(){
        Scanner input = new Scanner(System.in);
        int projectID, memberID;
        boolean isFound = false;
        char choice;

        Menu.assignProjectPage();
        viewMember();  // calls the view member method to check if the admin has a member or not
        if(memberCount >= 1){ // checks if the admin has a member
            System.out.println("\n\nPlease, Select a member ID to be deleted:");
            System.out.print("Member ID:");
            memberID = Validation.intValidation(); // validate that the input is an integer only


            for (int i = 0; i < Main.member.size(); i++) // loops through all the members
            {
                if (Main.member.get(i).getId() == memberID && Main.member.get(i).getTeamspaceId() == getTeamspaceId()) // checks if id and teamspace match to delete the member
                {
                    System.out.printf("Are you sure you want to delete Member %d  \\(˚☐˚”)/ ! (N\\Y)? %n", Main.member.get(i).getId());
                    choice = input.next().charAt(0);

                    // if the user accept to delete the member
                    if(choice == 'Y' || choice == 'y')
                    {

                        if(project.size() >= 1) // checks if there is a project created by admin
                        {
                            for (Project value : project) // loops through all projects
                            {
                                // remove from task
                                if(value.task.size() >= 1){ // checks if there is a task in this project
                                    for (int j = 0; j < value.task.size(); j++)
                                    {
                                        if(value.task.get(i).getTaskMemberID() == memberID){ // loops through each task and checks taskMemberId if it matches with the member we are looking to delete
                                            value.task.get(i).setTaskMemberID(0); // if taskMemberId match the member we will replace by 0 to indicate the user no longer is assigned to this task
                                        }
                                    }
                                }

                                // remove from project
                                for (int j = 0; j < value.membersAssignedToProject.size(); j++) // loops through the membersAssignedToProject list and checks if member if is not in that list
                                {
                                    if(value.membersAssignedToProject.get(i) == memberID){
                                        value.membersAssignedToProject.remove(i); // if the id was matched then the member will be removed
                                    }
                                }
                            }
                        }

                        Main.deactivatedMembers.add(Main.member.get(i).getEmail());
                        Main.member.remove(i); // after the member has been removed from task and project he will be removed from the member ArrayList
                        System.out.println("Member has been removed!");

                    }

                    else{
                        System.out.println("Member has not been removed!");
                        break;
                    }
                }
            }
        }
    }


    /**
     * This method loops through all the projects created by the admin.
     * then it looks for the project id the Admin is looking for to delete
     * Any task under this project is deleted also.
     */
    public void deleteProject(){
        Scanner input = new Scanner(System.in);
        int projectID;
        boolean isProjectID = false;
        char choice;

        viewProject();  // call the print project method to make the user chose id from the list given
        if(project.size() >= 1){ // checks if the admin has added project
            System.out.println("\n\nPlease, Select a project and provide its ID for it to be deleted:");
            System.out.print("Project ID: ");
            projectID = Validation.intValidation(); // validate that the input is an integer only

            // loops through the projects the admin has created
            for (int i = 0; i < project.size(); i++) {
                if (project.get(i).getProjectId() == projectID) { // looks for the requested project id
                    System.out.printf("Are you sure you want to delete project %d  \\(˚☐˚”)/ ! (N\\Y)? %n", projectID);
                    choice = input.next().charAt(0);

                    if(choice == 'Y' || choice == 'y'){ // if the admin ask for it to be deleted
                        project.remove(i); // project gets deleted
                        System.out.println("Project has been deleted!");
                    }else{ // else whatever the user adds will be ignored
                        System.out.println("Project has not been deleted!");
                        break;
                    }
                    isProjectID = true;
                }

                if(!isProjectID){ // when the user enters a wrong a project ID
                    System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");
                }
            }
        }
    }


    /**
     * This method is used to show the admins profile page.
     * it has multiple operations the admin can do.
     * @return Return true when admin choice was to deactivate the account. So when he goes back to the main page, he will be exited immediately from the admin page.
     */
    @Override
    public boolean profile() {
        boolean isExitRequested = false, isNotRegistered = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);
        String name, email, password;

        do{
            Menu.adminProfilePage(getName(), getEmail(), getPassword(), getTeamspaceId());
            // checks if the input is other than integer
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
                            name = Validation.inputStringValidation(); // validate that user provide full name
                            setName(name); // change the admin name
                        }
                        case 2 -> {
                            do{
                                System.out.println("Enter your information");
                                System.out.print("Email:");
                                email = Validation.emailFormatValidation(); // checks email validation

                                if(!Validation.isEmailRegistered(Main.admin, email)) // checks if someone already has this email
                                {
                                    isNotRegistered = true;
                                }
                                else {
                                    System.out.println("Email is taken, Please try again");
                                    isNotRegistered = false;
                                }
                            }while(!isNotRegistered);
                            setEmail(email); // change the admin email
                        }
                        case 3 -> {
                            System.out.println("Enter your information");
                            System.out.print("Password:");
                            password = Validation.passwordFormatValidation(); // validated the password format
                            setPassword(password); // change the admin password
                        }
                        case 4 -> {
                            deactivateAccount(); // calls the deactivate method
                            return true; // return true to exit immediately
                        }
                        case 5 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);

        return false; // return true to indicate that user didn't deactivate account
    }


    /**
     * this method helps the admin get a glance at the performance of members who wore assigned project and to get a full picture of task under each project.
     */
    @Override
    public void dashboard() {
        int projectID;
        boolean isProjectID = false;

        viewProject();  // call the print project method to make the user chose id from the list given
        if(project.size() >= 1){ // checks if the admin has added project
            System.out.println("\n\nPlease, Select a project and provide its ID to view its Progress:");
            System.out.print("Project ID: ");
            projectID = Validation.intValidation(); // validate that the input is an integer only

            for (Project value : project) { // loops through admin projects
                if (value.getProjectId() == projectID) { // look for the project the admin wants to view
                    value.viewTask(); // prints the all the task under project
                    isProjectID = true;
                }
            }

            if(!isProjectID){
                System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");
            }
        }
    }


    /**
     * This method deactivates the Admin account.
     * When an Admin deactivates his account all the project, task and members will be deleted also.
     */
    @Override
    public void deactivateAccount() {
        Scanner input = new Scanner(System.in);
        boolean isDone = false;
        char requestedChoice;


        do {
            System.out.print("Are you sure you want to delete Your Account  \\(˚☐˚”)/ ! (N\\Y)? ");
            requestedChoice = input.next().charAt(0);

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
                } else {
                    // loops through all the members
                    for (int i = 0; i < Main.member.size(); i++) {
                        // checks if member has the same teamspace code as the admin
                        if (Main.member.get(i).getTeamspaceId() == Main.currentUserTeamspaceId) {
                            // if they do have the same teamspace code they will first be added to the deactivated member list
                            // this list will be useful to give a message to members when there account has been deactivated . the message will show when they log in.
                            Main.deactivatedMembers.add(Main.member.get(i).getEmail());
                            // then they will be removed
                            Main.member.remove(i);
                        }
                    }

                    Main.admin.remove(Main.currentUserAdminIndex); // the admin will get removed
                    System.out.println("We're sad to see you go   (❀⃙⃕⃠⃝⃘⃚౪❀⃙⃕⃠⃝⃘⃚  )");
                    isDone = true;
                }
            }
        }while (!isDone) ;
    }


    /**
     * This method gives the admin the chance to edit the task name, state, priority.
     */
    @Override
    public void editTask() {
        int projectID;
        boolean isProjectID = false;
        int projectId, taskId;


        viewProject(); // call the print project method to make the user chose id from the list given
        if (project.size() >= 1) { // checks if there is any project
            System.out.println("\n\nPlease, Select a project and provide its ID to view its Progress:");
            System.out.print("Project ID: ");
            projectID = Validation.intValidation(); // validate that the input is an integer only

            for (Project value : project) { // loops through all project
                if (value.getProjectId() == projectID) { // it loops until it find the project
                    value.viewTask();  // prints all the tasks under this project
                    System.out.println("\n\nPlease, Select a task and provide its ID to edit:");
                    System.out.print("Task ID: ");
                    taskId = Validation.intValidation(); // the user select a task to edit

                    for (Task valueTask : value.task) // loops through all the task in this project
                    {
                        if (valueTask.getTaskID() == taskId) // checks for the asked task
                        {
                            valueTask.editTask(valueTask); // calls the edit task method
                        }
                    }
                }

                isProjectID = true;
            }

            if (!isProjectID) { // when admin provide a wrong id
                System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");

            }
        }
    }


    /**
     * This method allows the admin the edit the project name
     */
    public void editProject(){
        Scanner input = new Scanner(System.in);
        String name;
        int projectId;
        boolean isFound = false;

        viewProject(); // call the print project method to make the user chose id from the list given
        if(project.size() >= 1){ // checks if there is any project
            System.out.print("Enter Project ID you want to edit: ");
            projectId = Validation.intValidation(); // validate that the input is an integer only

            for(Project value: project) // loops through all project
            {
                if(value.getProjectId() == projectId) // it loops until it find the project
                {
                    System.out.print("Enter New project Name: ");
                    name = input.nextLine();
                    value.setProjectName(name); // changes the project name
                    isFound = true;
                }
            }

            if(!isFound){ // checks if the admin enters a wrong id
                System.out.println("Oops, Invalid Project ID (つ﹏⊂) ");
            }
        }
    }
}





