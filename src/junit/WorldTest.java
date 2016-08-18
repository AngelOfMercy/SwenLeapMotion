package junit;

import static org.junit.Assert.*;

import org.junit.*;

import world.Element;
import world.World;

public class WorldTest {

	
	private World setUp(){
		World w = new World();
		w.loadImages("assets");
		return w;
	}
	
	private World populateWorld(World w, int n){
		for(int i = 0; i < n; i++)
			w.addImageToWorld(i*500, i*500, w.getGallery().get(0));
		return w;
	}
	
	@Test
	public void testLoadImages_1(){
		World w = setUp();
		assertTrue(w.getGallery().size() == 1);
	}
	
	@Test
	public void testAddElement_1(){
		World w = setUp();
		w.addImageToWorld(0, 0, w.getGallery().get(0));
		assertTrue(w.getWorld().size() == 1);
		
	}
	
	@Test
	public void testSelectElement(){
		World w = populateWorld(setUp(), 5);
		w.selectElementAt(2000, 2000);
		assertTrue(w.getSelectedElement().equals(w.getWorld().get(4)));
	}
	
	@Test
	public void testMoveForward_1(){
		World w = populateWorld(setUp(), 5);
		System.out.println(w.getWorld().get(1).getLocation());
		w.selectElementAt(2000, 2000);
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		System.out.println("---");

		w.moveForward();
		
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		assertTrue(w.getWorld().get(4).getZ_Value() == 1);
	}
	
	@Test
	public void testMoveForward_2(){
		World w = populateWorld(setUp(), 5);
		System.out.println(w.getWorld().get(0).getLocation());
		w.selectElementAt(2000, 2000);
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		System.out.println("---");

		w.moveForward();
		
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		assertTrue(w.getWorld().get(4).getZ_Value() == 1);
	}
	
	@Test
	public void testMoveForward_3(){
		World w = populateWorld(setUp(), 5);
		System.out.println(w.getWorld().get(4).getLocation());
		w.selectElementAt(2000, 2000);
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		System.out.println("---");

		w.moveForward();
		
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		assertTrue(w.getWorld().get(4).getZ_Value() == 1);
	}
	
	@Test
	public void testMovebackward_1(){
		World w = populateWorld(setUp(), 5);
		System.out.println(w.getWorld().get(4).getLocation());
		w.selectElementAt(2000, 2000);
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		System.out.println("---");

		w.moveBackward();
		
		
		for(Element e : w.getWorld()){
			System.out.println(e.getZ_Value());
		}
		
		assertTrue(w.getWorld().get(0).getZ_Value() == -1);
	}
}
