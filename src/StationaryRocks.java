/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//Imports required Java packages
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


/*StationaryRocks class that represents the StationaryRocks and extends Sprite and implements Other
 * has a constructor and draw,update, remove, and getBounds methods
 * very similar to all other game elements 
 */
public class StationaryRocks extends Sprite implements Other{
	
	public StationaryRocks(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
	}

	
	Image image = loadImage(this.getFilename());
	
	
	@Override
	public void draw(Graphics g) {
	
		g.drawImage(image, (int)x, (int)y, sizex, sizey, null);
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, sizex, sizey);
	}


	@Override
	public void update() {
	}


	public void remove() {

	}

}
