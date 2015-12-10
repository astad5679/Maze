package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.Random;

import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Predator that kills a prey when they meet with each other in the labyrinth.
 * 
 */

abstract public class Predator extends Animal {

	/* constants relative to the Pac-Man game */
	public static final int SCATTER_DURATION = 14;
	public static final int CHASE_DURATION = 40;
	//implement another variable to count steps?
	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;
	private int timer;
	private final Vector2D HOME_POSITION;
	private Direction currentDir = Direction.NONE;
	private double minDist = Double.POSITIVE_INFINITY;
	private Direction nextDir = Direction.NONE;
	private String mode = "CHASE";

	/**
	 * Constructs a predator with a specified position.
	 * 
	 * @param position
	 *            Position of the predator in the labyrinth
	 */

	public Predator(Vector2D position) {
		super(position);
		HOME_POSITION = position;
		timer = 0;
		// TODO
	}

	/**
	 * Moves according to a <i>random walk</i>, used while not hunting in a
	 * {@code MazeSimulation}.
	 * 
	 */

	@Override
	public final Direction move(Direction[] choices) {
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
	
	public void updateTimer() {
		timer++;
//		System.out.println("timer: " + timer);
	}

	/**
	 * Retrieves the next direction of the animal, by selecting one choice among
	 * the ones available from its position.
	 * <p>
	 * In this variation, the animal knows the world entirely. It can therefore
	 * use the position of other animals in the daedalus to hunt more
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

	public Direction ghostPara(Direction[] choices, Daedalus daedalus, Vector2D preyPos) {
		
		switch (mode) {
		case "CHASE":
			if (timer == CHASE_DURATION) {
				mode = "SCATTER";
				timer = 0;
//				System.out.println("change");
			}
//			System.out.println("chase!");
			break;
		case "SCATTER":
			preyPos = HOME_POSITION;
			if (timer == SCATTER_DURATION) {
				mode = "CHASE";
				timer = 0;
			}
//			System.out.println("scatter!");
			break;
		}
		
		if (choices.length == 1 && choices[0] != Direction.NONE) { //This method disregards the main aspect of the mouse which is, as prescribed, never to retrace its steps
			previousDir(choices[0]);							   //considering that it does need to turn around if at a dead end, we admit that in the case where only 
			return choices[0];									   //one direction is available, he will choose that one no matter what
		} else if (choices.length == 0 || choices[0] == Direction.NONE) {
//			System.out.println("NO CHOICES!");
			return Direction.NONE;
		}
		
		nextDir = currentDir;
		minDist = Double.POSITIVE_INFINITY;
		
		for (Direction choice : choices) {
			if (!choice.isOpposite(currentDir)) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), preyPos);
//				System.out.println(choice + ": " + distance);
				if (distanceCheck(distance)) {
					nextDir = choice;
				}
			}
		}
		previousDir(nextDir);	
		updateTimer();
//		System.out.println(currentDir);
		return currentDir;
	}
	
	private void previousDir(Direction nextDir) { //This method simply updates the value of the previous direction with the one of the current one
		currentDir = nextDir;
	}
	
	private double distanceCalc(Vector2D nextPos, Vector2D preyPos) { 
		Vector2D difference = nextPos.sub(preyPos);
		return difference.dist();
	}
	
	private boolean distanceCheck(double newDist) {
		if (newDist < minDist) {
			minDist = newDist;
			return true;
		}
		return false;
	}
	
	abstract public Direction move(Direction[] choices, Daedalus daedalus); 
}
