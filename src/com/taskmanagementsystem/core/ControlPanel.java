package com.taskmanagementsystem.core;

import java.util.Scanner;

/**
 * It is a system that enables administrators and other workers to conduct various tasks like monitoring, maintaining, and controlling certain Task processes.
 */
public class ControlPanel implements Panel{


    @Override
    public void adminPanel() {
        Scanner input = new Scanner(System.in);
        boolean isExitRequested = false;
        int requestedChoice;

        do{
            Menu.adminPage();

            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-14), inclusive");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 14) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-14), inclusive");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            Main.admin.get( Main.currentUserAdminIndex).addMember(Main.admin.get(Main.currentUserAdminIndex));
                        }
                        case 2 -> {
                            Main.admin.get( Main.currentUserAdminIndex).deleteMember();
                        }
                        case 3 -> {
                            Main.admin.get( Main.currentUserAdminIndex).viewMember();
                        }
                        case 4 -> {
                            Main.admin.get( Main.currentUserAdminIndex).addProject();
                        }
                        case 5 -> {
                            Main.admin.get( Main.currentUserAdminIndex).deleteProject();
                        }
                        case 6 -> {
                            Main.admin.get( Main.currentUserAdminIndex).viewProject();
                        }
                        case 7 -> {
                            Main.admin.get( Main.currentUserAdminIndex).editProject();
                        }
                        case 8 -> {
                            Main.admin.get( Main.currentUserAdminIndex).addTask();
                        }
                        case 9 -> {
                            Main.admin.get( Main.currentUserAdminIndex).deleteTask();
                        }
                        case 10 -> {
                            Main.admin.get( Main.currentUserAdminIndex).editTask();
                        }
                        case 11 -> {
                            Main.admin.get( Main.currentUserAdminIndex).assignTask();
                        }
                        case 12 -> {
                            Main.admin.get( Main.currentUserAdminIndex).dashboard();
                        }
                        case 13 -> {
                            if( Main.admin.get( Main.currentUserAdminIndex).profile())
                                isExitRequested = true;
                        }
                        case 14 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);
    }

    @Override
    public void memberPanel() {
        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        do{
            Menu.memberPage();
            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-5), inclusive");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 5) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-5), inclusive");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            Main.member.get(Main.currentUserMemberIndex).viewTaskAssigned();
                        }
                        case 2 -> {
                            Main.member.get(Main.currentUserMemberIndex).editTask();
                        }
                        case 3 -> {
                            Main.member.get(Main.currentUserMemberIndex).dashboard();
                        }
                        case 4 -> {
                            if(Main.member.get( Main.currentUserMemberIndex).profile())
                                isExitRequested = true;
                        }
                        case 5 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);
    }

    @Override
    public void mainPanel() {
        boolean isExitRequested = false;
        int requestedChoice;
        Scanner input = new Scanner(System.in);

        do{
            Menu.mainPage();
            if(!input.hasNextInt()){
                input.nextLine();
                System.out.println("Please enter a number (1-4), inclusive");
            } else {
                requestedChoice = input.nextInt();
                if (requestedChoice < 1 || requestedChoice > 4) {
                    System.out.println(requestedChoice + " isn't an option, please enter a number (1-4), inclusive");
                } else {
                    switch (requestedChoice) {
                        case 1 -> {
                            AccessPanel.login();
                        }
                        case 2 -> {
                            AccessPanel.registerAsAdmin();
                        }
                        case 3 -> {
                            AccessPanel.registerAsMember();
                        }
                        case 4 -> isExitRequested = true;
                    }
                }
            }
        }while(!isExitRequested);
    }

}
