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

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		if (daedalus.getPreys().size() == 0) {
			System.out.println("NO PREY!");
			return Direction.NONE;
		}
		
		Prey prey = daedalus.getPreys().get(0);
		Vector2D preyPos = prey.getPosition();
//		System.out.println(position);
		
		double distance = this.distanceCalc(this.getPosition(), preyPos);
//		System.out.println("distance: " + distance);
		if (distance <= 4.0) {
			preyPos = this.HOME_POSITION;
		}
		
		return this.ghostPara(choices, daedalus, preyPos);
			
	}
	
	@Override
	public Animal copy() {
		// TODO
		Clyde c = new Clyde(this.getPosition());
		return c;
	}
}
