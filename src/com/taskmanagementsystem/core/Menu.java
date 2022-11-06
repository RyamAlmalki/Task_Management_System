package com.taskmanagementsystem.core;

/**
 *
 * This class is responsible for displaying the page the user activate.
 * This class will help the user to understand how to interact with the system.
 */
public class Menu {

    public static void mainPage() {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│             WELCOME TO THE TASK MANAGEMENT SYSTEM               │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        System.out.println("(1) LOGIN");
        System.out.println("(2) REGISTER AS TEAM ADMINISTRATOR");
        System.out.println("(3) REGISTER AS TEAM MEMBER");
        System.out.println("(4) EXIT");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    public static void adminPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                            ADMIN PAGE                           │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        System.out.println("1  - Add Member  ");
        System.out.println("2  - Remove Member");
        System.out.println("3  - View Members");
        System.out.println("4  - Add Project     ");
        System.out.println("5  - Remove Project    ");
        System.out.println("6  - View Project    ");
        System.out.println("7  - Edit Project Name ");
        System.out.println("8  - Add Task     ");
        System.out.println("9  - Remove Task    ");
        System.out.println("10 - Edit Task    ");
        System.out.println("11 - Assign Task    ");
        System.out.println("12 - Dashboard     ");
        System.out.println("13 - Profile    ");
        System.out.println("14 - Exit       ");
        System.out.print("Enter your choice: ");
    }

    public static void memberPage() {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                           MEMBER PAGE                           │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        System.out.println("1 - View Task");
        System.out.println("2 - Edit Task Status");
        System.out.println("3 - Dashboard");
        System.out.println("4 - Profile  ");
        System.out.println("5 - Exit");
        System.out.print("\nEnter your choice: ");
    }

