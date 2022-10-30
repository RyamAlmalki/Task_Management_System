package taskmanagementsystem3;

import java.util.ArrayList;

/**
 * This class is responsible for keeping track
 * of member information and operations each
 * member can perform.
 *
 */
public class Member extends User {


    private Account account;
    /**
     *
     * @param email email for user
     * @param role role for user
     * @param teamspace teamspace for user
     */
    public Member(String email, String role, int teamspace) {
        super.setEmail(email);
        super.setRole(role);
        super.setTeamspace(teamspace);
        super.setId(numberOfUser);
        setAccount(Account.INACTIVE);
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public void print()
    {
        System.out.printf("%15s %15s %25S %15s\n", getName(), getId(), getEmail(), getAccount());
    }


    public void viewProjectAssigned(ArrayList<Admin> admin) {
        ArrayList<Project> projects;
        System.out.printf("\n--------------------------------------------------------------%n");
        System.out.print("                        PROJECT ASSIGNED             ");
        System.out.printf("\n---------------------------------------------------------------%n");
        System.out.printf("%30s %20s", "NAME", "ID");
        System.out.printf("\n---------------------------------------------------------------%n");

        for (Admin value : admin) {
            if(value.getTeamspace() == getTeamspace()){// if admin has same teamspace as the member then look for projects
                // take member if and look through which project it has been assigned
                projects = value.getProject(); // we have all the projects under this admin
                // one or many of these projects can be for admin

                for(Project projectValue: projects){
                    projectValue.viewAssignMember(getId());
                }
            }
        }
    }
}


// we can have many admins we need the admin with that specific teamspace
// admin will have all projects
// user can be in any one of these projects

