package Leap;

import java.awt.Point;

import world.World;

import com.leapmotion.leap.*;

public class grabGesture extends Gesture{
	
	private static Hand hand = null;
	
	private static final int x_scalar = 10, y_scalar = 10;

	public static final boolean gestureDetected(Controller c){
		Frame f = c.frame();
		HandList hl = f.hands();
		
		for(Hand h : hl){
			if (h.grabStrength() > 0.9 && h.confidence() > 0.8){
				hand = h;
				return true;
			}
		}
		hand = null;
		return false;
	}
	
	public static final void manipulateWorld(Frame f, World w){
		for(Hand h : f.hands()){
			if(h.equals(hand)){
				Vector tl = h.translation(f);
				int x_trans = (int) (tl.getX() * x_scalar);
				int y_trans = (int) (tl.getY() * y_scalar);
				Point p = w.getSelectedElement().getLocation();
				p.x = p.x + x_trans;
				p.y = p.y + y_trans;
				w.getSelectedElement().setPoint(p);
				
			}
		}
	}
}
