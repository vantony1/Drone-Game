/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */


//imports required Java packages
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/*AgressiveEnemy class that represent the enemy drone that extends Sprite and implements Other 
 * has a constructor and a draw, getBounds and update methods
 * works and is very similar to other game elements
 */
public class AgressiveEnemy extends Sprite implements Other {
	
	boolean draw = true;

	public AgressiveEnemy(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
	
	}
	
	Image image = loadImage(this.getFilename());
	
	
	@Override
	public void draw(Graphics g) {
	
		if (draw) {
		g.drawImage(image, (int)x, (int)y, sizex, sizey, null);
		}
		
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, sizex, sizey);
	}

	@Override
	public void update() {
		
	
		
			x -= 25;
			
			if (x < 0) {
				x = 620;
				draw = true;
			}
	
		
	}

	
	public void remove() {
		this.draw = false;
	}
	
	

}
