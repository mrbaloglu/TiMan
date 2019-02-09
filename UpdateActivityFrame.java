import javax.swing.*;

public class UpdateActivityFrame extends JFrame {
    Activity a;
    UpdateActivityPanel panel;
    String username;

    /**
     * Constructor for UpdateActivityFrame object.
     * @param a activity
     * @param username username of the user.
     */

    public UpdateActivityFrame(Activity a, String username) {
        super();
        this.username = username;
        panel = new UpdateActivityPanel(a, username);

        add(panel);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1920,1080);
        setVisible(true);


    }

    public UpdateActivityPanel getPanel() {
        return panel;
    }

}
