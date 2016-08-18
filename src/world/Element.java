package world;


import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Element {

	private BufferedImage img;
	private Point p;
	private int z_value = 0;
	private int rotation = 0;
	
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
	
	public int getZ_Value(){
		return z_value;
	}
	
	public void setZ_Value(int z){
		if(z < -5 || z > 5)
			throw new IllegalArgumentException();
		z_value = z;
	}
	
	public void increment_z(){
		z_value = Math.min(5, ++z_value);
	}
	
	public void decrement_z(){
		z_value = Math.max(-5, --z_value);
	}

	public void setImage(BufferedImage img) {
		this.img = img;
		
	}

	public void rotateImage(int degree) {		
		AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(degree), 
				p.x + img.getWidth()/2, 
				p.y + img.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
	}
}
