package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Pink ghost from the Pac-Man game, targets 4 squares in front of its target.
 * 
 */

public class Pinky extends Predator {
	private Vector2D preyPrev;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public Pinky(Vector2D position) {
		super(position);
		preyPrev = this.getPosition();
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
		
		Vector2D preyVect = preyPos.sub(preyPrev);
		Direction preyDir = preyVect.toDirection();
		
		for (int i = 0; i < 4; i++) {
			preyPos = preyPos.addDirectionTo(preyDir);
		}
		
		preyPrev = preyPos;
		return this.ghostPara(choices, daedalus, preyPos);
	}
	
	@Override
	public Animal copy() {
		// TODO
		Pinky p = new Pinky(this.getPosition());
		return p;
	}
}
