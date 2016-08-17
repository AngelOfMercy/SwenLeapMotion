import java.io.IOException;

import Leap.LeapController;
import display.Display;
import world.World;

public class main {
	
	public static void main(String args[]){
		World world = new World();
		Display display = new Display(world);
		LeapController lc = new LeapController(world, display);
	
		display.addListener(lc);
	
	try{
		System.in.read();
	}
	catch(IOException e){
		e.printStackTrace();
	}

		display.removeListener(lc);
	}
}
