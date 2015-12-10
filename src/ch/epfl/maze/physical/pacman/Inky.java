package ch.epfl.maze.physical.pacman;

import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.Predator;
import ch.epfl.maze.physical.Prey;
import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;
import ch.epfl.maze.physical.pacman.Blinky;

/**
 * Pink ghost from the Pac-Man game, targets 4 squares in front of its target.
 * 
 */

public class Inky extends Predator {
	private Direction currentDir = Direction.NONE;
	private double minDist = Double.POSITIVE_INFINITY;
	private Direction nextDir = Direction.NONE;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public Inky(Vector2D position) {
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
		Vector2D preyPos = prey.getPosition();
		Vector2D blinkyPos = new Vector2D(0, 0);
		
		for (Predator pred : daedalus.getPredators()) {
			if (pred instanceof Blinky) {
//				System.out.println("check instance ok");
				blinkyPos = pred.getPosition();
			}
		}
		
		Vector2D blinkyDist = blinkyPos.sub(preyPos);
		blinkyDist = blinkyDist.add(blinkyDist);
		
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
		
		for (Direction choice : choices) {
			if (!choice.isOpposite(currentDir)) {
				double distance = distanceCalc(this.getPosition().addDirectionTo(choice), blinkyDist);
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
		Inky p = new Inky(this.getPosition());
		return p;
	}
}
