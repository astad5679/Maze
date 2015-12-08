package ch.epfl.maze.physical.zoo;

import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Bear A.I. that implements the Pledge Algorithm.
 * 
 */

public class Bear extends Animal {
	
	private Direction currentDir = Direction.NONE;
	private Direction favDir;
	private final Random rand = new Random();
	private int counter = 0;
	private int obstacleCheck = 1;

	/**
	 * Constructs a bear with a starting position.
	 * 
	 * @param position
	 *            Starting position of the bear in the labyrinth
	 */

	public Bear(Vector2D position) {
		super(position);
		favDir = Direction.values()[rand.nextInt(4)];
		System.out.println("Fav: " + favDir);
		// TODO
	}

	/**
	 * Moves according to the <i>Pledge Algorithm</i> : the bear tries to move
	 * towards a favorite direction until it hits a wall. In this case, it will
	 * turn right, put its paw on the left wall, count the number of times it
	 * turns right, and subtract to this the number of times it turns left. It
	 * will repeat the procedure when the counter comes to zero, or until it
	 * leaves the maze.
	 */


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
		
		if (obstacleCheck == 1) {
			if (possibleDir(relativeChoices, favDir)) { //calls the method possibleDir() while changing the choice parameter of choice
				return currentDir;							//this basically allows us to respect the priority of certain choices ("if left is available, then take that direction, otherwise go to the next priority")

				/*
				 * if this is false, we can assume we hit a wall, in which case the bear would need to turn right (and start a new counter) - can only be done with a counter variable
				 *this means we need to set up some sort of dynamic next direction, and only then proceed with the monkey method
				 */
			} else {
				obstacleCheck = 2;
				counter = 1;
				currentDir = currentDir.unRelativeDirection(Direction.RIGHT);
				return currentDir;
			}
		}
		
		else {
			if (possibleDir(relativeChoices, Direction.LEFT)) { //calls the method possibleDir() while changing the choice parameter of choice
				//return currentDir;							//this basically allows us to respect the priority of certain choices ("if left is available, then take that direction, otherwise go to the next priority")
				counter--;
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
				counter++;
			}
					
			else if (choices.length == 1 && choices[0] != Direction.NONE) { //if we only have the choice to go backwards, then do so
//				System.out.println("Choice: " + choices[0]);
				nextDir = choices[0];
//				System.out.println("Relative choice: " + nextDir);
				currentDir(nextDir);
				//return currentDir;
			}
			
			else if (currentDir == Direction.NONE) { //if the starting point is in the middle of nowhere (or between two horizontal walls), pick a random direction
				nextDir = choices[rand.nextInt(choices.length)];
				currentDir(nextDir);
				//return currentDir;
			}
			
			if (counter == 0) {
				obstacleCheck = 1;
			}
			
			return currentDir; //Since currentDir is now equivalent to the next direction, we can just return this 
			
		}
		
	}

	private void currentDir(Direction nextDir) {
		currentDir = nextDir;
	}
	
	//switch case to sort the priority
	
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
	
	public Direction monkeyMove() {
		
		return Direction.NONE;
	}
	
	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Bear b = new Bear(position);
		//b.favDir = this.favDir;
		
		return b;
	}
}
