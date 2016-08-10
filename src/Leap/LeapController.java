package Leap;

import java.awt.Point;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

import display.Display;
import world.World;

public class LeapController extends Listener{
	
	private Display display;
	private World world;
	
	public LeapController(World world, Display display){
		super();
		this.world = world;
		this.display = display;
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
    	
    	GestureList gsl = frame.gestures();
    	for (int i = 0; i < gsl.count(); i++) {
            Gesture gesture = gsl.get(i);

            switch (gesture.type()) {
            case TYPE_SCREEN_TAP:
            	ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
            	Vector loc = screenTap.position();
            	world.selectElementAt();
            	break;
            	//TODO:translate
            	//TODO: move
            	//TODO:scale
            	//TODO:x-axis translate
            	//TODO:rotate
            }
    	}
    	display.redraw();
    }

}
