package ch.epfl.maze.physical.zoo;

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
	
	private static final Random RANDOM = new Random();
	private static Vector2D lastChoice;
	private static Direction previousDir;
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
		
		//for (Direction choice : choices) {
		//	System.out.print(choice);
		//}
		//System.out.println();
		
		int index = RANDOM.nextInt(choices.length);
		
		if (choices.length == 1) { // && choices[0] != Direction.NONE) {
			return choices[0];
		}
		
		//System.out.println(index);
		while (choices[index].equals(previousDir)) {
			index = RANDOM.nextInt(choices.length);
			//System.out.println("hi");
		}
		
		
		previousDir(choices[index]);
		//this.update(choices[index]);
		
		return choices[index];
	}
	
	private void previousDir(Direction currentDir) {
		previousDir = currentDir;
	}
	
	private void lastChoice(Vector2D lastChoice) {
		this.lastChoice = lastChoice;
	}

	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Animal m = new Mouse(position);
		
		return m;
	}
}
