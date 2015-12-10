package ch.epfl.maze.physical.zoo;

import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Monkey A.I. that puts its hand on the left wall and follows it.
 * 
 */

public class Monkey extends Animal {

	private Direction currentDir = Direction.NONE;

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
		
		Direction nextDir = Direction.NONE; //Animals are placed with no initial trajectory, so it would be wrong to state that their default next direction where to be a
											//relative UP or something. ALL direction choices are made depending on the animals movements, including the starting one
//		System.out.println("Current Direction: " + previousDir);
		
		Direction[] relativeChoices = currentDir.relativeDirections(choices); //converts all the possible directions in "choices" to their respective choice relative to the current direction

		if (possibleDir(relativeChoices, Direction.LEFT)) { //calls the method possibleDir() while changing the choice parameter of choice
			//return currentDir;							//this basically allows us to respect the priority of certain choices ("if left is available, then take that direction, otherwise go to the next priority")
		}
		
		//To comment on the if-else conditions we used here:
		//	Personally, this does seem a little sketchy, because these conditions don't actually execute any code. Clearly, if one of these passes, then all the others
		//	are skipped, but it still seems like a wasted function. However, it seemed less redundant to return the next direction at the end of the method just because
		//	it is exactly what the method needs to output (and we would have to place it anyways if, for some reason, all the if conditions failed).
		else if (possibleDir(relativeChoices, Direction.UP)) {
			//return currentDir;
		}
		
		else if (possibleDir(relativeChoices, Direction.RIGHT)) {
			//return currentDir;
		}
				
		else if (choices.length == 1 && choices[0] != Direction.NONE) { //if we only have the choice to go backwards, then do so
//			System.out.println("Choice: " + choices[0]);
			nextDir = choices[0];
//			System.out.println("Relative choice: " + nextDir);
			currentDir(nextDir);
			//return currentDir;
		}
		
		else if (currentDir == Direction.NONE) { //if the starting point is in the middle of nowhere (or between two horizontal walls), pick a random direction
			Random rand = new Random();
			nextDir = choices[rand.nextInt(choices.length)];
			currentDir(nextDir);
			System.out.println("here!");
			//return currentDir;
		}
		
//		System.out.println("current: " + currentDir);
		return currentDir; //Since currentDir is now equivalent to the next direction, we can just return this 
	}

	private void currentDir(Direction nextDir) {
		currentDir = nextDir;
	}
	
	private boolean possibleDir(Direction[] relativeChoices, Direction dir) {
		for (Direction choice : relativeChoices) {
			if (choice == dir) {
				//System.out.println("Choice: " + choice);
				Direction nextDir = currentDir.unRelativeDirection(dir);
				//System.out.println("Relative choice: " + nextDir);
				currentDir(nextDir);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Monkey m = new Monkey(position);
		
		return m;
	}
}
