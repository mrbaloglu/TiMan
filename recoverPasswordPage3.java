//ADD FUNCTIONALTY TO DISPLAY PASSWORD

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
import javax.swing.JTextField;

public class recoverPasswordPage3 extends JFrame{

    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo,tri,tri2;;
    JPanel recoverPanel;
    JTextField txusername ,txfavAnimal;
    JLabel username, favAnimal, showPassword , saveLabel, logoLabel,triLabel,triLabel1,triLabel2,triLabel3;
    JButton savePassword, login;
    FileInteractor fileInteractor;

    int x,y;

    /**
     * Constructor for recoverPasswordPage3 object.
     */
    public recoverPasswordPage3(){
        super("Recover Password Page");

        x = 590 ;
        y = 300 ;

        login = new JButton("Go to Login Page");
        login.setBounds(x+114,y+120,150,20);
        login.addActionListener(new LoginListener());

        saveLabel = new JLabel("Save Your Password");
        saveLabel.setForeground(Color.WHITE);
        saveLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 30));
        saveLabel.setBounds(570, 130, 331, 238);

        txusername = new JTextField();
        txfavAnimal = new JTextField();
        //tri = new ImageIcon(getClass().getResource("tri2.png"));
        //tri2 = new ImageIcon(getClass().getResource("tri1.png"));

        recoverPanel = new JPanel();
        recoverPanel.setBackground(new Color(38, 52, 71));

        //tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
        //logoLabel = new JLabel(tMlogo);

        username = new JLabel("Username:");
        username.setForeground(Color.WHITE);
        favAnimal = new JLabel("Favorite Animal:");
        favAnimal.setForeground(Color.WHITE);
        showPassword = new JLabel("Your password is:");
        showPassword.setForeground(Color.WHITE);

        savePassword = new JButton("Save my Password");
        savePassword.addActionListener(new SaveButtonListener());

        triLabel = new JLabel(tri);
        triLabel1 = new JLabel(tri2);
        triLabel2 = new JLabel(tri);
        triLabel3 = new JLabel(tri);

        triLabel.setBounds(-100,700,633,267);
        triLabel1.setBounds(300,700,633,267);
        triLabel2.setBounds(700,700,633,267);
        triLabel3.setBounds(1100,700,633,267);

        setSize(dim);
        setLocation(0,0);
        recoverPanel.setLayout (null);

        //logoLabel.setBounds(x-30, y-268, 331, 238);

        username.setBounds(x,y,150,20);
        favAnimal.setBounds(x,y+30,150,20);

        savePassword.setBounds(x+114,y+60,144,20);
        showPassword.setBounds(x,y+90,150,20);

        txusername.setBounds(x+110,y,150,20);
        txfavAnimal.setBounds(x+110,y+30,150,20);

        //recoverPanel.add(logoLabel);

        recoverPanel.add(username);
        recoverPanel.add(favAnimal);
        recoverPanel.add(txusername);
        recoverPanel.add(txfavAnimal);
        recoverPanel.add(showPassword);
        recoverPanel.add(savePassword);

        recoverPanel.add(triLabel);
        recoverPanel.add(triLabel1);
        recoverPanel.add(triLabel2);
        recoverPanel.add(triLabel3);
        recoverPanel.add(saveLabel);
        recoverPanel.add(login);

        getContentPane().add(recoverPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * ActionListener for save button.
     */
    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tUsername = txusername.getText();

            try {
                fileInteractor = new FileInteractor(tUsername + ".txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String[] firstLine = fileInteractor.getFileScanner().nextLine().split("-");
            if( txfavAnimal.getText().equals(firstLine[3])) {
                showPassword.setText("Your password is: "+ firstLine[2]);
            }
            else {
                showPassword.setText("Wrong input");
            }
            fileInteractor.getFileScanner().close();
        }
    }

    /**
     * ActionListener for login button.
     */

    class LoginListener implements ActionListener {
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