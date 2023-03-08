import java.awt.*;
import javax.swing.*;

public class KingdomBuilder extends JFrame {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 960;
    
    public KingdomBuilder(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);       
        add(new Panel());
        setVisible(true);
        
    }
    
    
}
