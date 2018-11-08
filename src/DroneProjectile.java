/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//imports required java packages
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//class that represents the bullets extends sprite and implements Self
public class DroneProjectile extends Sprite implements Self {
	
	//variables needed
	int n;
	
	//Constructor
	public DroneProjectile(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
	}
	
	//image of bullet
	Image image = loadImage(this.getFilename());
	
	//method that renders the bullet
	@Override
	public void draw(Graphics g) {
	
		g.drawImage(image, (int)x, (int)y, 20, 20, null);
		
	}
	
	//method that updates the bullet
	@Override
	public void update() {
		x += 40;

	}

}
