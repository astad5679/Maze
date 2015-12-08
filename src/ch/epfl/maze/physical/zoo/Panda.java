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
	private ArrayList<Vector2D> colorZero = new ArrayList<Vector2D>(); 
	private ArrayList<Vector2D> colorOne = new ArrayList<Vector2D>();
	private ArrayList<Vector2D> colorTwo = new ArrayList<Vector2D>();
	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;
	
	private int[][] laby = new int [21][29];
	
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
		
		if (elementsZero > 0){
			nextDir = theChoice(choices, colorZero);
		} else if (elementsOne > 0){
			if (choices.length > 2 && elementsOne == choices.length){
				nextDir = previousDir.reverse();
			}
			nextDir = theChoice(choices, colorOne);
		} else if (elementsTwo > 0){
			nextDir = theChoice(choices, colorTwo);
		}
		
		if (choices.length > 2 && (elementsZero > 0) && (elementsOne > 0)){
			
		} else {
			color();
		}
		
		//Matrice per il debug
//		for (int i = 0; i < 21; i++) {
//		    for (int j = 0; j < 29; j++) {
//		        System.out.print(laby[i][j] + " ");
//		    }
//		    System.out.print("\n");
//		}
//		System.out.println();
		previousDir = nextDir;
		return nextDir;
		}
	
	//mi ritorna la scelta finale
	private Direction theChoice (Direction[] choices, ArrayList<Vector2D> elements){
		ArrayList<Direction> posChoices = new ArrayList<Direction>();
		Vector2D position = this.getPosition();
		
		if (choices.length == 1 && previousDir.equals(Direction.NONE)){
			return choices[0];
		}
		
		for (int i = 0; i < choices.length; i++){
			if (elements.contains(position.addDirectionTo(choices[i])) &&
				!choices[i].isOpposite(previousDir)){
					
				posChoices.add(choices[i]);
			}
		}
		
		if (posChoices.size() == 0){
			color();
			return previousDir.reverse();
		}
		
		int index = RANDOM.nextInt(posChoices.size());
		return posChoices.get(index);
	}
		
	//mi dice quanti elementi ci sono in un arrayList
	private int counter(Direction[] choices, ArrayList<Vector2D> list){
		Vector2D position = this.getPosition();
		int counter = 0;
		for (int i = 0; i < choices.length; i++){
			if(list.contains(position.addDirectionTo(choices[i]))){
			counter++;	
			}
		}
		return counter;
	}
	
	//aggiunge le caselle che non sono colorate al colorZero
	private void control(Direction[] choices){
		Vector2D position = this.getPosition();
		int x;
		int y;
		for (int i = 0; i < choices.length; i++) {
			if (!(colorZero.contains(position.addDirectionTo(choices[i]))) && 
				!(colorOne.contains(position.addDirectionTo(choices[i]))) && 
				!(colorTwo.contains(position.addDirectionTo(choices[i])))
				){
				colorZero.add(position.addDirectionTo(choices[i]));
				x = position.addDirectionTo(choices[i]).getX();
				y = position.addDirectionTo(choices[i]).getY();
				laby[y][x] = 1;
			}
		}	
	}
	
	//"colora" le caselle
	private void color(){
		Vector2D position = this.getPosition();
		int x, y;
		if (colorZero.contains(position)){
			colorZero.remove(position);
			colorOne.add(position);
			x = position.getX();
			y = position.getY();
			laby[y][x] = 2;
		} else if (colorOne.contains(position)){
			colorOne.remove(position);
			colorTwo.add(position);
			x = position.getX();
			y = position.getY();
			laby[y][x] = 3;
		}
	}
	
	
	@Override
	public Animal copy() {
		// TODO
		Vector2D position = this.getPosition ();
		Animal p = new Panda(position);
		
		return p;
	}
}