    public static void loginPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                            LOGIN PAGE                           │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    public static void registerPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                           REGISTER PAGE                         │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }
    public static void previousMemberMessagePage(){
        System.out.println();
        System.out.printf("                 oh snap! it looks like Admin has            %n");
        System.out.printf("                Deactivated your account (ㅠ﹏ㅠ)!°      %n");
    }

    public static void welcomePage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│          WELCOME YOU HAVE BEEN ADDED TO THE TEAMSPACE           │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    public static void addMemberPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                  ADD MEMBER TO YOUR TEAMSPACE                   │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    public static void viewMemberPage(){
        System.out.println();
        System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                     MEMBER INFORMATION                                │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.printf("%30s %25s %15s %15s", "EMAIL", "NAME", "ID", "ACCOUNT");
        System.out.printf("\n─────────────────────────────────────────────────────────────────────────────────────────%n");
    }

    public static void emptyMessagePage(String missing){
        System.out.println();
        System.out.println();
        System.out.printf("                Oops! %s information is empty!         %n", missing);
        System.out.println("                Looks like you haven't added          ");
        System.out.printf("               any %s to your Teamspace (ಥ ⌣ ಥ)       %n", missing);

    }

    public static void firstTaskAddedMessagePage(){
        System.out.println();
        System.out.printf("               Yay! You've created your first Task         %n");
        System.out.printf("              Now you can get started with Assigning   °%n");
        System.out.printf("        work to your members and getting things completed (๑>ᴗ<๑)! °%n");
    }

    public static void emptyMessageForMemberPage(String missing){
        System.out.println();
        System.out.printf("                        Oops! %s assigned is empty!        %n", missing);
        System.out.printf("                     Looks like Admin haven't assigned%n");
        System.out.printf("                         any %s to you (ಥ ⌣ ಥ)%n", missing);
    }

    public static void editTaskStatusPage(){
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                            EDIT TASK STATUS                          %n");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("1 - TODO");
        System.out.println("2 - RUNNING");
        System.out.println("3 - COMPLETED");
        System.out.println("4 - Exit");
        System.out.print("\nEnter your choice: ");
    }

    public static void addProjectPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                           ADD PROJECT                           │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    public static void firstProjectAddedMessagePage(){
        System.out.println();
        System.out.printf("              Yay! You've created your first project         %n");
        System.out.printf("             Now you can get started with adding tasks  °%n");
        System.out.printf("           and assigning work to your members (๑>ᴗ<๑)! °%n");
    }

    public static void viewProjectPage(){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                           MY PROJECT                            │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        System.out.printf("%35s %20s", "NAME", "ID");
        System.out.println("\n─────────────────────────────────────────────────────────────────");

    }

    public static void addTaskPage(){
        System.out.println();
        System.out.println("────────────────────────────────────────────────────────────────────────────────── ");
        System.out.printf("                                ADD TASK                            %n");
        System.out.printf("  \"Break the task into smaller parts to avoid procrastination (˵ •̀ ᴗ - ˵ ) ✧\" %n");;
        System.out.println("────────────────────────────────────────────────────────────────────────────────── ");
    }

    public static void dashboardPage(String projectTitle){
        System.out.println();
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────── ");
        System.out.printf("                                                %S                                               ", projectTitle);
        System.out.println("\n───────────────────────────────────────────────────────────────────────────────────────────────── ");
        System.out.printf("%30S %15S %15s %15s %15s", "TASK NAME", "TASK ID", "STATUS", "ASSIGNEE ID", "PRIORITY");
        System.out.println("\n───────────────────────────────────────────────────────────────────────────────────────────────── ");
    }

    public static void accountNotActiveMessagePage(){
        System.out.println();
        System.out.println();
        System.out.printf("                Oops! Something is not right!        %n");
        System.out.printf("          it looks like you either entered an invalid ID %n");
        System.out.println("          or Members account is not activated");
        System.out.printf("   Make sure to inform the member to register or use valid id（＾＿－）%n");
    }

    public static void assignProjectPage(){
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────── ");
        System.out.printf("                           ASSIGN A PROJECT                            %n");
        System.out.printf("            \"Together ... Everyone...Achieves...More\" %n");;
        System.out.println("─────────────────────────────────────────────────────────────────── ");
    }

    public static void adminProfilePage(String name, String email, String password, int teamspaceID){
        System.out.println();
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("                               PROFILE                            ");
        System.out.println("                      ┌─────────────────────┐                    ");
        System.out.println("                      │     ░░░░░░░░░░░░    │                    ");
        System.out.println("                      │     /░        ░\\    │                    ");
        System.out.println("                      │    |   0    0   |   │                    ");
        System.out.println("                      │  < |      v     | > │                    ");
        System.out.println("                      │     \\   ────|  /    │                    ");
        System.out.println("                      │      \\________/     │                    ");
        System.out.println("                      └─────────────────────┘                    ");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                 FULL NAME:       %S                               %n", name);
        System.out.printf("                 EMAIL:           %S                               %n", email);
        System.out.printf("                 PASSWORD:        %S                               %n", password);
        System.out.printf("                 TEAMSPACE CODE:  %S                               %n", teamspaceID);
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                            EDIT PROFILE                            %n");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("1 - Edit Full Name");
        System.out.println("2 - Edit Email");
        System.out.println("3 - Edit Password");
        System.out.println("4 - Deactivate Account");
        System.out.println("5 - Exit");
        System.out.print("\nEnter your choice: ");

    }

    public static void memberProfilePage(String name, String email, String password, int teamspaceID){
        System.out.println();
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("                               PROFILE                            ");
        System.out.println("                      ┌─────────────────────┐                    ");
        System.out.println("                      │     ░░░░░░░░░░░░    │                    ");
        System.out.println("                      │     /░        ░\\    │                    ");
        System.out.println("                      │    |   0    0   |   │                    ");
        System.out.println("                      │  < |      v     | > │                    ");
        System.out.println("                      │     \\   ────|  /    │                    ");
        System.out.println("                      │      \\________/     │                    ");
        System.out.println("                      └─────────────────────┘                    ");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                 FULL NAME:       %S                               %n", name);
        System.out.printf("                 EMAIL:           %S                               %n", email);
        System.out.printf("                 PASSWORD:        %S                               %n", password);
        System.out.printf("                 TEAMSPACE CODE:  %S                               %n", teamspaceID);
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                            EDIT PROFILE                            %n");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("1 - Edit Full Name");
        System.out.println("2 - Edit Password");
        System.out.println("3 - Deactivate Account");
        System.out.println("4 - Exit");
        System.out.print("\nEnter your choice: ");

    }

    public static void taskAssignedPage(){
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
        System.out.printf("                                                   TASK I AM ASSIGNED TO                  %n");
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
        System.out.printf("%30S %30S %15s %15s %15s", "PROJECT NAME", "TASK NAME", "ID", "STATUS", "PRIORITY");
        System.out.println("\n─────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");

    }

    public static void teamspaceCodePage(){
        System.out.println("This is the official Teamspace Code and Email for the team you are representing.");
        System.out.println("It's very important that this is entered accurately as this is used");
        System.out.println("to match you up to that team. The Team Admin should be able to provide this information for you.");
        System.out.println("────────────────────────────────────────────────────────────────────────────────── ");
    }

    public static void editTaskPage(){
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                            EDIT TASK                            %n");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("1 - Edit Task Name");
        System.out.println("2 - Edit Task Status");
        System.out.println("3 - Edit Task Priority");
        System.out.println("4 - Exit");
        System.out.print("\nEnter your choice: ");

    }

    public static void taskPriorityPage(){
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("                            EDIT TASK PRIORITY                         %n");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("1 - HIGH");
        System.out.println("2 - NORMAL");
        System.out.println("3 - LOW");
        System.out.print("\nEnter your choice: ");
    }
}
