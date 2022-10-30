package taskmanagementsystem3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static ArrayList<Admin> admin = new ArrayList<Admin>();
    public static ArrayList<Member> member = new ArrayList<Member>();
    static int teamspaceID;
    static int currentUserAdminIndex;
    static int currentUserMemberIndex;
    static String currentUserRole = "";

    public static void main(String[] args){
        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        do{
            mainPage();
            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-4), inclusive)");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 4) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-4), inclusive)");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            login();
                        }
                        case 2 -> {
                            register();
                        }
                        case 3 -> {
                            String email;
                            int teamspaceCode;

                            System.out.println("Enter your information");
                            System.out.print("Email:");
                            email = emailFormatValidation();
                            System.out.print("Teamspace Code:");
                            teamspaceCode = input.nextInt();
                            register(email, teamspaceCode);
                        }
                        case 4 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);
    }


    public static void register(String email, int teamspace)
    {
        boolean isRegisteredViaCode = false;
        for(int i = 0; i < member.size();i++)
        {
            if (member.get(i).getEmail().equals(email) && member.get(i).getTeamspace() == teamspace) {
                teamspaceID = member.get(i).getTeamspace();
                currentUserAdminIndex = i;

                String name, password;

                System.out.println("\nWelcome you have been added to the teamspace");
                System.out.println("Please include missing information for your account login");
                System.out.print("Full Name:");
                name = inputStringValidation();

                System.out.print("Password:");
                password = passwordFormatValidation();

                member.get(i).setPassword(password);
                member.get(i).setName(name);
                member.get(i).setAccount(Account.ACTIVE);
                memberPage();
                isRegisteredViaCode = true;
            }
        }

        if(!isRegisteredViaCode)
        {
            System.out.println("Invalid information");
        }
    }


    public static void login()
    {
        String email, password;

        System.out.println("Enter your information");
        System.out.print("Email:");
        email = emailFormatValidation();

        if(isRegistered(email, "admin") || isRegistered(email, "member") )
        {
            System.out.print("Password:");
            password = passwordFormatValidation();

            if(isUserExistConfigure(password, email, "admin") || isUserExistConfigure(password, email, "member"))
            {
                if(currentUserRole.equals("admin"))
                    adminPage();
                else memberPage();

            }
            else
            {
                System.out.println("Sorry, Invalid information");
            }

        }
        else {
            System.out.println("Email is not available, please register first!");
        }
    }

    public static boolean isRegistered(String email, String searchChoice) {

        if(searchChoice.equals("admin")) {
            for (Admin value : admin) {
                if (value.getEmail().equals(email) && value.getPassword() != null) {
                    return true;
                }
            }

        }

        if (searchChoice.equals("member")){
            for (Member value : member) {
                if (value.getEmail().equals(email) && value.getPassword() != null) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean isUserExistConfigure(String password, String email, String searchChoice) {

        if(searchChoice.equals("admin")) {
            for(int i = 0; i < admin.size();i++)
            {
                if (admin.get(i).getPassword().equals(password) && admin.get(i).getEmail().equals(email)) {
                    teamspaceID = admin.get(i).getTeamspace();
                    currentUserAdminIndex = i;
                    currentUserRole = "admin";
                    return true;
                }
            }
        }

        if (searchChoice.equals("member")) {
            for(int i = 0; i < member.size();i++)
            {
                if (member.get(i).getPassword().equals(password) && member.get(i).getEmail().equals(email)) {
                    teamspaceID = member.get(i).getTeamspace();
                    currentUserMemberIndex = i;
                    currentUserRole = "member";
                    return true;
                }
            }
        }

        return false;
    }

    public static void register()
    {
        String name, email, password;
        boolean isNotRegistered;

        System.out.println("Enter your information");
        System.out.print("Full Name:");
        name = inputStringValidation();

        do{
            System.out.print("Email:");
            email = emailFormatValidation();

            if(!isRegistered(email, "admin")) // false make true
            {
                isNotRegistered = true;
            }
            else {
                System.out.println("Email is taken, Please try again");
                isNotRegistered = false;
            }
        }while(!isNotRegistered);

        System.out.print("Password:");
        password = passwordFormatValidation();

        admin.add(new Admin(name, email, password, "admin"));

        if(isUserExistConfigure(password, email, "admin"))
            adminPage();

    }


    public static String inputStringValidation()
    {
        Scanner input = new Scanner(System.in);
        boolean isString;
        String stringInput = "";

        do{
            if(!input.hasNextInt())
            {
                stringInput = input.nextLine();
                isString = true;
            }
            else {
                System.out.println("Invalid input, Please try again");
                isString = false;
                input.next();
            }
        }while(!isString);

        return stringInput;
    }


    public static String emailFormatValidation()
    {
        Scanner input = new Scanner(System.in);
        boolean isValid;
        String re = "^(.+)@(.+)$";
        String text = "";


        do{
            text = input.nextLine();
            Pattern pt = Pattern.compile(re);
            Matcher mt = pt.matcher(text);

            if(mt.matches())
            {
                isValid = true;
            }
            else {
                System.out.println("Invalid email format, Please try again");
                isValid = false;
            }

        }while(!isValid);

        return text;
    }

    public static String passwordFormatValidation() {
        Scanner input = new Scanner(System.in);
        boolean isValid;
        String re = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        String text = "";

        do{
            text = input.nextLine();
            Pattern pt = Pattern.compile(re);
            Matcher mt = pt.matcher(text);

            if(mt.matches())
            {
                isValid = true;
            }
            else {
                System.out.println("Invalid password, Please try again");
                isValid = false;
            }

        }while(!isValid);

        return text;
    }


    public static void mainPage() {
        System.out.printf("\n------------------------------------------------------------------%n");
        System.out.printf("               WELCOME TO THE TASK MANAGEMENT SYSTEM             %n");
        System.out.printf("------------------------------------------------------------------%n");
        System.out.println("(1) LOGIN");
        System.out.println("(2) REGISTER");
        System.out.println("(3) REGISTER WITH VERIFICATION CODE");
        System.out.println("(4) EXIT");
        System.out.println();
        System.out.print("Enter your choice: ");
    }


    public static void adminPage()
    {
        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        do{
            System.out.printf("\n------------------------------------------------------------------%n");
            System.out.printf("                         WELCOME ADMIN                            %n");
            System.out.printf("------------------------------------------------------------------%n");
            System.out.println("1  - Add Member  ");
            System.out.println("2  - Remove Member");
            System.out.println("3  - View Members");
            System.out.println("4  - Add Project     ");
            System.out.println("5  - Remove Project    ");
            System.out.println("6  - Assign Project    ");
            System.out.println("7  - Add Task     ");
            System.out.println("8  - Remove Task    ");
            System.out.println("9  - Assign Task    ");
            System.out.println("10 - Project Status     ");
            System.out.println("11 - Profile    ");
            System.out.println("12 - Exit       ");
            System.out.print("Enter your choice: ");

            System.out.println(teamspaceID);
            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-11, inclusive)");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 12) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-11, inclusive)");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            Scanner in = new Scanner(System.in);
                            String email;

                            System.out.println("Write the Member Email to Join your Teamspace!");
                            System.out.print("Email: ");
                            email = emailFormatValidation();
                            admin.get(currentUserAdminIndex).addMember(member, email, teamspaceID);
                        }
                        case 2 -> {
                            System.out.println("hi3");
                        }
                        case 3 -> {
                            admin.get(currentUserAdminIndex).viewMember(member, teamspaceID);
                        }
                        case 4 -> {
                            admin.get(currentUserAdminIndex).addProject(teamspaceID);
                        }
                        case 6 -> {
                            admin.get(currentUserAdminIndex).assignProject(member, teamspaceID);
                        }
                        case 7 -> {
                            admin.get(currentUserAdminIndex).addTask();
                        }
                        case 9 -> {
                            admin.get(currentUserAdminIndex).assignTask(member, teamspaceID);
                        }
                        case 10 -> {
                            admin.get(currentUserAdminIndex).viewProjectStatus(teamspaceID);
                        }
                        case 12 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);
    }


    public static void memberPage() {

        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        do{
            System.out.printf("\n------------------------------------------------------------------%n");
            System.out.printf("                         WELCOME MEMBER                            %n");
            System.out.printf("------------------------------------------------------------------%n");
            System.out.println("1 - View Task");
            System.out.println("2 - View Project");
            System.out.println("3 - Edit Task");
            System.out.println("4 - Profile    ");
            System.out.println("5 - Exit");
            System.out.print("\nEnter your choice: ");

            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-5, inclusive)");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 6) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-11, inclusive)");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            System.out.println("hi1");
                        }
                        case 2 -> {
                            member.get(currentUserMemberIndex).viewProjectAssigned(admin);
                        }
                        case 3 -> {
                            System.out.println("hi2");
                        }
                        case 5 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);

    }


}


