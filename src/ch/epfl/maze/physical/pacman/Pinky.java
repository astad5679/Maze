package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Pink ghost from the Pac-Man game, targets 4 squares in front of its target.
 * 
 */

public class Pinky extends Predator {
	private Direction currentDir = Direction.NONE;
	private double minDist = Double.POSITIVE_INFINITY;
	private Direction nextDir = Direction.NONE;
	private Vector2D preyPrev;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public Pinky(Vector2D position) {
		super(position);
		preyPrev = this.getPosition();
		// TODO
	}

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		// TODO
		if (daedalus.getPreys().size() == 0) {
			System.out.println("NO PREY!");
			return Direction.NONE;
		}
		
		Prey prey = daedalus.getPreys().get(0);
		Vector2D preyPos = prey.getPosition();
		
		Vector2D preyVect = preyPos.sub(preyPrev);
		Direction preyDir = preyVect.toDirection();
//		System.out.println(preyDir);
//		System.out.println(position);
		
		if (choices.length == 1 && choices[0] != Direction.NONE) { //This method disregards the main aspect of the mouse which is, as prescribed, never to retrace its steps
			previousDir(choices[0]);							   //considering that it does need to turn around if at a dead end, we admit that in the case where only 
			return choices[0];									   //one direction is available, he will choose that one no matter what
		} else if (choices.length == 0 || choices[0] == Direction.NONE) {
			System.out.println("NO CHOICES!");
			return Direction.NONE;
		}
		
		nextDir = currentDir;
		minDist = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < 4; i++) {
			preyPos = preyPos.addDirectionTo(preyDir);
//			System.out.println("position " + i + ": " + position);
		}
		
		
		for (Direction choice : choices) {
			if (!choice.isOpposite(currentDir)) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), preyPos);
//				System.out.println(choice + ": " + distance);
				if (distanceCheck(distance)) {
					nextDir = choice;
				}
			}
		}
		
		preyPrev = preyPos;
		previousDir(nextDir);	
//		System.out.println(currentDir);
		return currentDir;
		
			
	}
	
	private void previousDir(Direction nextDir) { //This method simply updates the value of the previous direction with the one of the current one
		currentDir = nextDir;
	}
	
	private double distanceCalc(Vector2D nextPos, Vector2D preyPos) { 
		Vector2D difference = nextPos.sub(preyPos);
		return difference.dist();
	}
	
	private boolean distanceCheck(double newDist) {
		if (newDist < minDist) {
			minDist = newDist;
			return true;
		}
		return false;
	}
	
	@Override
	public Animal copy() {
		// TODO
		Pinky p = new Pinky(this.getPosition());
		return p;
	}
}
