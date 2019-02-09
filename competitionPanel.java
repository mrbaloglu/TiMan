import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class competitionPanel extends JPanel {
	
    static Dimension dim = new Dimension(1280,720);
    
    ImageIcon leaderImage,tri,tri2, exitImage, settingImage, trophyImage;
    JLabel triLabel,triLabel1,triLabel2,triLabel3, trophy;
    JLabel leaderLabel , activityLabel ,first ,second, third;
    JButton exit, back;
    JPanel buttonPanel;
    FileInteractor fileInteractor1, fileInteractor2;
    ArrayList<String> users;
    String activityName;

    /**
     * Constructor for Activity object
     *
     * @param activityName Name of activity.
     * @throws Exception
     */
    public competitionPanel(String activityName) throws Exception {
        this.activityName = activityName;
        setSize(dim);
        setLayout(null);
        setBackground(new Color(38, 52, 71));
        users = new ArrayList<String>();
        fileInteractor1 = new FileInteractor("users.txt");
        fileInteractor1.getFileScanner().useDelimiter("-");

        while (fileInteractor1.getFileScanner().hasNext()) {
            users.add( fileInteractor1.getFileScanner().next());
        }
        System.out.println(users);
        fileInteractor1.getFileScanner().close();

        String[] winners = winners();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(1200, 700, 110, 55);
        buttonPanel.setBackground(new Color(38, 52, 71));
        
        exitImage = new ImageIcon(getClass().getResource("logout2.png"));


        exit = new JButton(exitImage);
        exit.setBounds(0, 0, 55, 55);
        
        leaderImage = new ImageIcon(getClass().getResource("podbig.png"));
        /*tri = new ImageIcon(getClass().getResource("tri2.png"));
        tri2 = new ImageIcon(getClass().getResource("tri1.png"));*/
        trophyImage = new ImageIcon(getClass().getResource("trophy.png"));
        
        leaderLabel = new JLabel(leaderImage);
        triLabel = new JLabel(tri);
        triLabel1 = new JLabel(tri2);
        triLabel2 = new JLabel(tri);
        triLabel3 = new JLabel(tri);
        trophy = new JLabel(trophyImage);
        
        leaderLabel.setBounds(450, 200, 512, 512);
        triLabel.setBounds(-100,700,633,267);
        triLabel1.setBounds(300,700,633,267);
        triLabel2.setBounds(700,700,633,267);
        triLabel3.setBounds(1100,700,633,267);
        trophy.setBounds(370,550,150,150);

        if(winners[2].substring(0,1).equals("0")) {
            first = new JLabel();
        } else {
            first = new JLabel(winners[2].substring(winners[2].indexOf(" ")));
        }
        first.setForeground(Color.WHITE);
        first.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
        first.setBounds(680,350,150,20);

        if(winners[1].substring(0,1).equals("0")) {
            second = new JLabel();
        } else {
            second = new JLabel(winners[1].substring(winners[1].indexOf(" ")));
        }
        second.setForeground(Color.WHITE);
        second.setFont(new Font("SANS_SERIF", Font.BOLD, 20));  
        second.setBounds(500,440,150,20);

        if(winners[2].substring(0,1).equals("0")) {
            third = new JLabel();
        } else {
            third = new JLabel(winners[0].substring(winners[0].indexOf(" ")));
        }
        third.setForeground(Color.WHITE);
        third.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
        third.setBounds(850,490,150,20);
        
        activityLabel = new JLabel("Leaderboard for " + activityName);
        activityLabel.setForeground(Color.WHITE);
        activityLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 30));
        activityLabel.setBounds(530,200,400,30);

        buttonPanel.add(exit);
        back = new JButton(new ImageIcon("back2.png"));
        back.setBounds(20,20,40,40);

        add(back);
        add(trophy);
        add(buttonPanel);
        add(first);
        add(second);
        add(third);
        add(activityLabel);
        add(leaderLabel);
        add(triLabel);
        add(triLabel1);
        add(triLabel2);
        add(triLabel3);
        
    }

    public String[] winners() {

        TreeSet<String> usersViaDuration = new TreeSet<String>();
        //for each user
        for(String user: users) {
            //look at their activity durations for this activity
            FileInteractor fileInteractor2 = null;
            try {
                fileInteractor2 = new FileInteractor(user+".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Date date1 = new java.util.Date();
            String sdate1 = date1+"";
            String[] ssdate1 = sdate1.split(" ");
            String stringForDate1 = ssdate1[1]+" "+ssdate1[2]+" "+ssdate1[5];

            int count = 0;
            int duration = 0;
            String[] activitytemplate;
            String[] activity;
            while( fileInteractor2.getFileScanner().hasNextLine()) {
                if( count > 0) {
                    String str = fileInteractor2.getFileScanner().nextLine();
                    activitytemplate = str.split("~");
                    String[] datetemplate = activitytemplate[0].split(" ");
                    String filedate = datetemplate[1]+" "+datetemplate[2]+" "+datetemplate[5];
                    if( filedate.equals(stringForDate1)) {
                        for (int i = 1; i < activitytemplate.length; i++) {
                            activity = activitytemplate[i].split("-");
                            if (activity[0].equals(activityName)) {
                                duration += Integer.parseInt(activity[1]);
                            }
                        }
                    }
                }
                else {
                    fileInteractor2.getFileScanner().nextLine();
                }
                count++;
            }
            usersViaDuration.add(duration+" "+user);
            fileInteractor2.getFileScanner().close();

        }
        System.out.println(usersViaDuration + "<----------");
        boolean flag = true;
        if(flag) {
            String[] arr = new String[3];
            Iterator<String> iter = usersViaDuration.iterator();
            int index = 0;
            int count = 0;
            while (index < usersViaDuration.size()) {
                if(index > usersViaDuration.size() - 4) {
                    arr[count] = iter.next();
                    index++;
                    count++;
                }
                else {
                    index++;
                    iter.next();
                }
            }
            System.out.println(arr[0] + "----------->");
            return arr;
        }else {
            String[] arr = {null, null, null};
            return arr;
        }
    }

    public JButton getBack() {
        return back;
    }
}

