package display;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import world.world;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Display {
	
	private world w;

	private JFrame frame;
	private GalleryPanel gallery;
	private ViewPanel view;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display(new world());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display(world w) {
		initialize();
		this.w = w;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1550, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		gallery = new JPanel();
		gallery.setBackground(Color.WHITE);
		splitPane.setLeftComponent(gallery);
		
		view = new MyPanel();
		view.setBackground(Color.WHITE);
		splitPane.setRightComponent(view);
	}
	
	public void redraw(){
		view.repaint();
	}

	 class ViewPanel extends JPanel {

	        ViewPanel() {
	        }

	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            
	            //TODO: Draw the view
	        }
	    }
	 
	 class GalleryPanel extends JPanel {
		 
		 GalleryPanel() {
			 
		 }
		 
		 @Override
		 public void paintComponent(Graphics g) {
			 super.paintComponent(g);
			 
			 //TODO: Draw the view
		 }
	 }
}
