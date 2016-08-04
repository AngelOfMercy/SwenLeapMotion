package display;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import world.Element;
import world.World;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Display {
	
	private World w;

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
					Display window = new Display(new World());
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
	public Display(World w) {
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
		
		gallery = new GalleryPanel();
		gallery.setBackground(Color.WHITE);
		splitPane.setLeftComponent(gallery);
		
		view = new ViewPanel();
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
	            ArrayList<BufferedImage> gallery = w.getGallery();
	            int x = 0; int y = 0;
	            for(BufferedImage bf: gallery){
	            	int width = bf.getWidth();
	            	int height = bf.getHeight();
	            	int scale = 10;
	            	if(width > height){
	            		scale = width/10;
	            		width = 10;
	            		height = height/scale;
	            	}
	            	else{
	            		scale = height/10;
	            		height = 10;
	            		width = width/scale;
	            	}
	            	g.drawImage(bf, x, y, width, height, this);
	            	x += 10;
	            	y += 10;
	            }
	            //TODO: Draw the view
	        }
	    }
	 
	 class GalleryPanel extends JPanel {
		 
		 GalleryPanel() {
			 
		 }
		 
		 @Override
		 public void paintComponent(Graphics g) {
			 super.paintComponent(g);
			 ArrayList<Element> world = w.getWorld();
			 for(Element el: world){
				 Point p = el.getLocation();
				 g.drawImage(el.getImage(), (int)p.getX(), (int)p.getY(), this);
			 }
			 //TODO: Draw the view
		 }
	 }
}
