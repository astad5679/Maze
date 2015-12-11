package ch.epfl.maze.physical.pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Pac-Man character, from the famous game of the same name.
 * 
 */

public class PacMan extends Prey {

	private final Random RANDOM = new Random();
	private Direction previousDir = Direction.NONE;
	private double minDist;
	private double distPred;
	private Direction nextDir; 
	private final int SEARCH_RADIUS = 4;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public PacMan(Vector2D position) {
		super(position);
		// TODO
	}

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		ArrayList<Direction> modChoices = new ArrayList<Direction>();
		for (Direction choice : choices) {
			if (!choice.isOpposite(previousDir)) {
				modChoices.add(choice);
			}
		}
		
		if (choices.length == 1 && choices[0] != Direction.NONE) {
			nextDir = choices[0];
		}
		
		List<Predator> predators = daedalus.getPredators();
		minDist = Double.POSITIVE_INFINITY;
		Vector2D predPos = daedalus.getPredators().get(0).getPosition();
		
		
//		ArrayList<Predator> closePred = new ArrayList<Predator>();
		for (Predator predator : predators){
			distPred = distCalcul(predator.getPosition());
//			
			if (distPred < SEARCH_RADIUS){
				if (distPred < minDist){
					predPos = predator.getPosition();
					minDist = distPred;
//					closePred.add(predator);
//				} else if (distPred == minDist) {
//					closePred.add(predator);
				}
			}
		}
		
		if (choices.length == 2) {
			
		}
		
		if (choices.length > 2){
			
			
			
//			if (closePred.size() > 1) {
//				return Direction.NONE;
//			}
			
//			ArrayList<Direction> modChoices = new ArrayList<Direction>();
//			for (Direction choice : choices) {
//				if (!choice.isOpposite(previousDir)) {
//					modChoices.add(choice);
//				}
//			}

			if (minDist ==  Double.POSITIVE_INFINITY) {
				int index = RANDOM.nextInt(choices.length);
				previousDir(choices[index]);
				return choices[index];
			}
			
			for (Direction choice : choices) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), predPos);
				boolean check = false;
				
//				System.out.println(choice + ": " + distance);
				if (distanceCheck(distance)) {
					nextDir = choice;
//					if (distance < 1) {
//						check = true;
//					} else {
//						check = false;
//					}
				}
//				if (check) {
//					return Direction.NONE;
//				}
			}
		} else {
			
			if (choices.length == 1 && choices[0] != Direction.NONE) { //This method disregards the main aspect of the mouse which is, as prescribed, never to retrace its steps
				previousDir(choices[0]);							   //considering that it does need to turn around if at a dead end, we admit that in the case where only 
				return choices[0];									   //one direction is available, he will choose that one no matter what
				
			} 
			
			for (Direction choice : choices) {
				if (!choice.isOpposite(previousDir)) {
					previousDir(choice);
					return choice;
				}
			}
//			
//			int index = RANDOM.nextInt(mouseChoices.size()); //We only generate a random index when the taboo direction has been removed from the new list of choices, this
//															 //allows us to get rid of a potentially infinite while loop
//			previousDir(mouseChoices.get(index));
//			nextDir = previousDir;
//			System.out.println("Direzione non-incrocio: " + nextDir);
		}
		previousDir(nextDir);
		return nextDir;
		
	}
	
	private double distanceCalc(Vector2D nextPos, Vector2D preyPos) { 
		Vector2D difference = nextPos.sub(preyPos);
		return difference.dist();
	}
	
	private boolean distanceCheck(double newDist) {
		if (newDist > minDist) {
			minDist = newDist;
			return true;
		}
		return false;
	}
	
	private void previousDir(Direction currentDir) { //This method simply updates the value of the previous direction with the one of the current one
		previousDir = currentDir;
	}
	
	private double distCalcul(Vector2D posPred){
		Vector2D position = this.getPosition();
		Vector2D vectDist = position.sub(posPred);
		
		return vectDist.dist();
	}
	
	@Override
	public Animal copy() {
		// TODO
		PacMan p = new PacMan(this.getPosition());
		return p;
	}
}
