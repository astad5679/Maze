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
	private Vector2D[] currentIntersection = new Vector2D[1];
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
		// TODO
		
		ArrayList<Direction> choicesPos = dirToVect(choices);
		Direction nextDir;
		
//		System.out.println("Choices length: " + choices.length);
		if (choices[0] == Direction.NONE) {
			nextDir = Direction.NONE;
		} else if (choicesPos.size() == 0) {
			nextDir = choices[0];
			counter = 2;
		} else {
			int index = RANDOM.nextInt(choicesPos.size());
			nextDir = choicesPos.get(index);
		}
		
		previousDir(nextDir);
		
		if (choicesPos.size() > 1) {
			currentIntersection(nextDir);
			counter = 1;
		}
		
		
//		System.out.println("Modified choices length: " + choicesPos.size());

		
		 
//		else if (choicesPos.size() == 1) {
//			counter = 2;
//		}
		
//		System.out.println("Supposed intersection: " + currentIntersection[0]);
//		System.out.println("Current position: " + this.getPosition());
//		System.out.println("Modified choices length: " + choicesPos.size());
//		System.out.println("Counter: " + counter);
		
		if (counter == 2 && checkEnd()) {
			deadEnds(currentIntersection[0]);
		}
		
		return nextDir;
					
	}
			
	private void previousDir(Direction currentDir) {
		previousDir = currentDir;
	}
					
	private void currentIntersection(Direction currentDir) {
		currentIntersection[0] = this.getPosition().addDirectionTo(currentDir);
		//System.out.println("Current interesection:" + this.getPosition().addDirectionTo(currentDir));
	}
	
	private boolean checkEnd() {
		Vector2D position = this.getPosition();
		return (position.equals(currentIntersection[0]));
		//System.out.println(currentIntersection[0].getX() == position.getX());
		//return (currentIntersection[0].getX() == position.getX() && currentIntersection[0].getY() == position.getY());
		//return false;
	}
	
	private ArrayList<Direction> dirToVect(Direction[] choices) { //This method converts all the possible choices to what positions they represent in the labyrinth
		Vector2D currentPos = this.getPosition();
		ArrayList<Direction> choicesPos = new ArrayList<Direction>();
//		System.out.print("New choices: ");
		for (Direction choice : choices) {
			Vector2D choiceVect = currentPos.addDirectionTo(choice);
			if (!deadEnds.contains(choiceVect) && !choice.isOpposite(previousDir)) {
//				System.out.print(choice + ", ");
				choicesPos.add(choice); 
			}
		}
//		System.out.println();
		return choicesPos;
	}
	
	private void deadEnds(Vector2D deadEnd) {
		deadEnds.add(deadEnd);
		System.out.println("Dead End: " + deadEnd);
	}

	@Override
	public Animal copy() {
		Vector2D position = this.getPosition ();
		Animal h = new Hamster(position);
		
		// TODO
		return h;
	}
}
