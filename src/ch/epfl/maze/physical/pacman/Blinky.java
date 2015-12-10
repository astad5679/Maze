package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Red ghost from the Pac-Man game, chases directly its target.
 * 
 */

public class Blinky extends Predator {

	/**
	 * Constructs a Blinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Blinky in the labyrinth
	 */

	public Blinky(Vector2D position) {
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
		
		return this.ghostPara(choices, daedalus, preyPos);
	}
	
	@Override
	public Animal copy() {
		// TODO
		Blinky b = new Blinky(this.getPosition());
		return b;
	}
}
