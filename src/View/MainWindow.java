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

						public void paintComponent(Graphics graphics) {
							super.paintComponents(graphics);
							render(graphics);
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
							bufferGraphics = bufferImage.getGraphics();
						}
					});
					
					drawPanel.setPreferredSize(new Dimension((int) drawWidth,
							(int) drawHeight));
					drawPanel.setLayout(null);
					setLocationByPlatform(true);
					
					setContentPane(drawPanel);
					pack();
					setVisible(true);
					bufferImage = drawPanel.createImage((int) drawWidth,
							(int) drawHeight);
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
	
	private void render(Graphics graphics) {
		
	}
	
	private Timer animationTimer;
	private Image bufferImage;
	private Graphics bufferGraphics;
	
	private JPanel drawPanel;
	
	private int FPS = 30;
	
	private double drawWidth = 800;
	private double drawHeight = 600;
}
