package world;

import java.awt.Image;
import java.util.ArrayList;

public class world {
	
	
	private ArrayList<Image> image_gallery;
	private ArrayList<Element> world_obj;
	private int selectedIndex;
	
	public world(){
		image_gallery = new ArrayList<Image>();
		world_obj = new ArrayList<Element>();
	}
	
	public ArrayList<Image> getGallery(){
		return image_gallery;
	}
	
	public ArrayList<Element> getWorld() {
		return world_obj;
	}
	
	/**
	 * Moves the selected object one 'set' closer to the user.
	 * @return If the operation was successful
	 */
	public boolean moveForward(){
		if(selectedIndex == 0)
			return false;
		Element e = world_obj.remove(selectedIndex);
		world_obj.add(--selectedIndex, e);
		return true;	
	}
	
	public boolean selectElementAt(int x, int y){
		//TODO
		for(Element e : world_obj){
			int width = e.getImage().getWidth();
			int height = e.getImage().getHeight();
		}
		
		return false;
	}
	
	/**
	 * Moves the selected object one 'set' away from the user.
	 * @return If the operation was successful.
	 */
	public boolean moveBackward(){
		if(selectedIndex >= world_obj.size())
			return false;
		Element e = world_obj.remove(selectedIndex);
		world_obj.add(++selectedIndex, e);
		return true;
	}
	
	public Element getSelectedElement(){
		return world_obj.get(selectedIndex);
	}
	
	
	public void resizeImage(double scale){
		//TODO
		world_obj.get(selectedIndex).getImage();
	}
}
