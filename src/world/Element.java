package world;


import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Element {

	private BufferedImage img;
	private Point p;
	
	private static final int default_x = 0, default_y = 0;
	
	public Element(BufferedImage i, Point p){
		img = i;
		this.p = p;
	}
	
	public Element (String image_location, Point p){
		try {
			img = ImageIO.read(new File(image_location));
			this.p = p;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Element (String image_location){
		this(image_location, new Point(default_x, default_y));
	}
	
	public Element(BufferedImage i){
		this(i, new Point(default_x, default_y));
	}
	/**
	 * Returns the assosiated image object
	 * @return
	 */
	public BufferedImage getImage(){
		return img;
	}
	
	/**
	 * Returns the co-ordinate of the image at it's top left.
	 * @return
	 */
	public Point getLocation(){
		return p;
	}
	
	/**
	 * Set the top left co-ordinate for the image.
	 * @param x
	 * @param y
	 */
	public void setPoint(int x, int y){
		p = new Point(x, y);
	}
	
	/**
	 * Set the top left co-ordinate for the image.
	 * @param x
	 * @param y
	 */
	public void setPoint(Point p){
		this.p = p;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
		
	}
}
