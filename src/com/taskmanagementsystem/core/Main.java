package com.taskmanagementsystem.core;

import java.util.ArrayList;


/**
 *
 */
public class Main {

    static ArrayList<Admin> admin = new ArrayList<Admin>();
    static ArrayList<Member> member = new ArrayList<Member>();
    static ArrayList<String> deactivatedMembers = new ArrayList<String>();
    static int currentUserTeamspaceId;
    static int currentUserAdminIndex;
    static int currentUserMemberIndex;
    static Panel panel = new ControlPanel();


    public static void main(String[] args){
        panel.mainPanel();
    }

    public static void adminPanel(){
        panel.adminPanel();
    }

    public static void memberPanel(){
        panel.memberPanel();
    }

}
