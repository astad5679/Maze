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

public class TestMouse extends Animal {
	
	private static final Random RANDOM = new Random();
	private static ArrayList<Vector2D> lastChoice = new ArrayList<Vector2D>();
	private static Direction previousDir = Direction.NONE;
	//int var = random.nextInt(2);

	/**
	 * Constructs a mouse with a starting position.
	 * 
	 * @param position
	 *            Starting position of the mouse in the labyrinth
	 */

	public TestMouse(Vector2D position) {
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
		
		/*
		int index = RANDOM.nextInt(choices.length);
		Direction nextDir = Direction.NONE;
		//System.out.println(choices.length);
		
		if (choices.length == 1) { // && choices[0] != Direction.NONE) {
			nextDir = choices[0];
		}
		
		if (choices.length == 2) {
			nextDir = previousDir;
		}
		
//		else {
//			while (choices[index].equals(previousDir.reverse())) {
//				index = RANDOM.nextInt(choices.length);
//			}
//			nextDir = choices[index];
//		}
		
//		System.out.println(index);
//		while (choices[index].equals(previousDir.reverse())) {
//			index = RANDOM.nextInt(choices.length);
//			System.out.print("changed ");
//		}
		
//		System.out.println(index);
		
		previousDir(nextDir);
//		System.out.println();
//		this.update(choices[index]);
		
		return nextDir;
		*/
		
		int index = RANDOM.nextInt(choices.length);
		
		if (choices.length == 1 && choices[0] != Direction.NONE) {
			previousDir(choices[0]);
			return choices[0];
			
		} else {
			do {
				index = RANDOM.nextInt(choices.length);
			} while(choices[index].isOpposite(previousDir)); //|| (this.getPosition().addDirectionTo(choices[index]).equals(lastChoice.get(0)) && choices.length == 3));
		}
		
//		System.out.print(this.getPosition());
//		if (choices.length >= 3) {
//			lastChoice (choices[index]);
			//System.out.println(" remembered: " + lastChoice[0]);
//		}
		
		previousDir(choices[index]);
		//System.out.println(choices[index]);
		
		return choices[index];
			
	}
	
	private void previousDir(Direction currentDir) {
		previousDir = currentDir;
	}
	
//	private void lastChoice(Direction nextDir) {
//		lastChoice.add(this.getPosition().addDirectionTo(nextDir));
//	}

	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Animal m = new TestMouse(position);
		
		return m;
	}
}
