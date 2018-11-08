/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//imports all required packages 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;

//Main class that executes the game by creating the frame, rendering the graphics and calling on external methods. 
//extends JFrame
public class GameFrame extends JFrame {
	
	//Declares and initializes variables neccesary for game execution
	private static final long serialVersionUID = 1L;
	public static final int time = 100; 
	public static final int CWidth = 640; 
	public static final int CHeight = 480; 
	public static final int CBase = 480; 
	public static final int Base = 480;
	public PaintCanvas canvas;
	public Timer timer = new Timer(time, new AnimationHandler());
	boolean fire, mainRocket, leftRocket, rightRocket, gameOn, endGame, midGame, noFuel, noLives = false;
	boolean startGame = true;
	int level = 1;
	int lives = 3; 
	int score; 
	int fuel = 750;
	int angle = 0;
	
	//Declares and initializes objects required for game execution
	GameDrone drone = new GameDrone(0, 0, "filename", 60, 60);
	SpriteController element = new SpriteController(this);
	Background background = new Background();
	Random random = new Random();
	goalBase now = new goalBase(600, 360, "", 100, 100);
	
	
	//Declares LinkedLists neccessary for production of obstacles and other elements
	public LinkedList<Self> self;
	public static LinkedList<Other> other;
	public static LinkedList<AgressiveEnemy> enemies;
	public static LinkedList<FallingRocks> rocks;
	
	//Declares gui elements
	JLabel scoreLabel, levelLabel, lifeLabel, fuelLabel;
	JPanel Info;
	
	
	//Creates PaintCanvas class that extends JPanel and does the drawing for the game. 
	public class PaintCanvas extends JPanel{
		public void paintComponent(Graphics g) {
			
			//If-elseif-else conditionals that determine what the canvas draws dependent on game's state
			//Triggered when gameOn is true
			if (gameOn) {
				System.out.println("Canvas Created");
				
				//calls on background to generate background with respect to the level with Graphics g
				background.generateBackground(level, g);
				
				//If-elseif-else conditionals that draws drone's rockets when fired
				drone.drawDrone(g);
				if (fire) {
					element.drawBullet(g);
				}
				if (mainRocket) {
					drone.drawMainRocket(g);
				}
				
				if (rightRocket) {
					drone.drawRightRocket(g);
				}
				
				if (leftRocket) {
					drone.drawLeftRocket(g);
				}
				
				//Conditional that ends game when lives are 0;
				if (lives <= 0) {
					startGame = false;
					gameOn = false;
					midGame = false;
					endGame = true;
				}
				
				//triggered when startGame is true to render the startScreen
			} else if (startGame) {
				//Draws string in the center welcoming user and asking to press ENTER to continue
				g.drawString("Weclome to Drone", CWidth/2 - 50, CHeight/2);
				g.drawString("Press ENTER to start", CWidth/2 - 50, CHeight/2 + 20);
				
				//triggered when endGame is true
			} else if (endGame) {
				
				//triggered when Fuel = 0 and draws informatively reason for gameOver
				if (noFuel) {
					g.drawString("Out of Fuel!", CWidth/2 - 50, CHeight/2 + 20);
				}
				
				//triggered when Lives = 0 and draws informatively reason for gameOver
				if (noLives) {
					g.drawString("Out of Lives!", CWidth/2 - 50, CHeight/2 + 20);
				}
				
				//draws GAME OVER
				g.drawString("GAME OVER", CWidth/2 - 50, CHeight/2);
				
				//triggered when midGame is true in between stages
			} else if (midGame) {
				
				//Presents info and provides options
				g.drawString("CONGRATULATIONS! You finished level " + level + "." , CWidth/2 - 50, CHeight/2);
				g.drawString("Press ENTER to continue to level " + (level + 1) + ".", CWidth/2 - 50, CHeight/2 + 20);
				g.drawString("Press SHIFT to exit the game.", CWidth/2 - 50, CHeight/2 + 40);
			}
			
		}
	}
	
