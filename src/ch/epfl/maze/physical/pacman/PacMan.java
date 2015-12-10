package ch.epfl.maze.physical.pacman;

import java.util.ArrayList;
import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Pac-Man character, from the famous game of the same name.
 * 
 */

public class PacMan extends Prey {

	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public PacMan(Vector2D position) {
		super(position);
		// TODO
	}

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		if (choices.length == 1 && choices[0] != Direction.NONE) { //This method disregards the main aspect of the mouse which is, as prescribed, never to retrace its steps
			previousDir(choices[0]);							   //considering that it does need to turn around if at a dead end, we admit that in the case where only 
			return choices[0];									   //one direction is available, he will choose that one no matter what
			
		} 
		
		ArrayList<Direction> mouseChoices = new ArrayList<Direction>();
		for (Direction choice : choices) {
			if (!choice.isOpposite(previousDir)) {
				mouseChoices.add(choice);
			}
		}
		
		int index = RANDOM.nextInt(mouseChoices.size()); //We only generate a random index when the taboo direction has been removed from the new list of choices, this
														 //allows us to get rid of a potentially infinite while loop
		previousDir(mouseChoices.get(index)); 
		
		return mouseChoices.get(index);
		
			
	}
	
	private void previousDir(Direction currentDir) { //This method simply updates the value of the previous direction with the one of the current one
		previousDir = currentDir;
	}
	
	@Override
	public Direction getDir() {
		return previousDir;
	}
	
	@Override
	public Animal copy() {
		// TODO
		PacMan p = new PacMan(this.getPosition());
		return p;
	}
}
