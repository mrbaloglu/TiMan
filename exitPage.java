import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class exitPage extends JFrame{

    static Dimension dim = new Dimension(1280,720);

    String username;
    JLabel goodBye;
    ImageIcon tMlogo,tri,tri2, logInIcon;
    JLabel logoLabel,triLabel,triLabel1,triLabel2,triLabel3;
    JPanel exitPanel ,buttonPanel;
    JButton logIn;

    /**
     * Constructor for exitPage object.
     *
     * @param username Username of the user.
     */
    public exitPage(String username) {
        super("Exit Page");
        this.username = username;

        exitPanel = new JPanel();
        exitPanel.setBackground(new Color(38, 52, 71));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(1255, 700, 55, 55);
        buttonPanel.setBackground(new Color(38, 52, 71));

        logInIcon = new ImageIcon(getClass().getResource("key.png"));
        logIn = new JButton(logInIcon);
        logIn.setBounds(0, 0, 55, 55);
        logIn.addActionListener(new LoginButtonListener());

        buttonPanel.add(logIn);

        setSize(dim);
        setLocation(0,0);
        exitPanel.setLayout (null);

        tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
        logoLabel = new JLabel(tMlogo);

        logoLabel.setBounds(560, 150, 331, 238);

        goodBye = new JLabel("Goodbye, "+username+". see you soon!");
        goodBye.setForeground(Color.WHITE);
        goodBye.setFont(new Font("SANS_SERIF", Font.BOLD, 30));
        goodBye.setBounds(500, 300, 600, 238);

        tri = new ImageIcon(getClass().getResource("tri2.png"));
        tri2 = new ImageIcon(getClass().getResource("tri1.png"));

        triLabel = new JLabel(tri);
        triLabel1 = new JLabel(tri2);
        triLabel2 = new JLabel(tri);
        triLabel3 = new JLabel(tri);

        triLabel.setBounds(-100,700,633,267);
        triLabel1.setBounds(300,700,633,267);
        triLabel2.setBounds(700,700,633,267);
        triLabel3.setBounds(1100,700,633,267);

        exitPanel.add(goodBye);
        exitPanel.add(triLabel3);
        exitPanel.add(triLabel2);
        exitPanel.add(triLabel1);
        exitPanel.add(triLabel);
        exitPanel.add(logoLabel);
        exitPanel.add(buttonPanel);

        getContentPane().add(exitPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * ActionListener for login button.
     */

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                new loginPage3();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}