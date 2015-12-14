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
	
	//As stated more explicitley in its parent class and in the README, this method now calls upon a parent method to calculate the desireable choice, which this method will return on its own
	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		
		if (daedalus.getPreys().size() == 0) { //See Blinky for the explanation of this
			System.out.println("NO PREY!");
			return Direction.NONE;
		}
		
		Prey prey = daedalus.getPreys().get(0); 
		Vector2D preyPos = prey.getPosition(); //Inky takes both the position of his prey and that of blinky
		Vector2D blinkyPos = new Vector2D(0, 0);
		
		//For that he needs to check all the predators in the labyrinth until he finds "the instance of" Blinky
		for (Predator pred : daedalus.getPredators()) {
			if (pred instanceof Blinky) {
				blinkyPos = pred.getPosition();
			}
		}
		
		Vector2D blinkyDist = preyPos.sub(blinkyPos); //We then calculate the distance between the two
		blinkyDist = blinkyDist.add(blinkyDist); //then double it
		preyPos = blinkyPos.add(blinkyDist); //then add it to Blinky's original position to obtain the target position
		
		return this.ghostPara(choices, daedalus, preyPos);
	}
	
	@Override
	public Animal copy() {
		// TODO
		Inky p = new Inky(this.getPosition());
		return p;
	}
}
