/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/*FallingRocks class that extends Sprite and implements Other
 *  with constructor and  draw, update, remove and getBounds methods
 *  very similar to all other Sprites
 */
public class FallingRocks extends Sprite implements Other {
	
	boolean visible;

	public FallingRocks(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
		this.y = 0;
	}

	
	Image image = loadImage(this.getFilename());
	
	
	@Override
	public void draw(Graphics g) {
	
		if (visible) {
			g.drawImage(image, (int)x, (int)y, sizex, sizey, null);
		}
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, sizex, sizey);
	}


	@Override
	public void update() {
		y += 25;
		
		if (y > GameFrame.CHeight) {
			y = 0;
			visible = true;
		}
	
	}

	public void remove() {
		visible = false;
	}
	

}
