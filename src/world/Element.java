package world;


import java.awt.Point;
import java.awt.image.BufferedImage;

public class Element {

	private BufferedImage image;
	private Point p;
	
	private static final int default_x = 0, default_y = 0;
	
	public Element(BufferedImage i, Point p){
		image = i;
		this.p = p;
	}
	
	public Element (String image_location, Point p){
		//TODO grab image from locations string
	}
	
	public Element (String image_location){
		this(image_location, new Point(default_x, default_y));
	}
	
	public Element(BufferedImage i){
		this(i, new Point(default_x, default_y));
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public Point getLocation(){
		return p;
	}
	
	public void setPoint(int x, int y){
		p = new Point(x, y);
	}
	
	public void setPoint(Point p){
		this.p = p;
	}
}
