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
	private ArrayList<Vector2D> deadEnds = new ArrayList<Vector2D>();
	private Direction previousDir = Direction.NONE;
	private int counter = 1;

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
		ArrayList<Direction> choicesPos = dirToVect(choices);
		Direction nextDir;
		//the hamster is in a crossroad
		if (choices.length > 2){
			
			//if there are no possibles ways the hamster will stop
			if (choicesPos.size() == 0 && counter == 2){	
				return Direction.NONE;
			}
			
			if (counter == 2){
				
				//if the crossroad have only one way not already taken, the hamster will take it and count that crossroad like an impasse
				if (choicesPos.size() == 1){
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
				}
				//add the impasse to the deadEnd list
				else{
					
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
					counter = 1;
				}
			}
			
			//when the hamster finds an impasse it goes back and set the counter to 2
			if (choicesPos.size() == 0){					  	
				counter = 2;
				nextDir = previousDir.reverse();
			
			//in case of multiple choices the hamster's choice is random
			}else{
				
				int index = RANDOM.nextInt(choicesPos.size()); 
				nextDir = choicesPos.get(index);
			}
			
			previousDir = nextDir;
			return nextDir;
		
		//the hamster is not in a crossroad
		}else{	
			
			//if the hamster finds an impasse it goes back and set the counter to 2
			if (choicesPos.size() == 0){						
				counter = 2;
				nextDir = previousDir.reverse();
				
			}else{
			
			//the hamster doesn't go back 
			ArrayList<Direction> doubleChoices = new ArrayList<Direction>(); 
			for (Direction choice : choices){
				if (!choice.isOpposite(previousDir)) {
					doubleChoices.add(choice);
				}
			}
			nextDir = doubleChoices.get(0);
		}
			previousDir = nextDir;
			return nextDir;
		}
	}
	
	//This method converts all the possible choices to what positions they represent in the labyrinth
	private ArrayList<Direction> dirToVect(Direction[] choices) { 
		Vector2D currentPos = this.getPosition();
		ArrayList<Direction> choicesPos = new ArrayList<Direction>();
		for (Direction choice : choices) {
			Vector2D choiceVect = currentPos.addDirectionTo(choice);
			if (!deadEnds.contains(choiceVect) &&  !choice.isOpposite(previousDir)) {
				choicesPos.add(choice); 
			}
		}
		return choicesPos;
	}
	
	@Override
	public Animal copy() {
		Vector2D position = this.getPosition ();
		Animal h = new Hamster(position);
		
		// TODO
		return h;
	}
}
