package com.taskmanagementsystem.core;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to access users to the system
 */
public class AccessPanel {

    /**
     * Register the admin to the system
     */
    public static void registerAsAdmin() {
        String name, email, password;
        boolean isNotRegistered;

        Menu.registerPage();
        System.out.println("Enter your information");
        System.out.print("Full Name:");
        name = Validation.inputStringValidation();

        do{
            System.out.print("Email:");
            email = Validation.emailFormatValidation();

            if(!Validation.isEmailRegistered(Main.admin, email))
            {
                isNotRegistered = true;
            }
            else {
                System.out.println("Email is taken, Please try again");
                isNotRegistered = false;
            }
        }while(!isNotRegistered);

        System.out.print("Password:");
        password = Validation.passwordFormatValidation();

        Main.admin.add(new Admin(name, email, password));

        for(int i = 0; i < Main.admin.size();i++)
        {
            if (Main.admin.get(i).getPassword().equals(password) && Main.admin.get(i).getEmail().equals(email)) {
                Main.currentUserTeamspaceId = Main.admin.get(i).getTeamspaceId();
                Main.currentUserAdminIndex = i; // set this as the current user of the system
                System.out.println("Success!");
                Main.adminPanel();
            }
        }
    }

    /**
     * login admin and member
     */
    public static void login()
    {
        String email, password;
        char role;
        Boolean isRegistered = false;

        Menu.loginPage();
        System.out.println("Enter your information");
        System.out.print("Are you (A - Admin, M - Member):");
        role = Validation.inputRoleValidation();

        System.out.print("Email:");
        email = Validation.emailFormatValidation();

        if(role == 'A' || role == 'a'){

            if(Validation.isEmailRegistered(Main.admin,email))
            {
                System.out.print("Password:");
                password = Validation.passwordFormatValidation();

                for(int i = 0; i < Main.admin.size();i++)
                {
                    if (Main.admin.get(i).getPassword().equals(password) && Main.admin.get(i).getEmail().equals(email)) {
                        Main.currentUserTeamspaceId = Main.admin.get(i).getTeamspaceId();
                        Main.currentUserAdminIndex = i;
                        System.out.println("Success!");
                        Main.adminPanel();
                        isRegistered = true;
                    }
                }

                if(!isRegistered){
                    System.out.println("Sorry, Invalid information");
                }

            }else{
                System.out.println("Email is not available, please register first!");
            }
        }
        else if(role == 'M' || role == 'm'){
            if(Validation.isEmailRegistered(email, Main.member))
            {

                for(int i = 0; i < Main.member.size();i++)
                {
                    if (Main.member.get(i).getPassword() != null) {

                        System.out.print("Password:");
                        password = Validation.passwordFormatValidation();

                        if(Main.member.get(i).getPassword() != null && Main.member.get(i).getEmail().equals(email))
                        {
                            Main.currentUserTeamspaceId = Main.member.get(i).getTeamspaceId();
                            Main.currentUserMemberIndex = i;
                            System.out.println("Success!");
                            Main.memberPanel();
                            isRegistered = true;
                        }

                    }
                    else if(Main.member.get(i).getPassword() == null) {
                        System.out.println("Please register under teamspace for your account to be active before you login");
                        isRegistered = true;
                    }

                    if(!isRegistered){
                        System.out.println("Sorry, Invalid information");
                    }
                }
            }
            else if(Validation.isPreviousMember(email)) {
                Menu.previousMemberMessagePage(); // return a message if previous user tried to log in
            }
            else{
                System.out.println("Email is not available, please register first!");
            }
        }
    }

    /**
     * Register for members
     */
    public static void registerAsMember()
    {
        String email;
        int teamspaceCode;
        Scanner input = new Scanner(System.in);
        boolean isRegisteredViaCode = false;

        Menu.registerPage();
        Menu.teamspaceCodePage();
        System.out.println("Enter your information");
        System.out.print("Email:");
        email = Validation.emailFormatValidation();
        System.out.print("Teamspace Code:");
        teamspaceCode = Validation.intValidation();


        for(int i = 0; i < Main.member.size();i++)
        {
            if (Main.member.get(i).getEmail().equals(email) && Main.member.get(i).getTeamspaceId() == Main.currentUserTeamspaceId && Main.member.get(i).getPassword() == null) {
                Main.currentUserTeamspaceId = Main.member.get(i).getTeamspaceId();
                Main.currentUserMemberIndex = i;

                String name, password;

                Menu.welcomePage();
                System.out.println("Please include missing information for your account login");
                System.out.print("Full Name:");
                name = Validation.inputStringValidation();

                System.out.print("Password:");
                password = Validation.passwordFormatValidation();

                Main.member.get(i).setPassword(password);
                Main.member.get(i).setName(name);
                Main.member.get(i).setState(UserState.ACTIVE);
                System.out.println("Success!");
                Main.memberPanel();
                isRegisteredViaCode = true;
            }
        }

        if(!isRegisteredViaCode)
        {
            System.out.println("Invalid information");
        }

    }

}
