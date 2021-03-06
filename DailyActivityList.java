import java.io.*;
import java.util.*;

public class DailyActivityList {
    private ArrayList<Activity> activities;
    private Date date;


    /**
     * Constructor for DailyActivityList object.
     *
     * @param date Date for activity
     * @param activities List of activities
     */
    public DailyActivityList( Date date, ArrayList<Activity> activities) {
        this.date = date;
        this.activities = activities;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Date getDate() {
        return date;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Adds activity to arraylist.
     *
     * @param a activity
     */
    public void addActivity( Activity a) {
        activities.add(a);
    }

    /**
     * Adds activities to file daily.
     *
     * @param fileName name of the file
     * @throws IOException
     */

    public void addDailyActivitiesToFile(String fileName) throws IOException {
        //File file = new File( fileName);
        FileInteractor fileInteractor = new FileInteractor(fileName);

        PrintWriter fileWrite = new PrintWriter(new FileWriter( fileName, true));
        fileWrite.print(date + "~");
        for(int i = 0; i < activities.size(); i++) {
            fileWrite.print(date + "~");
            activities.get(i).printToFile( fileName);
        }
    }
}
