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
		
		if (choices.length > 2){	
			//nel caso non ci siano piu vie libere mi fermo
			if (choicesPos.size() == 0 && counter == 2){	
//				System.out.println(deadEnds.size());
				return Direction.NONE;
			}
			//aggiungo il vicolo cieco alla lista (se counter == 2)
			if (counter == 2){
				//se arrivo da un vicolo cieco e ho una sola strada possibile, allora questo incrocio sara un vicolo cieco
				if (choicesPos.size() == 1){
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
//					System.out.println(deadEnd);
				}
				//se arrivo da un vicolo cieco e ho altre scelte, tolgo semplicemente la scelta da quelle possibili del vicolo cieco
				else {
					Vector2D deadEnd = this.getPosition().addDirectionTo(previousDir.reverse());
					deadEnds.add(deadEnd);
//					System.out.println(deadEnd);
					counter = 1;
				}
			}
			
			//se non ho scelte torno da dove sono venuto e metto il counter == 2
			if (choicesPos.size() == 0){					  	
				counter = 2;
				nextDir = previousDir.reverse();
			
			//se ho piu scelte scelgo a caso
			} else {
				int index = RANDOM.nextInt(choicesPos.size()); 
				nextDir = choicesPos.get(index);
			}
			
			previousDir = nextDir;
			return nextDir;
		
		// Non mi trovo in un incrocio
		} else {								
			// Se sono in un vicolo cieco torno in dietro e metto counter == 2
			if (choicesPos.size() == 0){						
				counter = 2;
				nextDir = previousDir.reverse();
			} else {
			// scelgo la scelta non contraria a quella in cui sto viaggiango
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
	
	private ArrayList<Direction> dirToVect(Direction[] choices) { //This method converts all the possible choices to what positions they represent in the labyrinth
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
