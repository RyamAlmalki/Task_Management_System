package taskmanagementsystem3;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for keeping track
 * of admin information and operations each
 * member can perform.
 *
 */
public class Admin extends User {

    static int teamspace_counter = 1;
    ArrayList<Project> project = new ArrayList<Project>();

    /**
     *
     * @param name name for member.
     * @param email email for member.
     * @param password password for member.
     * @param role role for member.
     */
    public Admin(String name, String email, String password, String role) {
        super(name, email, password, role);
        super.setTeamspace(teamspace_counter++);
        super.setId(numberOfUser);
    }

    public ArrayList<Project> getProject() {
        return project;
    }

    public void addMember(ArrayList<Member> member, String email, int teamspace) {
        member.add(new Member(email, "member", teamspace));
        System.out.println("The Member has been added to your Teamspace!!");
        System.out.println("Please provide the Member with your Teamspace ID to be able to register under this teamspace");
        System.out.println("Teamspace id: " + teamspace);
    }

    public void viewMember(ArrayList<Member> member, int teamspace) {
        for (Member value : member) {
            if (value.getTeamspace() == teamspace){
                System.out.printf("\n-------------------------------------------------------------------------%n");
                System.out.print("                           MEMBER INFORMATION                                  ");
                System.out.printf("\n-------------------------------------------------------------------------%n");
                System.out.printf("%15S %15s %25S %15s", "NAME", "ID", "EMAIL", "ACCOUNT");
                System.out.printf("\n--------------------------------------------------------------------------%n");
                value.print();
            }
        }
    }

    public void addProject(int teamspace) {
        Scanner input = new Scanner(System.in);
        String projectTitle;

        System.out.print("Project Title: ");
        projectTitle = input.nextLine();
        project.add(new Project(projectTitle, teamspace));
        System.out.println("Project under the name of " +projectTitle+" has been created!");
        System.out.println("You can get stated now with adding tasks, and assigning work to your members :)");
    }

    public void viewProject() {
        System.out.printf("\n--------------------------------------------------------------%n");
        System.out.print("                        PROJECT CREATED             ");
        System.out.printf("\n---------------------------------------------------------------%n");
        System.out.printf("%30s %20s", "NAME", "ID");
        System.out.printf("\n---------------------------------------------------------------%n");
        for (Project value : project) {
            value.print();
        }
    }

    public void addTask() {
        Scanner input = new Scanner(System.in);
        int projectID;

        viewProject();
        System.out.println("\n\nPlease, enter project id for task to be added in it:");
        System.out.print("Project ID: ");
        projectID = input.nextInt();

        for (Project value : project) {
            if (value.getProjectID() == projectID) {
                value.addTask();
            }
        }
    }


    public void viewProjectStatus(int teamspaceID) {
        Scanner input = new Scanner(System.in);
        int projectID;

        viewProject();
        System.out.println("\n\nPlease, enter project id for task to be added in it:");
        System.out.print("Project ID:");
        projectID = input.nextInt();

        for (Project value : project) {
            if (value.getProjectID() == projectID && value.getTeamspace() == teamspaceID) {
                System.out.printf("\n-------------------------------------------------------------------------------%n");
                System.out.printf("                             " + value.getProjectTitle() + "                        ");
                System.out.printf("\n-------------------------------------------------------------------------------%n");
                System.out.printf("%30S %15S %15s %15s", "TASK NAME", "ID", "STATUS", "OWNER ID");
                System.out.printf("\n-------------------------------------------------------------------------------%n");
                value.taskUnderProject();
            }
        }
    }


    public void assignTask(ArrayList<Member> members, int teamspaceID) {
        Scanner input = new Scanner(System.in);
        int projectID, memberID;
        boolean isFound = false;

        viewMember(members, teamspaceID);
        System.out.println("\n\nPlease, chose a member to be assigned a task:");
        System.out.print("Provide the Member ID:");
        memberID = input.nextInt();

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getId() == memberID && members.get(i).getAccount() != Account.INACTIVE) {
                viewProject();
                System.out.println("\n\nPlease, enter project id for member to be added in it:");
                System.out.print("Project ID:");
                projectID = input.nextInt();

                for (Project value : project) {
                    if (value.getProjectID() == projectID) {
                        System.out.printf("\n-------------------------------------------------------------------------------%n");
                        System.out.printf("                             " + value.getProjectTitle() + "                        ");
                        System.out.printf("\n-------------------------------------------------------------------------------%n");
                        System.out.printf("%30S %15S %15s %15s", "TASK NAME", "ID", "STATUS", "OWNER ID");
                        System.out.printf("\n-------------------------------------------------------------------------------%n");
                        value.taskUnderProject();
                        value.assignTask(memberID, members.get(i));
                        isFound = true;
                    }
                }

                if(!isFound) {
                    System.out.println("Please add a project or chose a valid id!");
                }
            }
        }

        if(!isFound) {
            System.out.println("Make sure account is ACTIVE and AVAILABLE before you assign a task ;)");
        }

    }

    public void assignProject(ArrayList<Member> members, int teamspaceID) {
        Scanner input = new Scanner(System.in);
        int projectID, memberID;
        boolean isFound = false;

        viewMember(members, teamspaceID);
        System.out.println("\n\nPlease, chose a member to be assigned a task:");
        System.out.print("Provide the Member ID:");
        memberID = input.nextInt();

        for (Member member : members) {

            if (member.getId() == memberID && member.getAccount() != Account.INACTIVE) {
                viewProject();
                System.out.println("\n\nPlease, enter project id for member to be added in it:");
                System.out.print("Project ID:");
                projectID = input.nextInt();

                for (Project value : project) {
                    if (value.getProjectID() == projectID) {
                        System.out.println(member);
                        value.assignMember(member.getId());
                        isFound = true;
                    }
                }

                if(!isFound) {
                    System.out.println("Please add a project or chose a valid id!");
                }
            }
        }

        if(!isFound) {
            System.out.println("Make sure account is ACTIVE and AVAILABLE before you assign a task ;)");
        }

    }


}


