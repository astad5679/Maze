package ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Hamster A.I. that remembers the previous choice it has made and the dead ends
 * it has already met.
 * 
 */

public class Hamster extends Animal {

	private final Random RANDOM = new Random();
	private ArrayList<Vector2D> deadEnds = new ArrayList<Vector2D>(); //This arraylist gets updated based on the dead ends the hamster finds
	private Direction previousDir = Direction.NONE;
	private int counter = 1; //The counter works to identify if the Hamster is walking back after having hit a dead-end or not. The entire principle of this algorithm is based 
	//on this counter. As soon as the hamster finds a cross-roads, he will mark the position he just came from as a dead end.

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
		//create a new list of choices which removes the opposite direction and all directions which would lead to a dead end
		ArrayList<Direction> newChoices = checkChoices(choices);
		Direction nextDir; //this variable represents the next direction the hamster will take
		//the Hamster is at a cross-roads
		if (choices.length > 2){
			
			//If there are no possible choices, the hamster will stop
			if (newChoices.size() == 0 && counter == 2){	
				return Direction.NONE;
			}
			
			if (counter == 2){
				
				//if the cross-roads only has one path that hasn't been taken yet, the hamster will choose it and treat that cross-roads as a dead end
				if (newChoices.size() == 1){
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
				}
				//add the impasse to the deadEnd list
				else {
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
					counter = 1;
				}
			}
			
			//when the hamster finds a dead end, it turns around and sets the counter to 2
			if (newChoices.size() == 0){					  	
				counter = 2;
				nextDir = previousDir.reverse();
			
			//in the case of multiple choices, the hamster chooses randomly
			} else {
				int index = RANDOM.nextInt(newChoices.size()); 
				nextDir = newChoices.get(index);
			}
			
			previousDir = nextDir; //update the previous direction to match the direction returned
			return nextDir;
		
		//the hamster is not at a cross-roads
		} else {	
			
			//if the hamster finds a dead end it goes back and set the counter to 2
			if (newChoices.size() == 0){						
				counter = 2;
				nextDir = previousDir.reverse();
				
			} else {
			//the hamster does not turn around, instead opting for the one direction which will not make him do so
				nextDir = newChoices.get(0); 
		}
			previousDir = nextDir;
			return nextDir;
		}
	}
	
	//This method create a new list of choices based on the all the possible ones, which excludes the opposite direction and all directions which would lead to a dead end
	private ArrayList<Direction> checkChoices(Direction[] choices) { 
		Vector2D currentPos = this.getPosition();
		ArrayList<Direction> newChoices = new ArrayList<Direction>();
		for (Direction choice : choices) {
			Vector2D choiceVect = currentPos.addDirectionTo(choice);
			if (!deadEnds.contains(choiceVect) &&  !choice.isOpposite(previousDir)) {
				newChoices.add(choice); 
			}
		}
		return newChoices;
	}
	
	@Override
	public Animal copy() { //Creates a new hamster which it initializes with the same position of the instance this method is called from 
		Vector2D position = this.getPosition(); 
		Hamster h = new Hamster(position);
		return h;
	}
}
