package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.Random;

import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Prey that is killed by a predator when they meet each other in the labyrinth.
 * 
 */

abstract public class Prey extends Animal {
	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;

	/**
	 * Constructs a prey with a specified position.
	 * 
	 * @param position
	 *            Position of the prey in the labyrinth
	 */

	public Prey(Vector2D position) {
		super(position);
		// TODO
	}

	/**
	 * Moves according to a <i>random walk</i>, used while not being hunted in a
	 * {@code MazeSimulation}.
	 * 
	 */

	//In other words, moves in the same way as the mouse when no prey is around (assuming this exception is treated explicitly by the main program class)
	@Override
	public final Direction move(Direction[] choices) { 
		if (choices.length == 1 && choices[0] != Direction.NONE) { 
			previousDir = choices[0];							   
			return choices[0];									  
			
		} 
		
		ArrayList<Direction> newChoices = new ArrayList<Direction>();
		for (Direction choice : choices) {
			if (!choice.isOpposite(previousDir)) {
				newChoices.add(choice);
			}
		}
		
		int index = RANDOM.nextInt(newChoices.size()); 
														 
		previousDir = newChoices.get(index); 
		
		return newChoices.get(index);
	}

	/**
	 * Retrieves the next direction of the animal, by selecting one choice among
	 * the ones available from its position.
	 * <p>
	 * In this variation, the animal knows the world entirely. It can therefore
	 * use the position of other animals in the daedalus to evade predators more
	 * effectively.
	 * 
	 * @param choices
	 *            The choices left to the animal at its current position (see
	 *            {@link ch.epfl.maze.physical.World#getChoices(Vector2D)
	 *            World.getChoices(Vector2D)})
	 * @param daedalus
	 *            The world in which the animal moves
	 * @return The next direction of the animal, chosen in {@code choices}
	 */

	abstract public Direction move(Direction[] choices, Daedalus daedalus);
}


