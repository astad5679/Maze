package ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Random;
import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Panda A.I. that implements Trémeaux's Algorithm.
 * 
 */
public class Panda extends Animal {
	private ArrayList<Vector2D> colorZero = new ArrayList<Vector2D>(); //List containing all uncolored (or simply discovered) positions of the maze
	private ArrayList<Vector2D> colorOne = new ArrayList<Vector2D>(); //List containing all the positions that have been colored a single time
	private ArrayList<Vector2D> colorTwo = new ArrayList<Vector2D>(); //List containing all the positions that have been frequented more than once
	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;
	
	
	/**
	 * Constructs a panda with a starting position.
	 * 
	 * @param position
	 *            Starting position of the panda in the labyrinth
	 */

	public Panda(Vector2D position) {
		super(position);
		// TODO
	}

	/**
	 * Moves according to <i>Trémeaux's Algorithm</i>: when the panda
	 * moves, it will mark the ground at most two times (with two different
	 * colors). It will prefer taking the least marked paths. Special cases
	 * have to be handled, especially when the panda is at an intersection.
	 */

	@Override
	public Direction move(Direction[] choices) {
		control(choices);
		int elementsZero = counter(choices, colorZero);
		int elementsOne = counter(choices, colorOne);
		int elementsTwo = counter(choices, colorTwo);
		
		Direction nextDir = Direction.NONE;
		
		if (elementsZero > 0) {
			nextDir = theChoice(choices, colorZero);
		} else if (elementsOne > 0) {
			if (choices.length > 2 && elementsOne == choices.length){
				nextDir = previousDir.reverse();
			}
			nextDir = theChoice(choices, colorOne);
		} else if (elementsTwo > 0) {
			nextDir = theChoice(choices, colorTwo);
		}
		
		if (coloroCheck(choices, elementsTwo)) {
			color();
		}

		previousDir = nextDir;
		return nextDir;
		
		}
	
	//return the direction the Panda is going to take
	private Direction theChoice (Direction[] choices, ArrayList<Vector2D> elements) {
		ArrayList<Direction> posChoices = new ArrayList<Direction>();
		Vector2D position = this.getPosition();
		
		if (choices.length == 1 && previousDir.equals(Direction.NONE)) {
			return choices[0];
		}
		
		
		for (Direction choice : choices) {
			if (elements.contains(position.addDirectionTo(choice)) &&
				!choice.isOpposite(previousDir)) {
					
				posChoices.add(choice);
			}
		}
		
		if (posChoices.size() == 0) {
			color();
			return previousDir.reverse();
		}
		
		int index = RANDOM.nextInt(posChoices.size());
		return posChoices.get(index);
		}
		
	//return the number of ArrayList's elements 
	private int counter(Direction[] choices, ArrayList<Vector2D> list) {
		Vector2D position = this.getPosition();
		int counter = 0;
		for (Direction choice : choices){
			if(list.contains(position.addDirectionTo(choice))) {
				counter++;	
			}
		}
		return counter;
		}
	
	//if the box it is not "colored" it become colorZero
	private void control(Direction[] choices) {
		Vector2D position = this.getPosition();
		for (Direction choice : choices) {
			if (!(colorZero.contains(position.addDirectionTo(choice))) && 
				!(colorOne.contains(position.addDirectionTo(choice))) && 
				!(colorTwo.contains(position.addDirectionTo(choice)))
				) {
				colorZero.add(position.addDirectionTo(choice));
			}
		}	
	}
	
	//applies the color to the boxes
	private void color() { 
		
		Vector2D position = this.getPosition();
		if (previousDir == Direction.NONE) {
			if (!(colorOne.contains(this.getPosition()))){
				colorOne.add(this.getPosition());
			}
		}
		
		if (colorZero.contains(position)) {
			colorZero.remove(position);
			colorOne.add(position);
		} else if (colorOne.contains(position)) {
			colorOne.remove(position);
			colorTwo.add(position);
		}
	}
	
	//until the crossroad have more than one colorOne or colorZero possibilities it doesn't color the crossroad's box
	private boolean coloroCheck(Direction[] choices, int two) {
		
		if (choices.length > 2 &&  choices.length - 1 > two) {
			if (!(colorOne.contains(this.getPosition()))){
				colorOne.add(this.getPosition());
				if (colorZero.contains(this.getPosition())) {
					colorZero.remove(this.getPosition());
				}
			}
			return false;
		}
		return true;
	}
	
	
	@Override
	public Animal copy() { //Creates a new panda which it initializes with the same position of the instance this method is called from 
		// TODO
		Vector2D position = this.getPosition ();
		Panda p = new Panda(position);
		return p;
	}
}
