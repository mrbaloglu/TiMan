//DONE
import javafx.scene.chart.BarChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ActivitySummaryPanel extends JPanel {

    public JPanel pieChartPanel;
    public JPanel barChartPanel;
    public Activity activity;
    FileInteractor fileInteractor, fileInteractor2;
    JLabel triLabel,triLabel1,triLabel2,triLabel3;
    ImageIcon tri,tri2;


    String username;
    int duration;

    /**
     * Constructor for ActivitySummaryPanel object
     *
     * @param a Activity
     * @param username Username of the user
     * @throws Exception
     */

    public ActivitySummaryPanel( Activity a, String username) throws Exception {
        setLayout(null);
        setBackground(new Color(38, 52, 71));

        this.username = username;
        activity = a;
        duration = 0;

        fileInteractor = new FileInteractor(username+".txt");
        fileInteractor2 = new FileInteractor(username+".txt");

        createBarChartPanel();
        createPieChartPanel();

    }

    /***
     * creates pie chart panel
     */
    public void createPieChartPanel() {

         //get info about this activity from file for last week
        Date date1 = new java.util.Date();
        String sdate1 = date1+"";
        String[] ssdate1 = sdate1.split(" ");
        final String stringForDate1 = ssdate1[1]+" "+ssdate1[2]+" "+ssdate1[5];
        final String stringForDate2 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-1)+" "+ssdate1[5];
        final String stringForDate3 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-2)+" "+ssdate1[5];
        final String stringForDate4 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-3)+" "+ssdate1[5];
        final String stringForDate5 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-4)+" "+ssdate1[5];
        final String stringForDate6 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-5)+" "+ssdate1[5];
        final String stringForDate7 = ssdate1[1]+" "+(Integer.parseInt(ssdate1[2])-6)+" "+ssdate1[5];
        int count = 0;
        String[] activitytemplate;
        String[] sactivity;
        while( fileInteractor2.getFileScanner().hasNextLine()) {
            if( count > 0) {
                String str = fileInteractor2.getFileScanner().nextLine();
                activitytemplate = str.split("~");
                String[] datetemplate = activitytemplate[0].split(" ");
                String filedate = datetemplate[1]+" "+datetemplate[2]+" "+datetemplate[5];
                if( filedate.equals(stringForDate1) || filedate.equals(stringForDate2) || filedate.equals(stringForDate3)
                        || filedate.equals(stringForDate4) || filedate.equals(stringForDate5) || filedate.equals(stringForDate6)
                        || filedate.equals(stringForDate7)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            duration += Integer.parseInt(sactivity[1]);
                        }
                    }
                }
            }
            else {
                fileInteractor2.getFileScanner().nextLine();
            }
            count++;
        }
        fileInteractor2.getFileScanner().close();

        //draw a pie chart for last week's overall info
        final DefaultPieDataset data = new DefaultPieDataset();
        data.setValue(activity.getName(), duration);
        data.setValue("Goal", activity.getGoalPerWeek() - duration);
        JFreeChart chart = ChartFactory.createPieChart("Weekly progress on "+ activity.getName(), data, false,true,false);
        pieChartPanel = new ChartPanel(chart);
        pieChartPanel.setBounds(270,150,500,500);
        add(pieChartPanel);
    }

    /***
     * creates a bar chart panel
     */
    public void createBarChartPanel() {

        //draw bar chart
        Date date1 = new java.util.Date();
        String sdate1 = date1+"";
        String[] ssdate1 = sdate1.split(" ");
         String stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
         String stringForDate2 = ssdate1[1] + " " + (29) + " " + ssdate1[5];
         String stringForDate3 = ssdate1[1] + " " + (28) + " " + ssdate1[5];
         String stringForDate4 = ssdate1[1] + " " + (27) + " " + ssdate1[5];
         String stringForDate5 = ssdate1[1] + " " + (26) + " " + ssdate1[5];
         String stringForDate6 = ssdate1[1] + " " + (25) + " " + ssdate1[5];
         String stringForDate7 = ssdate1[1] + " " + (24) + " " + ssdate1[5];
        if( Integer.parseInt(ssdate1[2]) > 1 ) {
            stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
            stringForDate2 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 1) + " " + ssdate1[5];
            stringForDate3 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 2) + " " + ssdate1[5];
            stringForDate4 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 3) + " " + ssdate1[5];
            stringForDate5 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 4) + " " + ssdate1[5];stringForDate6 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 5) + " " + ssdate1[5];
            stringForDate7 = ssdate1[1] + " " + (Integer.parseInt(ssdate1[2]) - 6) + " " + ssdate1[5];
        }
        else {
            //if the month before has 31 days
            if(ssdate1[1].equals("Feb") || ssdate1[1].equals("Apr") || ssdate1[1].equals("Jun") || ssdate1[1].equals("Aug") ||
                    ssdate1[1].equals("Sep") || ssdate1[1].equals("Oct") || ssdate1[1].equals("Jan") ) {
                stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
                stringForDate2 = ssdate1[1] + " " + (31) + " " + ssdate1[5];
                stringForDate3 = ssdate1[1] + " " + (30) + " " + ssdate1[5];
                stringForDate4 = ssdate1[1] + " " + (29) + " " + ssdate1[5];
                stringForDate5 = ssdate1[1] + " " + (28) + " " + ssdate1[5];
                stringForDate6 = ssdate1[1] + " " + (27) + " " + ssdate1[5];
                stringForDate7 = ssdate1[1] + " " + (26) + " " + ssdate1[5];
            }
            //if the month before has 30 days
            else if( ssdate1[1].equals("May") || ssdate1[1].equals("Jul") || ssdate1[1].equals("Oct") || ssdate1[1].equals("Dec") ) {
                stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
                stringForDate2 = ssdate1[1] + " " + (30) + " " + ssdate1[5];
                stringForDate3 = ssdate1[1] + " " + (29) + " " + ssdate1[5];
                stringForDate4 = ssdate1[1] + " " + (28) + " " + ssdate1[5];
                stringForDate5 = ssdate1[1] + " " + (27) + " " + ssdate1[5];
                stringForDate6 = ssdate1[1] + " " + (26) + " " + ssdate1[5];
                stringForDate7 = ssdate1[1] + " " + (25) + " " + ssdate1[5];
            }
            //if march
            else {
                //look at if it is an leap year
                if( Integer.parseInt(ssdate1[5]) % 4 == 0) {
                    stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
                    stringForDate2 = ssdate1[1] + " " + (29) + " " + ssdate1[5];
                    stringForDate3 = ssdate1[1] + " " + (28) + " " + ssdate1[5];
                    stringForDate4 = ssdate1[1] + " " + (27) + " " + ssdate1[5];
                    stringForDate5 = ssdate1[1] + " " + (26) + " " + ssdate1[5];
                    stringForDate6 = ssdate1[1] + " " + (25) + " " + ssdate1[5];
                    stringForDate7 = ssdate1[1] + " " + (24) + " " + ssdate1[5];
                }
                else {
                    stringForDate1 = ssdate1[1] + " " + ssdate1[2] + " " + ssdate1[5];
                    stringForDate2 = ssdate1[1] + " " + (28) + " " + ssdate1[5];
                    stringForDate3 = ssdate1[1] + " " + (27) + " " + ssdate1[5];
                    stringForDate4 = ssdate1[1] + " " + (26) + " " + ssdate1[5];
                    stringForDate5 = ssdate1[1] + " " + (25) + " " + ssdate1[5];
                    stringForDate6 = ssdate1[1] + " " + (24) + " " + ssdate1[5];
                    stringForDate7 = ssdate1[1] + " " + (23) + " " + ssdate1[5];
                }
            }
        }

        int count = 0;
        String[] activitytemplate;
        String[] sactivity;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int counter = 0;
        while( fileInteractor.getFileScanner().hasNextLine()) {
            System.out.println(counter);
            if( counter > 0) {
                String str = fileInteractor.getFileScanner().nextLine();
                activitytemplate = str.split("~");
                String[] datetemplate = activitytemplate[0].split(" ");
                String filedate = datetemplate[1]+" "+datetemplate[2]+" "+datetemplate[5];
                System.out.println(filedate);
                if(filedate.equals(stringForDate1)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate1);
                        }
                    }
                    System.out.println(stringForDate1);
                }
                else if( filedate.equals(stringForDate2)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate2);
                        }
                    }
                    System.out.println(stringForDate2);
                }
                else if (filedate.equals(stringForDate3)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate3);
                        }
                    }
                    System.out.println(stringForDate3);
                }
                else if (filedate.equals(stringForDate4)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate4);
                        }
                    }
                }
                else if (filedate.equals(stringForDate5)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate5);
                        }
                    }
                }
                else if (filedate.equals(stringForDate6)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate6);
                        }
                    }
                }
                else if (filedate.equals(stringForDate7)) {
                    for (int i = 1; i < activitytemplate.length; i++) {
                        sactivity = activitytemplate[i].split("-");
                        if (sactivity[0].equals(activity.getName())) {
                            dataset.addValue(Integer.parseInt(sactivity[1]), "Mins", stringForDate7);
                        }
                    }
                }
            }
            else {
                fileInteractor.getFileScanner().nextLine();
                counter++;
            }

        }
        fileInteractor.getFileScanner().close();
        JFreeChart barChart = ChartFactory.createBarChart(
                "Your weekly progress on ",
                "Day",
                "Mins",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        barChartPanel = new ChartPanel( barChart );
        barChartPanel.setBounds(870,150,500,500);
        add(barChartPanel);
    }

    /***
     * creates the labels
     */
    public void createLabels() {
        triLabel = new JLabel(tri);
        triLabel1 = new JLabel(tri2);
        triLabel2 = new JLabel(tri);
        triLabel3 = new JLabel(tri);

        triLabel.setBounds(-100,650,633,267);
        triLabel1.setBounds(300,650,633,267);
        triLabel2.setBounds(700,650,633,267);
        triLabel3.setBounds(1100,650,633,267);

        add(triLabel);
        add(triLabel1);
        add(triLabel2);
        add(triLabel3);
    }

    /***
     * creates image icons
     */
    public void createImageIcons() {
        /*tri = new ImageIcon(getClass().getResource("tri2.png"));
        tri2 = new ImageIcon(getClass().getResource("tri1.png"));*/
    }
}
