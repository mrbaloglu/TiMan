import javax.imageio.IIOException;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class competitionFrame extends JFrame {
    public JTabbedPane pane;
    //public ArrayList<Activity> activities;
    String username;

    /**
     * Constructor for competitionFrame object.
     *
     * @param username Username of the user.
     * @throws IOException
     * @throws Exception
     */
    public competitionFrame(String username) throws IOException, Exception {
        this.username = username;

        pane = new JTabbedPane();

        competitionPanel socialComp = new competitionPanel("Social");
        socialComp.exit.addActionListener(new ExitButtonListener());
        socialComp.getBack().addActionListener(new BackButtonListener());
        competitionPanel sportComp = new competitionPanel("Sport");
        sportComp.exit.addActionListener(new ExitButtonListener());
        sportComp.getBack().addActionListener(new BackButtonListener());
        competitionPanel studyComp = new competitionPanel("Work");
        studyComp.exit.addActionListener(new ExitButtonListener());
        studyComp.getBack().addActionListener(new BackButtonListener());
        //competitionPanel sleepComp = new competitionPanel("Social");

        pane.addTab("Social",socialComp);
        pane.addTab("Sport",sportComp);
        pane.addTab("Work",studyComp);


        add(pane);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1920,1080);
        setVisible(true);
    }

    /**
     * ActionListener for exit button.
     */
    class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new exitPage(username);
        }
    }

    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                new MainManuFrame(username);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

