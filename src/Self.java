/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//Imports Java Packages
import java.awt.Graphics;
import java.awt.Rectangle;

//Interface self with update, draw, getx, gety and getbounds methods which helps in 
//distinguishing between friendly and enemy or other
public interface Self {
	
	public void update();
	
	public void draw(Graphics g);
	
	public int getX();
	
	public int getY();
	
	public Rectangle getBounds();

}
