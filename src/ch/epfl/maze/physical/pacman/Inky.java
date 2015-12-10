package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;
import ch.epfl.maze.physical.pacman.Blinky;

/**
 * Pink ghost from the Pac-Man game, targets 4 squares in front of its target.
 * 
 */

public class Inky extends Predator {

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public Inky(Vector2D position) {
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
		Vector2D blinkyPos = new Vector2D(0, 0);
		
		for (Predator pred : daedalus.getPredators()) {
			if (pred instanceof Blinky) {
				blinkyPos = pred.getPosition();
			}
		}
		
		Vector2D blinkyDist = preyPos.sub(blinkyPos);
		blinkyDist = blinkyDist.add(blinkyDist);
		blinkyDist = blinkyPos.add(blinkyDist);
		
		return this.ghostPara(choices, daedalus, blinkyDist);
	}
	
	@Override
	public Animal copy() {
		// TODO
		Inky p = new Inky(this.getPosition());
		return p;
	}
}
