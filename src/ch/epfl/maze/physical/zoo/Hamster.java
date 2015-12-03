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
	private ArrayList<ArrayList<Vector2D>> crossings;

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

		int index = RANDOM.nextInt(choices.length);
		
		ArrayList<Vector2D> choicesPos = dirToVect(choices);
		
		while(choices[index].isOpposite(previousDir) || (deadEnds.contains(this.getPosition().addDirectionTo(choices[index])) && choices.length == 3)) {
			if (choices.length == 1 && choices[0] != Direction.NONE) {
				previousDir(choices[0]);
				counter = 2;
				return choices[0];
			} else if (choices.length == 0) {
				return Direction.NONE;
			} //else {		
			index = RANDOM.nextInt(choices.length);
			//}
		}
		
//				System.out.print(this.getPosition());
//				if (choices.length >= 3) {
//					lastChoice (choices[index]);
//					System.out.println(" remembered: " + lastChoice[0]);
//				}
				
		previousDir(choices[index]);
				//System.out.println(choices[index]);
		
		
		if (choices.length >= 3) {
			counter = 1;
			currentIntersection(choices[index]);
			//System.out.println("Current interesection:" + choices[index]);
		}
		
		if (checkEnd()) {
			if (counter == 1) {
				deadEnds(this.getPosition());
				System.out.println("Dead end: " + this.getPosition());
				counter = 2;
			}
		}
		
		//System.out.println(checkEnd());
		
		
		//counter = 1;
		
		return choices[index];
					
	}
			
	private void previousDir(Direction currentDir) {
		previousDir = currentDir;
	}
					
	private void currentIntersection(Direction currentDir) {
		currentIntersection[0] = (this.getPosition().addDirectionTo(currentDir));
		System.out.println("Current interesection:" + this.getPosition().addDirectionTo(currentDir));
	}
	
	private boolean checkEnd() {
		Vector2D position = this.getPosition();
		return (position.equals(currentIntersection[0]));
		//System.out.println(currentIntersection[0].getX() == position.getX());
		//return (currentIntersection[0].getX() == position.getX() && currentIntersection[0].getY() == position.getY());
		//return false;
	}
	
	private ArrayList<Vector2D> dirToVect(Direction[] choices) { //This method converts all the possible choices to what positions they represent in the labyrinth
		Vector2D currentPos = this.getPosition();
		ArrayList<Vector2D> choicesPos = new ArrayList<Vector2D>();
		for (Direction choice : choices) {
			Vector2D choiceVect = currentPos.addDirectionTo(choice);
			if (!deadEnds.contains(choiceVect)) {
				choicesPos.add(choiceVect); 
			}
		}
		return choicesPos;
	}
	
	private void deadEnds(Vector2D deadEnd) {
		deadEnds.add(deadEnd);
	}

	@Override
	public Animal copy() {
		Vector2D position = this.getPosition ();
		Animal h = new Hamster(position);
		
		// TODO
		return h;
	}
}