	//GameFrame constructor that calls initialize game with level
	public GameFrame() {
		initializeGame(level);
	}
	
	//initializeGame method that renders game dependent on level
	public void initializeGame(int level) {
		
		//renders graphics, background and drone and initializes the timer
		initializeGraphics();
		initializeBackground(level);
		initializeDrone();
		initializeTimer();
		
	}
	
	
	//Initialize Background method that adds appropriate external elements to lists 
	// using element the SpriteController that then will be used to graphically generate 
	private void initializeBackground(int n) {
		Graphics g = canvas.getGraphics();
		
		element = new SpriteController(this);
		
		//For loops that create new game elements dependent on level on a random basis with set restrictions 
		for (int i = 0; i < n; i++) {
			
			int x = random.nextInt(500-100) + 100;
			int size = random.nextInt(100-50) + 50;
			
			element.addStationaryRock(new StationaryRocks(x, 360, "graphics/Drone-rocks.png", size, size));
		
			
			
			}
		
		for (int i = 0; i < n; i++) {
			
			int x = random.nextInt(500-100) + 100;
			int size = random.nextInt(100-50) + 50;

			
			element.addFallingRock(new FallingRocks(x, 360, "graphics/Drone-rockflipped.png", size, size));
			element.addOther(new FallingRocks(x, 360, "graphics/Drone-rocks.png", size, size));
			
			
				
			}
		
		for (int i = 0; i < n; i++) {
			
			int y = random.nextInt(300-80) + 80;
			
			element.addAgressiveEnemy(new AgressiveEnemy(620, y, "graphics/Drone-enemy.png", 60, 60));
			element.addOther(new AgressiveEnemy(620, y, "graphics/Drone-enemy.png", 60, 60));
			
			}
		
		for (int i = 0; i < n; i++) {
			
			int x = random.nextInt(500-100) + 100;
			int y = random.nextInt(300-80) + 80;
			
			element.addStar(new Star(x, y, "graphics/Drone-star.png", 40, 40));
	
			
			}
	
		
		self = element.getSelf();
		other = element.getOther();
		
		System.out.println(element.other.toString());
		
		background.generateBackground(n, g);
		
	}


	//initialize Timer that updates drone 
	public void initializeTimer() {
		drone.update();
	}
	
	//Initialize Graphics method that creates the canvas, the frame and JPanel
	public void initializeGraphics() {
		//sets title, close operation, layout for the Frame
		setTitle("Drone Pilot");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Creates new PaintCanvas, sets it size and adds it to the south of the Frame
		canvas = new PaintCanvas();
		canvas.setPreferredSize(new Dimension(CWidth,CHeight));
		add(canvas, BorderLayout.SOUTH);
		
		//Creates the GUI elements for the JPanel to keep user informed of Game's State
		Info = new JPanel();
		scoreLabel = new JLabel("Score:");
		levelLabel = new JLabel("Level:");
		lifeLabel = new JLabel("Life:");
		fuelLabel = new JLabel("Fuel:");
		Info.setLayout(new FlowLayout());
		Info.add(levelLabel);
		Info.add(scoreLabel);
		Info.add(lifeLabel);
		Info.add(fuelLabel);
		
		//adds JPanel to the North
		add(Info, BorderLayout.NORTH);
		
	
		//Adds KeyListener to Frame and sets its resizable to false and focusable to true and packs it; 
		addKeyListener(new KeyCommand());
		setResizable(false);
		setFocusable(true);
		pack();
	}
	
	//method that creates a new drone and sets its location 
	public void initializeDrone() {
		drone = new GameDrone(0, 0, "filename", 60, 60, this);
		element.addSelf(drone);
		int x = 20;
		int y = 420;
		drone.setLocation(x, y);
		
	}
	
	//start game method that sets Frame to visible and starts the timer 
	public void startGame() {
		
		setVisible(true);
		timer.start();
	}
	
