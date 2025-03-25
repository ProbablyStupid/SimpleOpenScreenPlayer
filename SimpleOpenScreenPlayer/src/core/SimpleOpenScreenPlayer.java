package core;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
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

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// defining the behavior
		
		boolean failsafe = true;
		boolean noexit = false;

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
		// visibility should be the last adjustment
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
		frame.setContentPane(panel);
		frame.setLayout(null);

		ImageIcon icon = new ImageIcon(image);

		JLabel imageLabel = new JLabel(icon);
		imageLabel.setIcon(icon);
		imageLabel.setSize(screenDimension);
//		imageLabel.setBounds(screenDimension.width/2, screenDimension.height/2, screenDimension.width, screenDimension.height);

		panel.add(imageLabel);
		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
		
		
		if (noexit) {
			try {
				Robot robot = new Robot();

				while (true) {
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_WINDOWS);
					
					Thread.sleep(50);
					
					robot.keyPress(KeyEvent.VK_ALT);
					robot.keyPress(KeyEvent.VK_WINDOWS);
					
					// TODO: make the value adjustable
					Thread.sleep(50);
				}
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}

}
