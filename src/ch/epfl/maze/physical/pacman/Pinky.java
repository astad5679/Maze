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
	private Vector2D preyPrev; //Pinky has an exclusive attribute which permits him to store pacmans previous position

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
		
		Vector2D preyVect = preyPos.sub(preyPrev); //these two lines permit us to calculate pacmans direction based on where he moved respective to his previous position
		Direction preyDir = preyVect.toDirection();
		preyPrev = preyPos; //update his position
		
		for (int i = 0; i < 4; i++) { //we then just add the same direction 4 times in a row to obtain our target position
			preyPos = preyPos.addDirectionTo(preyDir);
		}
		
		return this.ghostPara(choices, daedalus, preyPos);
	}
	
	@Override
	public Animal copy() {
		// TODO
		Pinky p = new Pinky(this.getPosition());
		return p;
	}
}