	//AnimationHandler that updates game everytimer timer is triggered by implementing ActionListener 
	public class AnimationHandler implements ActionListener {
		public void actionPerformed(ActionEvent t) {
			
			System.out.println("Timer triggered");
			
			//Updates Labels dependent on state of game
			scoreLabel.setText("Current Score: " + score);
			levelLabel.setText("Current Level: " + level);
			lifeLabel.setText("Current Lives: " + lives);
			fuelLabel.setText("Current Fuel: " + fuel);
			
			
			//The following method detect collision between different objects and respond as programmed  
			if (drone.getBounds().intersects(now.getBounds())){
				
				if(gameOn) {
				score += 150;
				}
				
				gameOn = false;
				midGame = true;
			}
			
			
			
			if (gameOn) {
			
			if(!(Detector.Hit(drone, element.getAgressiveEnemy())== -1)) {          
				element.getAgressiveEnemy().remove(element.getAgressiveEnemy().get(Detector.Hit(drone, element.getAgressiveEnemy())));
				
				lives  = lives - 1;
				
				if (lives == 0) {
					noLives = true;
					endGame = true;
					gameOn = false;
				}
				
				
				
			}
			
			
			if(!(Detector.Collision(element.getDroneProjectile(), element.getAgressiveEnemy())== -1)) {          
				element.getAgressiveEnemy().remove(element.getAgressiveEnemy().get(Detector.Collision(element.getDroneProjectile(), element.getAgressiveEnemy())));
				
				score += 50;
				
				
			}
			
			if(!(Detector.Fall(drone, element.getFallingRocks())== -1)) {          
				element.getFallingRocks().remove(element.getFallingRocks().get(Detector.Fall(drone, element.getFallingRocks())));
				
				lives  = lives - 1;
				
				if (lives == 0) {
					noLives = true;
					endGame = true;
					gameOn = false;
				}
				
				
			}
			
			if(!(Detector.Score(drone, element.getStar())== -1)) {          
				element.getStar().remove(element.getStar().get(Detector.Score(drone, element.getStar())));
				
				score  += 50;
			}
			
			if(!(Detector.Still(drone, element.getStationaryRocks())== -1)) {          
				element.getStationaryRocks().remove(element.getStationaryRocks().get(Detector.Still(drone, element.getStationaryRocks())));
				
				fuel  -= 150;
			}
		
			}
			
			//updates drone, FallingRock and Enemies and if fire -- bullets
			drone.update();
			element.addSelf(drone);
			element.updateFallingRock();
			element.updateAgressiveEnemy();
			if (fire) {
				element.updateBullet();
			}
			
			//calls repaints
			repaint();
		}
	}
	
	//main method that triggers the game by creating new GameFrame and calling startGame
	public static void main(String[] args) {
		GameFrame current = new GameFrame();
		current.startGame();
		
	}
	
	//method that implements KeyListener and allows for appropriate reaction to user input through the keyboard
	public class KeyCommand implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
			System.out.println("key triggered");
			
			//Switch loop that detects which key was pressed and reacts appropriately
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_RIGHT:
				
				//only sets out if fuel is more than 0 and edits angle postively
				if (fuel > 0) {
				leftRocket = true;
				drone.addAngle(+20);
				}
				break;
				
			case KeyEvent.VK_LEFT:
				
				//only sets out if fuel is more than 0 and edits angle negatively
				if (fuel > 0) {
				rightRocket = true;
				drone.addAngle(-20);
				}
				break;
				
			case KeyEvent.VK_UP:
				
				//only sets out if fuel is more than 0 and edits direction postively
				if (fuel > 0) {
					mainRocket = true;
					drone.addSpeed(-15);
					useFuel();
				} 
				
				//is no fuel ends game 
				if (fuel <= 0){
					noFuel = true;
					gameOn = false;
					endGame = true; 
				}
				
				break;
				
			case KeyEvent.VK_DOWN:
				
				//only sets out if fuel is more than 0 and edits direction negatively
				if (fuel > 0) {
					drone.addSpeed(+15);
					useFuel();
				} 
				
				//is no fuel ends game 
				if (fuel <= 0){
					noFuel = true;
					gameOn = false;
					endGame = true; 
				}
				break;
				
