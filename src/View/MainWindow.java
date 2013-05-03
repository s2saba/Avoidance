package View;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import Root.IObserver;
import Controller.*;

public class MainWindow extends JFrame implements IObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow() {
		super("Avoidance");
		animationTimer = new Timer(1000 / FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.TimerTick(e);
				drawPanel.repaint();
			}
		});
		animationTimer.setRepeats(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					controller = new GameController();					
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
					addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							controller.MouseClicked(e);
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							controller.MouseDown(e);
						}
						
						@Override
						public void mouseReleased(MouseEvent e) {
							controller.MouseUp(e);
						}
					});
					addMouseMotionListener(new MouseAdapter() {
						@Override
						public void mouseDragged(MouseEvent e) {
							controller.MouseDragged(e);
						}
						
						@Override
						public void mouseMoved(MouseEvent e) {
							controller.MouseMoved(e);
						}
					});
					addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							controller.KeyDown(e);
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							controller.KeyUp(e);
						}
						
						@Override
						public void keyTyped(KeyEvent e) {
							controller.KeyTyped(e);
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
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
	
	private Timer animationTimer;
	private Image bufferImage;
	private Graphics2D bufferGraphics;
	
	private IModeController controller;
	
	private JPanel drawPanel;
	
	private int FPS = 30;
	
	private int drawWidth = 800;
	private int drawHeight = 600;
}
