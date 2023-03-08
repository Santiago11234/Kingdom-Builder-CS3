import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics2D;

public class KingdomBuilderPanel extends JPanel implements MouseListener {
	 
	private BufferedImage back;


	public KingdomBuilderPanel() {
		
		try {		
			back= ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/back.png"));
		}
        catch(Exception E){
            System.out.println("Exception Error");
            return;
        }
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(back, 0, 0,getWidth(), getHeight(), null);
		System.out.println("hello");
	}
    




	@Override
	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

}