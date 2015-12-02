package ch.epfl.maze.physical.zoo;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Hamster A.I. that remembers the previous choice it has made and the dead ends
 * it has already met.
 * 
 */

public class Hamster extends Animal {

	/**
	 * Constructs a hamster with a starting position.
	 * 
	 * @param position
	 *            Starting position of the hamster in the labyrinth
	 */

	public Hamster(Vector2D position) {
		super(position);
		// TODO
	}

	/**
	 * Moves without retracing directly its steps and by avoiding the dead-ends
	 * it learns during its journey.
	 */

	@Override
	public Direction move(Direction[] choices) {
		// TODO
//		for (Direction choice : choices) {
//			System.out.print(choice);
//		}
//		System.out.println();
		//this.update(choices[0]);
		
		return choices[0];
	}

	@Override
	public Animal copy() {
		Vector2D position = this.getPosition ();
		Animal h = new Hamster(position);
		
		// TODO
		return h;
	}
}
