package core;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimpleOpenScreenPlayer {
	
	public static void main(String[] args) throws IOException {
		// loading the content
		
		// sosp.png is the name for the default image
		BufferedImage image = ImageIO.read(SimpleOpenScreenPlayer.class.getResourceAsStream("sosp.png"));
		
		// creating the window
		JFrame frame = new JFrame();
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setSize(screenDimension);
		frame.setTitle("Simple Open Screen Player");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setAutoRequestFocus(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		// visibility has to be last adjustment
		frame.setVisible(true);
		
		
		// keyboard interaction
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// fail safe key
				if (e.getKeyCode() == KeyEvent.VK_A)
					System.exit(0);
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// fail safe key
				if (e.getKeyCode() == KeyEvent.VK_A)
					System.exit(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// fail safe key
				if (e.getKeyCode() == KeyEvent.VK_A)
					System.exit(0);
				
			}
		});
		
		// adding content
		JPanel panel = new JPanel();
		frame.add(panel);
		
		ImageIcon icon = new ImageIcon(image);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(icon);
		
		panel.add(imageLabel);
		panel.repaint();
		frame.repaint();
		
	}
	
}
