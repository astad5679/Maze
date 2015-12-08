package ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Mouse A.I. that remembers only the previous choice it has made.
 * 
 */

public class Mouse extends Animal {
	
	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;
	//int var = random.nextInt(2);

	/**
	 * Constructs a mouse with a starting position.
	 * 
	 * @param position
	 *            Starting position of the mouse in the labyrinth
	 */

	public Mouse(Vector2D position) {
		super(position);
		//System.out.println(this.getPosition());
	}

	/**
	 * Moves according to an improved version of a <i>random walk</i> : the
	 * mouse does not directly retrace its steps.
	 */

	@Override
	public Direction move(Direction[] choices) {
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
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Mouse m = new Mouse(position);
		
		return m;
	}
}
