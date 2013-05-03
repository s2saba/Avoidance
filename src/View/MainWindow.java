package View;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow() {
		super("Avoidance");
		animationTimer = new Timer(1000 / FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				drawPanel.repaint();
			}
		});
		animationTimer.setRepeats(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					drawPanel = new JPanel() {
						private static final long serialVersionUID = 1L;

						@Override
						public void paintComponent(Graphics graphics) {
							super.paintComponents(graphics);
							render((Graphics2D)graphics);
						}
					};
					addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent event) {
							System.exit(EXIT_ON_CLOSE);
						}
					});
					addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent event) {
							bufferImage = drawPanel
									.createImage(drawPanel.getWidth(),
											drawPanel.getHeight());
							bufferGraphics = (Graphics2D) bufferImage.getGraphics();
						}
					});
					
					drawPanel.setPreferredSize(new Dimension(drawWidth, drawHeight));
					drawPanel.setLayout(null);
					setLocationByPlatform(true);
					
					setContentPane(drawPanel);
					pack();
					setVisible(true);
					bufferImage = drawPanel.createImage(drawWidth, drawHeight);
					if (bufferImage == null) {
						throw new Exception("Failed to create image buffer");
					}
					bufferGraphics = (Graphics2D) bufferImage.getGraphics();
					animationTimer.start();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(ERROR);
				}
			}
		});
		
	}
	
	private void render(Graphics2D graphics) {
		AffineTransform oldTransform = bufferGraphics.getTransform();
		
		bufferGraphics.scale(((double) drawPanel.getWidth()) / ((double) drawWidth), ((double) drawPanel.getHeight()) / ((double) drawHeight));
		
		bufferGraphics.setBackground(Color.BLACK);
		bufferGraphics.clearRect(0, 0, drawWidth, drawHeight);
		
		bufferGraphics.setTransform(oldTransform);
		
		graphics.drawImage(bufferImage, 0, 0, drawPanel);
	}
	
	private Timer animationTimer;
	private Image bufferImage;
	private Graphics2D bufferGraphics;
	
	private JPanel drawPanel;
	
	private int FPS = 30;
	
	private int drawWidth = 800;
	private int drawHeight = 600;
}
