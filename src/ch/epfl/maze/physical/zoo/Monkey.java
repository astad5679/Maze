package ch.epfl.maze.physical.zoo;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Monkey A.I. that puts its hand on the left wall and follows it.
 * 
 */

public class Monkey extends Animal {

	private static Direction previousDir = Direction.NONE;

	/**
	 * Constructs a monkey with a starting position.
	 * 
	 * @param position
	 *            Starting position of the monkey in the labyrinth
	 */

	public Monkey(Vector2D position) {
		super(position);
		// TODO
	}

	/**
	 * Moves according to the relative left wall that the monkey has to follow.
	 */

	@Override
	public Direction move(Direction[] choices) {
		// TODO
		
		Direction nextDir = Direction.NONE;
		
		for (Direction dir : choices) {
			if (dir == previousDir.rotateLeft()) {
				return dir;
			}
		}
		
		for (Direction dir : choices) {
			if (dir == Direction.NONE) {
				return dir;
			}
		}
		
		
		if (choices.length == 1 && choices[0] != Direction.NONE) {
			previousDir(choices[0]);
			return choices[0];
		}
		
		/*
		for (Direction dir : choices) {
			if (!dir.isOpposite(previousDir)) {
				if (choices.length == 1) {
					
				} else if (choices.length == 2) {
					nextDir = dir.rotateLeft();
				} else if (choices.length >= 3) {
					
				}
			}
		}
		*/
		
		previousDir(nextDir);
		
		return Direction.NONE;
	}

	private void previousDir(Direction currentDir) {
		previousDir = currentDir;
	}
	
	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Animal m = new Monkey(position);
		
		return m;
	}
}
