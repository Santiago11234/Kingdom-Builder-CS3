import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.*;

public class EndPanel extends JPanel implements ActionListener{
    private KingdomBuilder frame;

    public EndPanel(KingdomBuilder kb) {
        setSize(getPreferredSize());
        setLayout(null);
        frame = kb;
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
    }

    public void actionPerformed(ActionEvent e) {

    }
}