			case KeyEvent.VK_SPACE:
				
				//calls shoot to make the drone shoot bullets
				System.out.println("Bar");
				shoot();
				break;
			
			case KeyEvent.VK_ENTER:
				//only works during startGame and starts game
				if (startGame) {
					startGame = false;
					gameOn = true;
					level = 1;
					
					//triggered during midGame and resets the game
				} else if (midGame && lives > 0) {
					level ++;
					midGame = false;
					startGame = false;
					endGame = false;
					gameOn = true;
					initializeBackground(level);
					initializeDrone();
					initializeTimer();

					fuel = 750;
					lives = 3;
			
				}
				
				//ends game when pressed during midGame
			case	 KeyEvent.VK_SHIFT:
				if (midGame) {
					midGame = false; 
					gameOn = false;
					endGame = true; 
					lives = 0;
				}
				
			}
			
			}


		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("key released");
			switch (e.getKeyCode()) {
			//sets angle to 90 && sets speed to 0 and rockets firing to false when keys are released 
			case KeyEvent.VK_RIGHT:	
				leftRocket = false;
				drone.setAngle(90);
				break;
				
			case KeyEvent.VK_LEFT:
				rightRocket = false;
				drone.setAngle(90);
				break;
				
			case KeyEvent.VK_UP:
				drone.setSpeed(0);
				mainRocket = false;
				break;
				 
			case KeyEvent.VK_DOWN:
				drone.setSpeed(0);
				break;
				
			case KeyEvent.VK_SPACE:
				break;
		
			}
			
		}
	}
	
	
	//shoot method that makes drone 'shoot' bullets
	public void shoot() {
		//sets fire to true
		fire = true;
		
		//adds a projectile to both the projectiles list from element that the self list
		element.addProjectile(new DroneProjectile(drone.getX(),drone.getY(), "graphics/Drone-bullet.png", 20, 20));
		element.addSelf(new DroneProjectile(drone.getX(),drone.getY(), "graphics/Drone-bullet.png", 20, 20));
		
	}
	
	//Background class that renders new bacground for every level
	public class Background {
		
		int width = 640;
		int height = 480;

		public Image loadImage(String filename) {
			try {
				return ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.err.println(e);
				return null;
			}
		}
		
		public void drawBlocks(Graphics g) {
		
			
		}
		
		
		public void drawCave(Graphics g, int n) {
			//creates the base and top rectangles
			g.setColor(Color.GRAY);
			g.fillRect(80, 0, 500, 80);
			g.fillRect(100, 400, 500, 80);
			
			Image rock = loadImage("graphics/Drone-rockflipped.png");
			
			//for loop that creates the top rocks of the 'cave;
			for (int i = 70; i < 560; i += 40) {
			g.drawImage(rock, i, -10, 100, 120, null);
			}
			
			//draws all game elements based on lists
			element.drawStationaryRock(g);
			element.drawFallingRock(g);
			element.drawAgressiveEnemy(g);
			element.drawStar(g);
		
		}
			
		//method that actually draws the background
		public void generateBackground(int n, Graphics g) {
			
			now.draw(g);
			drawCave(g, n);
			
		}
		
	}

	
	//setters and getters for lists and variables in the game
	public static LinkedList<Other> getOther() {
		return other;
	}
	
	public void useLife() {
		lives --;
	}
	
	public void setLevel(int level) {
		this.level = level; 
	}
	
	public int getLevel() {
		return level; 
	}
	
	public void addScore(int score) {
		this.score += score; 
	}
	
	public void useFuel() {
		this.fuel -= 10; 
	}
	
	public static LinkedList<AgressiveEnemy> getEnemies() {
		return enemies;
	}

	public static LinkedList<FallingRocks> getRocks() {
		return rocks;
	}

	public static void addEnemies(int i, AgressiveEnemy e) {
		enemies.add(i, e);
	}

	public static void addRocks(int i , FallingRocks e) {
		rocks.add(i, e);
	}

	


	

	
}
	



