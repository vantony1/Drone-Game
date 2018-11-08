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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Abstract classes for game sprites
public abstract class Sprite {
	
	//needed variables and objects
	int x, y;
	int sizex, sizey; 
	String filename;

	
	//constructor 
	public Sprite(int x, int y, String filename, int sizex, int sizey) {
		 this.x = x;
		 this.y = y;
		 this.sizex = sizex;
		 this.sizey = sizey;
		 this.filename = filename; 
	}

	//setters and getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getFilename() {
		return filename;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	//image loader method
	public Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image image = null;
	
	//method that draws
	public void draw(Graphics g) {
	
		g.drawImage(image, (int)x, (int)y, sizex, sizey, null);
		
	}
	
	public void update() {
		
	}

	//method that helps getting detector working
	public Rectangle getBounds(){
		return new Rectangle(x, y, sizex, sizey);
	}
	
	
	
	
	
	
	

}
