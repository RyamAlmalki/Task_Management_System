package taskmanagementsystem3;

public class Task {
    private final int taskID;
    private String taskName;
    public int taskProjectID;
    public int taskMemberID;
    static int count = 1000;
    private final int teamspace;
    private State state;


    Task(String taskName, int teamspace, int taskProjectID)
    {
        this.taskName = taskName;
        this.teamspace = teamspace;
        this.taskProjectID = taskProjectID;
        taskID = count++;
        this.state = State.TODO;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public int getTaskProjectID()
    {
        return taskProjectID;
    }

    public int getTaskID()
    {
        return taskID;
    }

    public int getTeamspace()
    {
        return teamspace;
    }

    public State getStatus() {
        return state;
    }

    public void setStatus(State state) {
        this.state = state;
    }

    public void setTaskMemberID(int taskMemberID)
    {
        this.taskMemberID = taskMemberID;
    }
    public int getTaskMemberID()
    {
        return taskMemberID;
    }
    public void print()
    {
        System.out.printf("%30S %15s %15s %15s\n", getTaskName(), getTaskID(), getStatus(), getTaskMemberID());
    }

}
