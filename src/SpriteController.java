/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */


//imports required Java projects
import java.awt.Graphics;
import java.util.LinkedList;

//Class that creates an object that controls all the sprites
public class SpriteController {
	
	//Lists required 
	public LinkedList<DroneProjectile> projectiles = new LinkedList<DroneProjectile>();
	public LinkedList<FallingRocks> caveLoose = new LinkedList<FallingRocks>();
	public LinkedList<StationaryRocks> caveFixed = new LinkedList<StationaryRocks>();
	public LinkedList<AgressiveEnemy> enemyAgressive = new LinkedList<AgressiveEnemy>();
	public LinkedList<Star> points = new LinkedList<Star>();
	public LinkedList<Self> self = new LinkedList<Self>();
	public LinkedList<Other> other = new LinkedList<Other>();
	
	//Objects needed
	Self aself;
	Other another; 
	DroneProjectile currentProjectile; 
	FallingRocks currentFallingRock;
	StationaryRocks currentStationaryRock;
	AgressiveEnemy currentEnemy;
	Star currentStar;
	
	
	//Constructor
	public SpriteController(GameFrame gameFrame) {
		super();
	}
	
	//Update method for self and other
	public void update() {
		
		for (int i = 0; i <self.size(); i++) {
			aself = self.get(i);
			
			aself.update();
		}
		
		for (int i = 0; i <other.size(); i++) {
			another = other.get(i);
			
			another.update();
		}
		
		
	}
	
	//draw method for self and other
	public void draw(Graphics g) {
		
		for (int i = 0; i <self.size(); i++) {
			aself = self.get(i);
			
			aself.draw(g);
		}
		
		for (int i = 0; i <other.size(); i++) {
			another = other.get(i);
			
			another.draw(g);
		}
		
		
	}

	//Update, draw, add, remove method for game elements working basically the same using for loops to iterate relative LinkedLists 
	public void updateBullet() {
		for (int i = 0; i <projectiles.size(); i++) {
			currentProjectile = projectiles.get(i);
			
			if (currentProjectile.getX() > GameFrame.CWidth) {
				removeProjectile(currentProjectile);
			}
			
			currentProjectile.update();
		}
	}
	
	public void updateFallingRock() {
		for (int i = 0; i <caveLoose.size(); i++) {
			currentFallingRock = caveLoose.get(i);
			
			if (currentFallingRock.getX() > GameFrame.CWidth) {
				removeFallingRock(currentFallingRock);
			}
			
			currentFallingRock.update();
		}
		
	}

		
	public void updateAgressiveEnemy() {
		for (int i = 0; i < enemyAgressive.size(); i++) {
			currentEnemy = enemyAgressive.get(i);
			
			if (currentEnemy.getX() > GameFrame.CWidth) {
				removeAgressiveEnemy(currentEnemy);
			}
			
			currentEnemy.update();
		}
		
	}
	
	
	public void updateStar() {	
		for (int i = 0; i <points.size(); i++) {
			currentStar = points.get(i);
			
			if (currentStar.getX() > GameFrame.CWidth) {
				removeStar(currentStar);
			}
			
			currentStar.update();
		}
	}
	
	public void drawBullet(Graphics g) {
		for (int i = 0; i <projectiles.size(); i++) {
			currentProjectile = projectiles.get(i);
			
			currentProjectile.draw(g);
		}
		
	}
	
	public void drawFallingRock(Graphics g) {
		for (int i = 0; i <caveLoose.size(); i++) {
			currentFallingRock = caveLoose.get(i);
		
			currentFallingRock.draw(g);
		}
	
	}
	
	public void drawStationaryRock(Graphics g) {
		
		for (int i = 0; i <caveFixed.size(); i++) {
			currentStationaryRock = caveFixed.get(i);
			
			currentStationaryRock.draw(g);
		}
		
	}
	
	public void drawAgressiveEnemy(Graphics g) {
		
		for (int i = 0; i < enemyAgressive.size(); i++) {
			currentEnemy = enemyAgressive.get(i);
			
			currentEnemy.draw(g);
		}
	}
	
	public void drawStar(Graphics g) {
		for (int i = 0; i <points.size(); i++) {
			currentStar = points.get(i);
			
			currentStar.draw(g);
		}
		
		
		
	}
	
	public void addProjectile(DroneProjectile p) {
		projectiles.add(p);
	}
	
	public void removeProjectile(DroneProjectile p) {
		projectiles.remove(p);
	}
	
	public void addFallingRock (FallingRocks p) {
		caveLoose.add(p);
	}
	
	public void removeFallingRock (FallingRocks p) {
		caveLoose.remove(p);
	}
	public void addStationaryRock(StationaryRocks p) {
		caveFixed.add(p);
	}
	
	public void removeStationaryRock(StationaryRocks p) {
		caveFixed.remove(p);
	}
	
	public void addAgressiveEnemy(AgressiveEnemy p) {
		enemyAgressive.add(p);
	}
	
	public void removeAgressiveEnemy(AgressiveEnemy p) {
		enemyAgressive.remove(p);
	}
	
	public void addStar(Star p) {
		points.add(p);
	}
	
	public void removeStar(Star p) {
		points.remove(p);
	}
	
	public void addSelf(Self a) {
		self.add(a);
		
	}
	
	public void removeSelf(Self a) {
		self.remove(a);
	}

	public void addOther(Other a) {
		other.add(a);
	}
	public void removeOther(Other a) {
		other.remove(a);
	}
	
	public LinkedList<Self> getSelf(){
		return self;
	}
	 
	public LinkedList<Other> getOther(){
		return other;
	}
	
	public LinkedList<DroneProjectile> getDroneProjectile(){
		return projectiles;
	}
	 
	public LinkedList<FallingRocks> getFallingRocks(){
		return caveLoose;
	}
	public LinkedList<StationaryRocks> getStationaryRocks(){
		return caveFixed;
	}
	 
	public LinkedList<AgressiveEnemy> getAgressiveEnemy(){
		return enemyAgressive;
	}
	
	public LinkedList<Star> getStar(){
		return points;
	}
	


}
