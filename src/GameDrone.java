/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//imports required Java packages 
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


//GameDrone class the extends Sprite and implements Self
//that has code that renders, updates and moves Drone
public class GameDrone extends Sprite implements Self {
	
	//object and variables needed
	boolean first = true;
	int n;
	GameFrame GameFrame;
	public int xCenter;
	public int yCenter;
	public int G = 10;
	int angle = 90;
	int speed;
	public int width = 64;
	public int height = 118;
	public int hX = 0;
	public int hY = 0;
	

	
	//constructor with respect to the parent class
	public GameDrone(int x, int y, String filename, int sizex, int sizey) {
		super(x, y, filename, sizex, sizey);
		this.filename = "graphics/Drone-sideways.png";
	}
	
	//constructor with the GameFrame in parameter
	public GameDrone(int x, int y, String filename, int sizex, int sizey, GameFrame current) {
		super(x, y, filename, sizex, sizey);
		this.GameFrame = current;
		this.filename = "graphics/Drone-sideways.png";
	}
	
	//Getters and setters of Drone position, speed and size
	public void setLocation(int x, int y) {
		xCenter = x;
		yCenter = y;
		
	}
	
	public void setX(int h) {
		xCenter += h;
	}
	
	public void setY(int h) {
		yCenter += h;
	}
	
	public int getX() {
		return xCenter;
	}
	
	public int getY() {
		return yCenter;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

	public void setAngle(int h) {
		angle = h;
	}
	
	public void addAngle(int h) {
		angle += h;
	}
	
	public void setSpeed(int h) { 	
		speed = h;
	}
	
	public void addSpeed(int h) { 	
		speed += h;
	}
	
	//method that allows for loading image
	public Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	//images that are needed for Drone graphics
	Image droneMain = loadImage("graphics/Drone-sideways.png");
	Image droneMainRocket = loadImage("graphics/Drone-rocket.png");
	Image droneSideRocket = loadImage("graphics/Drone-sideRocket.png");
	
	
	//Method that updates drone location based on angle and speed and restricts rendering locations
	public void update() {
		
		
		if (angle > 180) {
			angle = 180;
		} else if (angle < 0) {
			angle = 0;
		}
		
		hX = (int)(speed*(Math.cos(Math.toRadians(angle))));
		xCenter += hX;
		
		if (xCenter > 580) {
			xCenter = 580;
		} else if (xCenter < 0) {
			xCenter = 0;
		}
		
		hY = (int)(speed*(Math.sin(Math.toRadians(angle))));
		yCenter += hY;
		
		yCenter += G;
	
		
		if (yCenter > 420) {
			yCenter = 420;
		} else if (yCenter < 0) {
			yCenter = 0;
		} 
		
		if (xCenter > 50 && yCenter > 340) {
			
			yCenter = 340;
			
			if (speed > 30) {
				GameFrame.useLife();
			}
			
		}
		
		if (xCenter > 50 && yCenter < 80) {
		    yCenter = 80;
		}
		
 		
	}

	//drawDrone method that draws the drone at set location
	public void drawDrone(Graphics g) {
		int x = xCenter;
		int y = yCenter;
		g.drawImage(droneMain, x, y, 60, 60, null);
		
	}
	
	//method that draw the rockets
	public void drawMainRocket(Graphics g) {
		int x = xCenter + 17;
		int y = yCenter + 40;
		
		
		g.drawImage(droneMainRocket, x, y, 30, 30, null);
	}
	
	public void drawRightRocket(Graphics g) {
		int x = xCenter + 40;
		int y = yCenter + 17;
		
		g.drawImage(droneSideRocket, x, y, 30, 30, null);
	}
	
	public void drawLeftRocket(Graphics g) {
		int x = xCenter - 10;
		int y = yCenter + 17;
		
		g.drawImage(droneSideRocket, x, y, 30, 30, null);
	}

	//method that draws when fire is called 
	public void fire(Graphics g) {
		int x = getX();
		int y = getY();
		
		g.drawLine(x, y, x+10, y+10);
		
		x += 10;
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	//getBounds method that is used in collision detection
	public Rectangle getBounds(){
		return new Rectangle(xCenter, yCenter, sizex, sizey);
	}

	
}
