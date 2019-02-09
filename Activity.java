import java.io.*;

public class Activity implements Comparable {
    private String name;
    private int[] startTime;
    private int[] endTime;
    private int goalPerWeek;
    private int duration;
    //private FileInteractor fileInteractor;

    /**
     * Constructor for Acticity object
     *
     * @param name Name of activity
     */
    public Activity( String name) {
        this.name = name;
        startTime = new int[2];
        endTime = new int[2];
        goalPerWeek = 0;
        duration = calculateDuration();
        //fileInteractor = new FileInteractor();
    }

    public String getName() {
        return name;
    }

    public int[] getEndTime() {
        return endTime;
    }

    public int[] getStartTime() {
        return startTime;
    }

    public int calculateDuration() {
        int minutes = 60*(endTime[0] - startTime[0]) + endTime[1] - startTime[1];
        return minutes;
    }

    public int getGoalPerWeek() {
        return goalPerWeek;
    }

    public void setEndTime(int[] endTime) {
        this.endTime = endTime;
    }

    public void setGoalPerWeek(int goalPerWeek) {
        this.goalPerWeek = goalPerWeek;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Activity) {
            Activity a = (Activity) o;
            if(a.getDuration() > this.getDuration()) {
                return 1;
            }
            else if(a.getDuration() < this.getDuration()) {
                return -1;
            }
            else{
                return 0;
            }
        }
        else{
            return -2;
        }
    }

    public void setStartTime(int[] startTime) {
        this.startTime = startTime;
    }

    /**
     * File printer method for Activity object.
     *
     * @param fileName
     * @throws IOException
     */
    public void printToFile( String fileName) throws IOException {
        PrintWriter fileWrite = new FileInteractor(fileName).getFileWriter();
        fileWrite.print(name + "-");
        fileWrite.print(getDuration() + "-");
        fileWrite.println(goalPerWeek + "~");
        fileWrite.close();
    }
    public void setDuration(int x) {
        duration = x;
    }

    public int getDuration() {
        return duration;
    }
}
