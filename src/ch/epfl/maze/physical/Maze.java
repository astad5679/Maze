package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.maze.util.Vector2D;

/**
 * Maze in which an animal starts from a starting point and must find the exit.
 * Every animal added will have its position set to the starting point. The
 * animal is removed from the maze when it finds the exit.
 * 
 */

public final class Maze extends World {
	
	private List<Animal> endAnimals = new ArrayList<Animal>();
	private List<Animal> navAnimals = new ArrayList<Animal>();

	/**
	 * Constructs a Maze with a labyrinth structure.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Maze(int[][] labyrinth) {
		super(labyrinth); //What is going on here?
		// TODO
	}

	@Override
	public boolean isSolved() {
		//navAnimals = getAnimals();
		return navAnimals.isEmpty();
		// TODO
	}

	@Override
	public List<Animal> getAnimals() { //copy constructor
		// TODO
		//System.out.println(navAnimals.get(0));
		return navAnimals;
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
		// TODO
		//make sure we understand differences of List vs. ArrayList
		//maybe differentiate between one list containing the animals on the map, and the other in general so we can reset animals respectively
			//ask certain questions: are all animals always present? Does this change in basis of how much you add, and then these animals can never be fully removed
		return navAnimals.contains(a);
	}

	/**
	 * Adds an animal to the maze.
	 * 
	 * @param a
	 *            The animal to add
	 */

	public void addAnimal(Animal a) {
		//Same for over here
		Vector2D start = this.getStart();
		a.setPosition(start);
		//endAnimals.add(a);
		navAnimals.add(a);
		// TODO
	}

	/**
	 * Removes an animal from the maze.
	 * 
	 * @param a
	 *            The animal to remove
	 */

	public void removeAnimal(Animal a) {
		//Need to test if the reference stuff has any impact on these commands
		//getAnimals().remove(a);
		//a.setPosition(this.getExit());
		navAnimals.remove(a);
		endAnimals.add(a);
		// TODO
	}

	@Override
	public void reset() {
		//I need to pass an instance of the class as an argument to be able to use the getStart() getter
		//Can we import any classes we want? We would technically need Vector2D
		for (Animal creature : navAnimals) {
			endAnimals.add(creature);
		}
		
		navAnimals = new ArrayList<Animal>();
		
		for (Animal creature : endAnimals) {
			Animal newCreature = creature.copy();
			navAnimals.add(newCreature);
		}
		
		
		
		// TODO
	}
	
	
}
