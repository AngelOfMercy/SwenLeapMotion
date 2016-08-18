package Leap;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

import display.Display;
import world.World;

public class LeapController extends Listener{
	

	private final int x_scalar = 10, y_scalar = 10;
	private final double pinchDifference = 0.2;
	private final double grabThreshold = 0.9;
	
	private Display display;
	private World world;
	private int count;
	
	public LeapController(World world, Display display){
		super();
		this.world = world;
		this.display = display;
		this.count = 0;
	}
	
	public void onInit(Controller controller){
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller){
    	Frame frame = controller.frame();
    	boolean gesturePerf = false;
    	GestureList gsl = frame.gestures();
    	
    	for (int i = 0; i < gsl.count(); i++) { //check to see if it is an inbuilt gesture
            Gesture gesture = gsl.get(i);

            switch (gesture.type()) {
            case TYPE_SCREEN_TAP:
            	System.out.println("tap");
            	ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
            	Vector loc = screenTap.position();
            	Point curs = world.getCursor();
            	System.out.println(curs);
            	if(curs.getX() < 0){
            		BufferedImage image = display.getImage();
            		if(image == null){
            			System.out.println("no image");
            			break;
            		}
            		world.addImageToWorld(100, 100, image);
            		System.out.println("image added");
            	}
            	else {
            		world.selectElementAt((int)curs.getX(), (int)curs.getY());
            		System.out.println("selecting");
            	}
            	gesturePerf = true;
            	break;
            	
            case TYPE_SWIPE:
            	SwipeGesture swipe = new SwipeGesture(gesture);
            	HandList hl = swipe.hands();
            	if(hl.get(0) != null){
            		Hand h = hl.get(0);
            		if(h.isLeft()){
            			if(swipe.direction().getX() < 0){
            				world.moveBackward();
            				System.out.println("move back");
            			}
            			else if(swipe.direction().getX() > 0){
            				world.moveForward();
            				System.out.println("move forward");
            			}
            		}
            		else if(h.isRight()){

            			if(swipe.direction().getX() < 0){
            				world.moveForward();
            				System.out.println("move forward");
            			}
            			else if(swipe.direction().getX() > 0){
            				world.moveBackward();
            				System.out.println("move back");
            			}
            		}
            	}
            	gesturePerf = true;
            	break;
            	
            case TYPE_CIRCLE:
            	CircleGesture circle = new CircleGesture(gesture);
            	if(circle.normal().getZ() > 0){
            		//rotate counter clockwise
            		world.rotateImage(-10);
            		System.out.println("rotating");
            	}
            	else {
            		//rotate clockwise
            		world.rotateImage(10);
            		System.out.println("rotating");
            	}
            	gesturePerf = true;
            	break;
            	
            	//TODO: move
            	//TODO:rotate
            }
    	}
    	
    	
    	if(!gesturePerf){ //if no gesture is performed check the following
    		if(!frame.hands().isEmpty()){
    			HandList hl = frame.hands();
    			for(int i = 0; i < hl.count(); i++){
    				Hand hand = hl.get(i);
    				//System.out.println(hand.grabStrength());
    				if(hand.grabStrength() >= grabThreshold){//if it is a grab, do something
    					//System.out.println("True");
    					Vector tl = hand.translation(controller.frame(1));
    					int x_trans = (int) (tl.getX() * x_scalar);
    					int y_trans = (int) (tl.getY() * y_scalar);
    					Point p = world.getSelectedElement().getLocation();
    					p.x = p.x + x_trans;
    					p.y = p.y + y_trans;
    					world.getSelectedElement().setPoint(p);
    					System.out.println("moved shit");

    			    	display.redraw();
    					return;
    				}
    				

					FingerList fingerList = hand.fingers();//check what fingers are extended
					boolean onlyIndex = true;
					boolean middle = false, ring = false, pinky = false;
					int ind = 0;
					for(int f = 0; f < fingerList.count(); f++){
						if(fingerList.get(f).type().equals(Finger.Type.TYPE_INDEX)){
							ind = f;
							if(!fingerList.get(f).isExtended()){
								onlyIndex = false;
							}
						}
						else {
							if(fingerList.get(f).isExtended()){
								onlyIndex = false;
							}
						}
						if(fingerList.get(f).type().equals(Finger.Type.TYPE_MIDDLE)){
							middle = fingerList.get(f).isExtended();
						}

						if(fingerList.get(f).type().equals(Finger.Type.TYPE_RING)){
							ring = fingerList.get(f).isExtended();
						}
						

						if(fingerList.get(f).type().equals(Finger.Type.TYPE_PINKY)){
							pinky = fingerList.get(f).isExtended();
						}
					}
    					if(controller.frame(5) != null && middle && ring && pinky){//check for a pinch with middle/ring/pinky extended
    						Frame prev = controller.frame(5);
    						HandList phl = prev.hands();
    						for(int h = 0; h < phl.count(); h++){
    							if(phl.get(h).id() ==(hand.id())){
    								Hand ph = phl.get(h);
    								if(hand.pinchStrength() > ph.pinchStrength() + pinchDifference){
    									//world.resizeImage(0.5);
    									System.out.println("Shrink");
    									display.redraw();
    			    					return;
    								}
    								else if(hand.pinchStrength() < ph.pinchStrength() - pinchDifference){
    									//world.resizeImage(1.5);
    									System.out.println("enlarge");
    									display.redraw();
    			    					return;
    								}
    							}
    						}
    					}

    					if(onlyIndex){//move cursor if only the index finger is selected
    						Vector tl = hand.translation(controller.frame(1));
        					int x_trans = (int) (tl.getX() );//* x_scalar);
        					int y_trans = (int) (tl.getY() );//* y_scalar);
        					Point p = world.getCursor();
        					p.x = p.x + x_trans;
        					p.y = p.y + y_trans;
        					world.setCursor(p);
        					display.redraw();
        					return;
    					}
    			}
    		}
    	}
    	display.redraw();

    	//System.out.println("frame" + count++);
    }
    public static void main(String args[]){
    	World world = new World();
    	Display display = new Display(world);
    	LeapController lc = new LeapController(world, display);
    	
    	display.addListener(lc);
    }
}
