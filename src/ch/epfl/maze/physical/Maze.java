package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.maze.util.Vector2D;
import ch.epfl.maze.physical.Animal;

/**
 * Maze in which an animal starts from a starting point and must find the exit.
 * Every animal added will have its position set to the starting point. The
 * animal is removed from the maze when it finds the exit.
 * 
 */

public final class Maze extends World {
	
	//We create two lists, the first of which will contain all the animals that are currently moving in the maze at any given moment
	//The second basically stores the same animal, but this will never change even as the animal moves. This serves for the reset() method which requires an exact copy of the 
	//animal initially added into the maze, effectively preserving all of it's original attributes
	private List<Animal> navAnimals = new ArrayList<Animal>();
	private List<Animal> endAnimals = new ArrayList<Animal>();
	
	/**
	 * Constructs a Maze with a labyrinth structure.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Maze(int[][] labyrinth) {
		super(labyrinth); 
	}

	@Override
	public boolean isSolved() {
		return navAnimals.isEmpty();
	}

	@Override
	public List<Animal> getAnimals() { 
		List<Animal> temp = new ArrayList<Animal>();
		for (Animal animal : navAnimals) {
			temp.add(animal);
		}
		return temp;
	}

	/**
	 * Determines if the maze contains an animal.
	 * 
	 * @param a
	 *            The animal in question
	 * @return <b>true</b> if the animal belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasAnimal(Animal a) {
		return navAnimals.contains(a);
	}

	/**
	 * Adds an animal to the maze.
	 * 
	 * @param a
	 *            The animal to add
	 */

	public void addAnimal(Animal a) { //the animal is set to the starting position and is appended to both lists
		Vector2D start = this.getStart();
		a.setPosition(start);
		navAnimals.add(a);
		endAnimals.add(a);
	}

	/**
	 * Removes an animal from the maze.
	 * 
	 * @param a
	 *            The animal to remove
	 */

	public void removeAnimal(Animal a) {
		navAnimals.remove(a);
	}

	@Override
	public void reset() {
		navAnimals = new ArrayList<Animal>(); //resets the navigation list to make way for the new animal instances copied from the "final" or end list
		
		for (Animal creature : endAnimals) {
			Animal newCreature = creature.copy();
			navAnimals.add(newCreature);
		}
	}
	
	
}
