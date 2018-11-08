/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #4
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */

//imports Java packages
import java.awt.Rectangle;
import java.util.LinkedList;

//class that contains methods that detect collision
public class Detector {

	//detects collision between bullet and enemy by iterating through relative lists and return index if dectected
	public static int Collision(LinkedList<DroneProjectile> self, LinkedList<AgressiveEnemy> other) {
		int d = -1;
		
		for (int n = 0; n < self.size(); n++) {
		for (int i = 0; i < other.size(); i++) {
			if(self.get(n).getBounds().intersects((other.get(i)).getBounds())) {
	
				d = i;
				
			}
		}
		}
		
		return d;
	}
	
	//All methods below work similarly to the one above but between different elements
	
	public static int Hit(Self self, LinkedList<AgressiveEnemy> other) {
		
		for (int i = 0; i < other.size(); i++) {
			if(self.getBounds().intersects((other.get(i)).getBounds())) {
				return i;
				
			}
		}
		
		return -1;
	}
	
	public static int Fall(Self self, LinkedList<FallingRocks> other) {
		
		for (int i = 0; i < other.size(); i++) {
			if(self.getBounds().intersects((other.get(i)).getBounds())) {
				return i;
				
			}
		}
		
		return -1;
	}
	
	public static int Score(Self self, LinkedList<Star> other) {
		
		for (int i = 0; i < other.size(); i++) {
			if(self.getBounds().intersects((other.get(i)).getBounds())) {
				return i;
				
			}
		}
		
		return -1;
	}
	
	public static int Still(Self self, LinkedList<StationaryRocks> other) {
		
		for (int i = 0; i < other.size(); i++) {
			if(self.getBounds().intersects((other.get(i)).getBounds())) {
				return i;
				
			}
		}
		return -1;
}
	
}
