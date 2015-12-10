package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * Red ghost from the Pac-Man game, chases directly its target.
 * 
 */

public class Blinky extends Predator {
	private Direction currentDir = Direction.NONE;
	private double minDist = Double.POSITIVE_INFINITY;
	private Direction nextDir = Direction.NONE;

	/**
	 * Constructs a Blinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Blinky in the labyrinth
	 */

	public Blinky(Vector2D position) {
		super(position);
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
		Vector2D position = prey.getPosition();
//		System.out.println(position);
		
		if (choices.length == 1 && choices[0] != Direction.NONE) { //This method disregards the main aspect of the mouse which is, as prescribed, never to retrace its steps
			previousDir(choices[0]);							   //considering that it does need to turn around if at a dead end, we admit that in the case where only 
			return choices[0];									   //one direction is available, he will choose that one no matter what
		} else if (choices.length == 0 || choices[0] == Direction.NONE) {
//			System.out.println("NO CHOICES!");
			return Direction.NONE;
		}
		
		nextDir = currentDir;
		minDist = Double.POSITIVE_INFINITY;
		
		for (Direction choice : choices) {
			if (!choice.isOpposite(currentDir)) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), position);
//				System.out.println(choice + ": " + distance);
				if (distanceCheck(distance)) {
					nextDir = choice;
				}
			}
		}
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
		Blinky b = new Blinky(this.getPosition());
		return b;
	}
}
