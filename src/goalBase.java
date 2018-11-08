/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */


//Imports java packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//goalBase class that extends sprite and represents the landing zone
public class goalBase extends Sprite {

	//constructor
	public goalBase(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
		this.filename = null;
	}
	
	//method that draws goal base
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, sizex, sizey);
	}
	
	//method that is used to detect collision
	public Rectangle getBounds() {
		return new Rectangle(x, y, sizex, sizey);
	}

}
