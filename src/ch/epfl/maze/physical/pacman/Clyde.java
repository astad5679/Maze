package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Orange ghost from the Pac-Man game, alternates between direct chase if far
 * from its target and SCATTER if close.
 * 
 */

public class Clyde extends Predator {

	/**
	 * Constructs a Clyde with a starting position.
	 * 
	 * @param position
	 *            Starting position of Clyde in the labyrinth
	 */

	public Clyde(Vector2D position) {
		super(position);
		// TODO
	}

	//As stated more explicitley in its parent class and in the README, this method now calls upon a parent method to calculate the desireable choice, which this method will return on its own
	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		if (daedalus.getPreys().size() == 0) {
			System.out.println("NO PREY!");
			return Direction.NONE;
		}
		
		Prey prey = daedalus.getPreys().get(0);
		Vector2D preyPos = prey.getPosition();
		
		//Clyde will exclusively calculate the distance to his target using the parent method
		double distance = this.distanceCalc(this.getPosition(), preyPos); 
		if (distance <= 4.0) { //he then uses this to check if he is too close to his prey, in which case he targets his home position
			preyPos = this.HOME_POSITION;
		}
		//otherwise he just targets his prey the same way Blinky does
		
		return this.ghostPara(choices, daedalus, preyPos);
			
	}
	
	@Override
	public Animal copy() {
		// TODO
		Clyde c = new Clyde(this.getPosition());
		return c;
	}
}
