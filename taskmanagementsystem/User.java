package taskmanagementsystem3;

/**
 * This class is responsible for keeping track
 * of user information.
 *
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private int teamspace;
    static int numberOfUser = 0;


    /**
     * To create a new user by initializing.
     * @param name name for the user.
     * @param email email for the user.
     * @param password password for the user.
     * @param role role for user.
     */
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {

    }


    /**
     *
     * @return return id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return return name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return return role.
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @return return teamspace.
     */
    public int getTeamspace() {
        return teamspace;
    }

    /**
     *
     * @param name name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param email email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param password password for user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param password password for the user.
     */
    public void getPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param role role for the user.
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    public void setId(int id)
    {
        this.id = id;
        numberOfUser++;
    }
    /**
     *
     * @param teamspace teamspace for the user.
     */
    public void setTeamspace(int teamspace)
    {
        this.teamspace = teamspace;
    }

    /**
     * Print user information.
     */
    public void print()
    {
        System.out.printf("%15s %15s %25S \n", getName(), getId(), getEmail());
    }
}
