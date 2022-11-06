package com.taskmanagementsystem.core;

/**
 *
 * This User abstract class is responsible for representing all
 * the classes derived from it. This class is not used to instantiate
 * from it a user object but provides the necessary information that each subclass must have.
 */
public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int teamspaceId;
    static int numberOfUser = 20220000;


    /**
     * initializing user information.
     * @param name name for the user.
     * @param email email for the user.
     * @param password password for the user.
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * TeamspaceId is a unique number that manage different workspace environment.
     * This method will help when a user wants to find their workspace.
     * @return return teamspaceId.
     */
    public int getTeamspaceId() {
        return teamspaceId;
    }

    /**
     * Assign the User id the current numberOfUser, then increment
     * the numberOfUser for the following User who gets added to the systems.
     * This method will ensure that each User has a unique id.
     * @param id set the id with the current numberOfUser.
     */
    public void setId(int id) {
        this.id = id;
        numberOfUser++;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * When the user admin gets initialized, this method will be called to set a
     * unique id for the admin and member's workspace.
     * @param teamspaceId teamspace for the user.
     */
    public void setTeamspaceId(int teamspaceId)
    {
        this.teamspaceId = teamspaceId;
    }

    public abstract boolean profile();
    public abstract void dashboard();
    public abstract void deactivateAccount();
    public abstract void editTask();
    public abstract void viewProject();
}
