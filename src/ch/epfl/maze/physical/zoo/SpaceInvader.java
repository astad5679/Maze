package ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Random;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Space Invader A.I. that implements an algorithm of your choice.
 * <p>
 * Note that this class is considered as a <i>bonus</i>, meaning that you do not
 * have to implement it (see statement: section 6, Extensions libres).
 * <p>
 * If you consider implementing it, you will have bonus points on your grade if
 * it can exit a simply-connected maze, plus additional points if it is more
 * efficient than the animals you had to implement.
 * <p>
 * The way we measure efficiency is made by the test case {@code Competition}.
 * 
 * @see ch.epfl.maze.tests.Competition Competition 
 * 
 */

public class SpaceInvader extends Animal {

	/**
	 * Constructs a space invader with a starting position.
	 * 
	 * @param position
	 *            Starting position of the mouse in the labyrinth
	 */

	private final Random RANDOM = new Random();
	private ArrayList<Vector2D> deadEnds = new ArrayList<Vector2D>();
	private Direction previousDir = Direction.NONE; 
	private int counter = 1;
	private Vector2D end;

	/**
	 * Constructs a hamster with a starting position.
	 * 
	 * @param position
	 *            Starting position of the hamster in the labyrinth
	 */

	public SpaceInvader(Vector2D position) {
		super(position);
		end = this.getPosition();
//		previousDir = Direction.NONE;
	}

	/**
	 * Moves without retracing directly its steps and by avoiding the dead-ends
	 * it learns during its journey.
	 */

	@Override
	public Direction move(Direction[] choices) {
		
		Direction nextDir;
		//the hamster is in a crossroad
		if (choices.length > 2){
			
			ArrayList<Direction> choicesPos = dirToVect(choices);
			//if there are no possibles ways the hamster will stop
			if (choicesPos.size() == 0 && counter == 2){	
				return Direction.NONE;
			}
			
			if (counter == 2){
				
				//if the crossroad have only one way not already taken, the hamster will take it and count that crossroad like an impasse
				if (choicesPos.size() == 1){
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
					counter = 1;
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
			if (choices.length == 1 && !(previousDir == Direction.NONE)){						
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
		SpaceInvader s = new SpaceInvader(position);
		s.deadEnds = this.deadEnds;
		
		// TODO
		return s;
	}
}
