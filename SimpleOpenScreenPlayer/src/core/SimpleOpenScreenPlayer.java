package core;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimpleOpenScreenPlayer {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// default values
		boolean nofailsafe = false;
		boolean noexit = false;
		boolean captureMouse = false;
		long noexit_interval = 50;

		HashMap<String, String> arguments = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				String key = args[i].substring(1);
				String value = (i + 1 < args.length && !args[i + 1].startsWith("--")) ? args[++i] : "true";
				arguments.put(key, value);
			}
		}
		
		// this is an unreadable mess
		nofailsafe = Boolean.parseBoolean(arguments.getOrDefault("nofailsafe", Boolean.toString(nofailsafe)));
		noexit = Boolean.parseBoolean(arguments.getOrDefault("noexit", Boolean.toString(noexit)));
		captureMouse = Boolean.parseBoolean(arguments.getOrDefault("captureMouse", Boolean.toString(captureMouse)));
		noexit_interval = Long.decode(arguments.getOrDefault("noexit_interval", Long.toString(noexit_interval)));

		// loading the content
		// sosp.png is the name for the default image -> [s]imple[o]pen[s]creen[p]layer
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

		// hiding the cursor
		frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));

		if (!nofailsafe) {
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
		}

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

		if (noexit && !captureMouse) {
			try {
				Robot robot = new Robot();

				while (true) {
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_WINDOWS);

					Thread.sleep(noexit_interval);

					robot.keyPress(KeyEvent.VK_ALT);
					robot.keyPress(KeyEvent.VK_WINDOWS);

					Thread.sleep(noexit_interval);
				}
			} catch (AWTException e1) {
				System.out.println("noexit could not be initiated!");
				e1.printStackTrace();
			}
		} else if (noexit && captureMouse) {
			try {
				Robot robot = new Robot();

				while (true) {
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_WINDOWS);

					robot.mouseMove(screenDimension.width / 2, screenDimension.height / 2);
					robot.mousePress(MouseEvent.BUTTON1);

					Thread.sleep(noexit_interval);

					robot.keyPress(KeyEvent.VK_ALT);
					robot.keyPress(KeyEvent.VK_WINDOWS);

					robot.mouseMove(screenDimension.width / 2, screenDimension.height / 2);
					robot.mouseRelease(MouseEvent.BUTTON1);

					Thread.sleep(noexit_interval);
				}
			} catch (AWTException e1) {
				System.out.println("noexit could not be initiated!");
				e1.printStackTrace();
			}
		} else if (captureMouse && !noexit) {
			System.out.println("incompatible arguments! Cannot capture mouse without \"noexit\" enabled");
		}
	}

}
