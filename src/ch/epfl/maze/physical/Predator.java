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
	private int timer;
	protected final Vector2D HOME_POSITION;
	private Direction previousDir = Direction.NONE;
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
	
	public void updateTimer() { //method that adds 1 to the timer
		timer++;
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

	//we decided to add a common move method for all the ghosts, because they work all in the same way, with the exception of the position they target (which is the "ghost parameter")
	public Direction ghostPara(Direction[] choices, Daedalus daedalus, Vector2D preyPos) {
		
		switch (mode) { //based on the current mode, the if condition will trigger sooner or later (and change the mode to it's respective counterpart) 
		case "CHASE":
			if (timer == CHASE_DURATION) {
				mode = "SCATTER";
				timer = 0;
			}
			break;
		case "SCATTER":
			preyPos = HOME_POSITION; //in the case we are in scatter mode, make sure the ghost targets its "home position", which is defined upon its creation
			if (timer == SCATTER_DURATION) {
				mode = "CHASE";
				timer = 0;
			}
			break;
		}
		
		if (choices.length == 1 && choices[0] != Direction.NONE) { //if there is only one choice, pick it (ghost turns around)
			previousDir = choices[0];							   
			return choices[0];									   
		} else if (choices.length == 0 || choices[0] == Direction.NONE) {
//			System.out.println("NO CHOICES!");
			return Direction.NONE;
		}
		
		nextDir = previousDir; //this gets set in case the if condition below doesn't trigger, in which case the ghost will just choose the same direction
		minDist = Double.POSITIVE_INFINITY; //resets the smallest distance from target
		
		
		for (Direction choice : choices) { //loops over all the available choices to calculate the respective positions the ghost would find itself on
			if (!choice.isOpposite(previousDir)) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), preyPos); //It then calculates the distance from said position to the position of its
				if (distanceCheck(distance)) {														//prey, and finds and stores the smallest one (with the choice it corresponds
					nextDir = choice;																//to, of course)
				}
			}
		}
		previousDir = nextDir;	
		updateTimer(); //updates the timer to represent a step (or movement) in the maze 
		return previousDir;
	}
	
	//This method calculates and returns the euclidean distance between two positions in the labyrinth
	public double distanceCalc(Vector2D nextPos, Vector2D preyPos) { 
		Vector2D difference = nextPos.sub(preyPos);
		return difference.dist();
	}
	
	//This method checks if the a given distance is smaller than the former one (which is why it is consistenly reset to infinity), and if so it rewrites the smallet distance with the given value
	//It only returns a boolean because the above loop needs to know whether to update nextDir with the respective choice (which can only be accessed in that method)
	private boolean distanceCheck(double newDist) {
		if (newDist < minDist) {
			minDist = newDist;
			return true;
		}
		return false;
	}
	
	//this method still acts the same as before, requiring the user to return a direction (this changes nothing for the main program classes)
	abstract public Direction move(Direction[] choices, Daedalus daedalus); 
}
