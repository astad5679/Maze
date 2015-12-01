package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.maze.physical.zoo.Mouse;
import ch.epfl.maze.util.Vector2D;

/**
 * Maze in which an animal starts from a starting point and must find the exit.
 * Every animal added will have its position set to the starting point. The
 * animal is removed from the maze when it finds the exit.
 * 
 */

public final class Maze extends World {
	
	private static ArrayList<Animal> animal = new ArrayList<Animal>();
	private static List<Animal> animals; // = new ArrayList<Animal>();

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
		animals = getAnimals();
		return animals.isEmpty();
		// TODO
	}

	@Override
	public List<Animal> getAnimals() { //copy constructor
		// TODO
		animals = new ArrayList<Animal>();
		Vector2D exit = this.getExit();
		for (Animal creature : animal) { //Is this plausible?
			//System.out.println(creature);
			if (creature.getPosition().equals(exit)) {
				continue;
			}
			else {
				Animal nCreature = new Mouse(creature.getPosition());
				animals.add(nCreature);
			}
		}
		return animals;
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
		animals = getAnimals();
		//make sure we understand differences of List vs. ArrayList
		//maybe differentiate between one list containing the animals on the map, and the other in general so we can reset animals respectively
			//ask certain questions: are all animals always present? Does this change in basis of how much you add, and then these animals can never be fully removed
		return animals.contains(a);
	}

	/**
	 * Adds an animal to the maze.
	 * 
	 * @param a
	 *            The animal to add
	 */

	public void addAnimal(Animal a) {
		//ArrayList<Animal> animal = new ArrayList<Animal>();
		//Same for over here
		Vector2D start = this.getStart();
		a.setPosition(start);
		animal.add(a);
		// TODO
	}

	/**
	 * Removes an animal from the maze.
	 * 
	 * @param a
	 *            The animal to remove
	 */

	public void removeAnimal(Animal a) {
		//ArrayList<Animal> animal = new ArrayList<Animal>();
		//Need to test if the reference stuff has any impact on these commands
		//getAnimals().remove(a);
		a.setPosition(this.getExit());
		// TODO
	}

	@Override
	public void reset() {
		//I need to pass an instance of the class as an argument to be able to use the getStart() getter
		//Can we import any classes we want? We would technically need Vector2D
		Vector2D start = this.getStart();
		//Vector2D start = new Vector2D(0,0);
		for (int i = 0; i < animal.size(); i++) {
			Animal creature = animal.get(i);
			creature.setPosition(start);
		}
		
		// TODO
	}
	
	
}
