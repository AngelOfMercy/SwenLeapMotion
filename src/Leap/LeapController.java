package Leap;

import java.awt.Point;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

import display.Display;
import world.World;

public class LeapController extends Listener{
	
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
    	for (int i = 0; i < gsl.count(); i++) {
            Gesture gesture = gsl.get(i);

            switch (gesture.type()) {
            case TYPE_SCREEN_TAP:
            	ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
            	Vector loc = screenTap.position();
            	//world.selectElementAt();
            	System.out.println("tap");
            	gesturePerf = true;
            	break;
            	//TODO:translate
            	//TODO: move
            	//TODO:scale
            	//TODO:x-axis translate
            	//TODO:rotate
            }
    	}
    	
    	if(!gesturePerf){
    		if(!frame.hands().isEmpty()){
    			HandList hl = frame.hands();
    			for(int i = 0; i < hl.count(); i++){
    				Hand hand = hl.get(i);
    				int id = hand.id();
    				for(int f = 1; i < 5; i++){
    					if(controller.frame(f) != null){
    						Frame prev = controller.frame(f);
    						HandList phl = prev.hands();
    						for(int h = 0; h < phl.count(); h++){
    							if(phl.get(h).equals(hand)){
    								Hand ph = phl.get(h);
    								if(hand.pinchStrength() > ph.pinchStrength() + 0.2){
    									world.resizeImage(0.5);
    									System.out.println("Shrink");
    								}
    								else if(hand.pinchStrength() < ph.pinchStrength() - 0.2){
    									world.resizeImage(1.5);
    									System.out.println("enlarge");
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	display.redraw();

    	System.out.println("frame" + count++);
    }
    public static void main(String args[]){
    	World world = new World();
    	Display display = new Display(world);
    	LeapController lc = new LeapController(world, display);
    	
    	display.addListener(lc);
    }
}
