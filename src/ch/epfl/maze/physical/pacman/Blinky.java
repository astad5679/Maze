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

	//As stated more explicitley in its parent class and in the README, this method now calls upon a parent method to calculate the desireable choice, which this method will return on its own
	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		
		if (daedalus.getPreys().size() == 0) { //This block of code is present for all the ghosts as a fail safe. Due to our lack of understanding of how the simulation unfolds,
			System.out.println("NO PREY!");    //we decided to implement this just in case, but it shouldn't actually every pass
			return Direction.NONE;
		}
		
		Prey prey = daedalus.getPreys().get(0); //In the case there are no preys, this would result in an error...(see above)
		Vector2D preyPos = prey.getPosition(); //All blinky does is target the exact position of his prey
//		System.out.println(position);
		
		return this.ghostPara(choices, daedalus, preyPos); //It is then passed as a paramater to the super move function
	}
	
	@Override
	public Animal copy() {
		// TODO
		Blinky b = new Blinky(this.getPosition());
		return b;
	}
}
